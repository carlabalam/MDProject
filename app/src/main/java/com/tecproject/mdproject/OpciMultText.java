package com.tecproject.mdproject;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;

public class OpciMultText extends AppCompatActivity {

    private TextView instruction;
    private ImageView imageExercise;
    private Button aceptar;
    private RadioButton r1, r2, r3;
    private String textInstruction = "";
    private int id_subtema;

    //objeto de clase SQLIteDatabase
    SQLiteDatabase db = null;

    private Cursor cursorEjercicio;
    private Cursor cursor2 = null;

    //para guardar los bits de la imagen
    private byte[] imagenData;
    private String respuestaDesdeBd;
    private String respuestaXUsario;
    private int respuestasCorrectas = 0;

    private int[] ejerciciosT2 = {15, 17, 20, 22, 57, 59, 61, 71, 74, 78};
    private int indexArrayT2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opci_mult_text);

        //Bloquea la orientación en vertical, LANDSCAPE es horizontal
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //pertence al ejercicio de enviar datos entre actividades
        Intent intentar = getIntent(); //esto cacha lo que le estamos pasando en el activity 1
        Bundle bundle = intentar.getExtras();

        if (bundle != null) {
            int id_subtema_recogido = bundle.getInt("id_subtema");
            set_id_subtema(id_subtema_recogido);
        }

        instruction = (TextView) findViewById(R.id.InstruccionesConjuntos);
        imageExercise = (ImageView) findViewById(R.id.imageExercise);
        r1 = (RadioButton) findViewById(R.id.res1);
        aceptar = (Button) findViewById(R.id.AceptarConjuntos);

        //r1.setText("fdfdfdfffddf");

        //abrimos la conexion con la bd
        db = openOrCreateDatabase("BaseDatos.sqlite", MODE_PRIVATE, null);

        //consulta los ejercicios
        ejecutaSQL();
        muestraTabla();
        instruction.setText("dhewjewhjew");
        //imageExercise.setImageBitmap(consultarImagen());

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //respuestaXUsario = textRespuesta.getText().toString();

                if (indexArrayT2 < 5){
                    countAnswers(respuestaDesdeBd, respuestaXUsario);
                } else {
                    lanzarMensajeEjercicio();
                }
            }
        });

    }

    public void set_id_subtema(int id_subtema) {
        this.id_subtema = id_subtema;
    }

    public void ejecutaSQL() {
        cursorEjercicio = db.rawQuery("SELECT BancoInstruccionesEjercicios.SubTemas_id,BancoRespuestasEjercicios.BancoEjercicios_id,BancoInstruccionesEjercicios.Instrucccion,BancoEjercicios.Ejercicio,BancoRespuestasEjercicios.TextosRespuesta,BancoRespuestasEjercicios.EsRespuesta , BancoRespuestasEjercicios.Ponderacion FROM BancoInstruccionesEjercicios left join BancoEjercicios ON BancoEjercicios.BancoIntruccionesEjercicios_id = BancoInstruccionesEjercicios._id LEFT JOIN BancoRespuestasEjercicios ON BancoRespuestasEjercicios.BancoEjercicios_id = BancoEjercicios._id WHERE BancoInstruccionesEjercicios.SubTemas_id = " + id_subtema + " AND BancoRespuestasEjercicios.BancoEjercicios_id = " + ejerciciosT2[indexArrayT2] , null);
    }

    private void muestraTabla() {
        if(cursorEjercicio.moveToFirst()){
            String instruccion = cursorEjercicio.getString(2);
            respuestaDesdeBd = cursorEjercicio.getString(4);
            textInstruction = textInstruction + instruccion;
        }
    }

    private Bitmap consultarImagen() {
        cursor2 = db.rawQuery("SELECT BancoImagenes.Imagen FROM BancoImagenes_X_BancoEjercicios,BancoEjercicios, BancoImagenes WHERE (BancoImagenes_X_BancoEjercicios.BancoEjercicios_id = BancoEjercicios._id and BancoImagenes_X_BancoEjercicios.BancoImagenes_id= BancoImagenes._id) AND BancoEjercicios._id = " + ejerciciosT2[indexArrayT2], null);
        cursor2.moveToFirst();
        imagenData = cursor2.getBlob(0);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imagenData);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    private void countAnswers(String r1, String r2) {
        if (r1.equals(r2)){
            respuestasCorrectas ++;
            Toast.makeText(OpciMultText.this, "Respuesta de bd" + respuestaDesdeBd + "\n" + "Respuesta de user" + respuestaXUsario + "\n" + "Correctas: " + respuestasCorrectas, Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(OpciMultText.this, "no entro al if", Toast.LENGTH_SHORT).show();
        }

        indexArrayT2 ++;

        textInstruction = "";
        ejecutaSQL();
        muestraTabla();
        instruction.setText(textInstruction);
        imageExercise.setImageBitmap(consultarImagen());
    }

    private void lanzarMensajeEjercicio(){
        AlertDialog.Builder alertDialogBuider = new AlertDialog.Builder(OpciMultText.this);
        alertDialogBuider.setMessage("Has finalizado el tema. \nTienes " + respuestasCorrectas +
                " respuestas correctas de 6 ejercicios")
                .setCancelable(false)
                .setPositiveButton("continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(OpciMultText.this, Home.class);
                        startActivity(intent);
                    }
                });

        AlertDialog alertDialog = alertDialogBuider.create();
        alertDialog.show();
    }
}