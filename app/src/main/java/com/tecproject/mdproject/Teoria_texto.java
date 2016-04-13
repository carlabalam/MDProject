package com.tecproject.mdproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    int numRows;

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
                imagen.setImageResource(R.drawable.imagen1);

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
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent22  = new Intent(Teoria_texto.this, Subtemas.class);
                boolean loquesea = true;
                intent22.putExtra("loquesea", loquesea);
                startActivity(intent22);
                startActivity(intent22);
            }
        });
    }

    private void ejecutaSQL() {
        cursor = db.rawQuery("SELECT * FROM BancoTextos WHERE SubTemas_id == " + id_subtema + "  AND Secuencia ==" + id_secuencia, null);
    }

    private void muestraTabla() {
        cursor.moveToFirst();
        String titulo = cursor.getString(3);
        texto = texto + "\n " + titulo;
        cursor.moveToNext();
    }

    private void set_id_subtema(int id_subtema) {
        this.id_subtema = id_subtema;
    }

    private void regresarRows(){
        cursor2 = db.rawQuery("SELECT * FROM BancoTextos WHERE SubTemas_id == " + id_subtema, null);
        numRows = cursor2.getCount();
    }
}
