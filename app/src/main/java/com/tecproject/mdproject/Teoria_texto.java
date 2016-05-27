package com.tecproject.mdproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Teoria_texto extends AppCompatActivity {
    int id_secuencia = 0;
    int id_subtema;
    private TextView tv;
    private ImageView imagen;
    private Button next, atras, finalizar;
    private String texto = "";
    SQLiteDatabase db = null;
    Cursor cursor = null;
    Cursor cursor2 = null;
    Cursor cursor3 = null;
    int numRows;
    public byte [] imagenData;
    public int longArray;
    public int total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teoria_texto);


        //pertence al ejercicio de enviar datos entre actividades
        Intent intentar = getIntent(); //esto cacha lo que le estamos pasando en el activity 1
        Bundle bundle = intentar.getExtras();

        if (bundle != null) {
            int id_subtema_recogido = bundle.getInt("id_subtema");
            set_id_subtema(id_subtema_recogido);
        }

        db = openOrCreateDatabase("BaseDatos.sqlite", MODE_PRIVATE, null);

        tv = (TextView) findViewById(R.id.textView);
        next = (Button) findViewById(R.id.btnNext);
        atras= (Button) findViewById(R.id.bAtras);
        finalizar = (Button) findViewById(R.id.btnFin);
        imagen = (ImageView) findViewById(R.id.imageViewT);

        regresarRows();
        ejecutaSQL();
        muestraTabla();
        tv.setText(texto);
        /*if(muestraTabla()){
            imagen.setImageBitmap(MapearImagen());
        }*/
        atras.setVisibility(View.GONE);


        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                id_secuencia++;


                if (id_secuencia > 0){
                    atras.setVisibility(View.VISIBLE);
                    atras.setEnabled(true);
                }

                texto = "";
                ejecutaSQL();
                muestraTabla();
                tv.setText(texto);
                if(muestraTabla()){
                    imagen.setImageBitmap(MapearImagen());
                }

                if(id_secuencia == numRows-1){
                    next.setVisibility(View.INVISIBLE);
                    finalizar.setVisibility(View.VISIBLE);

                }


            }
        });

        atras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    id_secuencia--;
                next.setVisibility(View.VISIBLE);
                finalizar.setVisibility(View.GONE);

                    if(id_secuencia==0){
                        atras.setVisibility(View.GONE);
                    }

                    texto = "";
                    ejecutaSQL();
                    muestraTabla();
                    tv.setText(texto);
                    if(muestraTabla()){
                        imagen.setImageBitmap(MapearImagen());
                    }
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent22  = new Intent(Teoria_texto.this, Subtemas.class);
                boolean loquesea = true;
                intent22.putExtra("id_subtema", id_subtema);
                startActivity(intent22);
                startActivity(intent22);
            }
        });
    }

    private void ejecutaSQL() {
        cursor = db.rawQuery("SELECT BancoTextos.Secuencia,BancoTextos.Textos,SubTemas.NomSubtema,BancoImagenes.Imagen,BancoImagenes._id FROM BancoTextos LEFT JOIN SubTemas ON BancoTextos.SubTemas_id = SubTemas._id LEFT JOIN BancoImagenes ON BancoTextos.BancoImagenes_id = BancoImagenes._id WHERE BancoTextos.SubTemas_id == " + id_subtema + " and BancoTextos.Secuencia == "+ id_secuencia, null);
        /*cursor = db.rawQuery("SELECT * FROM BancoTextos WHERE SubTemas_id == " + id_subtema + "  AND Secuencia ==" + id_secuencia, null);*/

    }
    //1 INT
    //2 STRING
    //3 STRING
    //4 ARRAY BYTE
    //5 ID

    private boolean muestraTabla() {

        boolean bandera = false;
        cursor.moveToFirst();
        String texto = cursor.getString(1);
        this.texto = texto + "\n " + texto;


        if (cursor.getBlob(3) != null){
            bandera = true;
            byte[] imagenData = cursor.getBlob(3);
            int largoArray = imagenData.length;
            Toast.makeText(this, "Largo array" + largoArray, Toast.LENGTH_SHORT).show();

        }
        Toast.makeText(this, "No HAY IMAGEN", Toast.LENGTH_SHORT).show();
        cursor.moveToNext();

        return bandera;
    }



    /*public static Bitmap getImage(byte[] imagen) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imagen);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }*/
    public Bitmap MapearImagen(){

        Bitmap bitmap = BitmapFactory.decodeByteArray(this.imagenData , 0, this.imagenData .length);

        return bitmap;
    }


    private void set_id_subtema(int id_subtema) {
        this.id_subtema = id_subtema;
    }

    private void regresarRows(){
        cursor2 = db.rawQuery("SELECT * FROM BancoTextos WHERE SubTemas_id == " + id_subtema, null);
        numRows = cursor2.getCount();
    }
}
