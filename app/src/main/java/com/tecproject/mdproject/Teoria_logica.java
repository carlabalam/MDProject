package com.tecproject.mdproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class Teoria_logica extends AppCompatActivity {

    int id_secuencia = 0;
    int id_subtema=13;
    private TextView tv;
    private ImageView imagen;
    private ImageButton next, atras, finalizar;
    private String texto = "";
    SQLiteDatabase db = null;
    private Cursor cursor = null;
    private Cursor cursor2 = null;
    private Cursor cursor3 = null;
    private int numRows;
    private byte[] imagenData;
    private int campoIdPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teoria_logica);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Bloquea la orientación en vertical, LANDSCAPE es horizontal
        //pertence al ejercicio de enviar datos entre actividades
        Intent intentar = getIntent(); //esto cacha lo que le estamos pasando en el activity 1
        Bundle bundle = intentar.getExtras();if (bundle != null) {
            int id_subtema_recogido = bundle.getInt("id_subtema");
            set_id_subtema(id_subtema_recogido);
        }

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(getResources().getColor(R.color.cafe));
        }
        db = openOrCreateDatabase("BaseDatos.sqlite", MODE_PRIVATE, null);

        tv = (TextView) findViewById(R.id.textView);
        next = (ImageButton) findViewById(R.id.btnNext);
        atras= (ImageButton) findViewById(R.id.bAtras);
        finalizar = (ImageButton) findViewById(R.id.btnFin);
        imagen = (ImageView) findViewById(R.id.imageViewT);


        regresarRows();
        ejecutaSQL();
        muestraTabla();
        tv.setText(texto);
        if (campoIdPicture != 0){
            imagen.setImageBitmap(consultarImagen());
            campoIdPicture = 0;
        }
        atras.setVisibility(View.GONE);


        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                id_secuencia++;

                if (id_secuencia > 0) {
                    atras.setVisibility(View.VISIBLE);
                    atras.setEnabled(true);
                }

                texto = "";
                ejecutaSQL();
                muestraTabla();
                tv.setText(texto);
                if (campoIdPicture != 0) {
                    imagen.setImageBitmap(consultarImagen());
                    campoIdPicture = 0;
                }
                if (id_secuencia == numRows - 1) {
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

                if (id_secuencia == 0) {
                    atras.setVisibility(View.GONE);
                }

                texto = "";
                ejecutaSQL();
                muestraTabla();
                tv.setText(texto);
                if (campoIdPicture != 0) {
                    imagen.setImageBitmap(consultarImagen());
                    campoIdPicture = 0;
                }
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent22 = new Intent(Teoria_logica.this, Subtema_Logica.class);
                boolean loquesea = true;
                intent22.putExtra("id_subtema", id_subtema);
                startActivity(intent22);
            }
        });


    }

    private void ejecutaSQL() {
        cursor = db.rawQuery("SELECT * FROM BancoTextos WHERE SubTemas_id == " + id_subtema + " AND Secuencia == " + id_secuencia, null);
        /*cursor = db.rawQuery("SELECT * FROM BancoTextos WHERE SubTemas_id == " + id_subtema + "  AND Secuencia ==" + id_secuencia, null);*/
    }

    private void muestraTabla() {
        cursor.moveToFirst();
        String teoria = cursor.getString(3);
        texto = texto + "\n " + teoria;

        if (cursor.getInt(1) != 0){
            campoIdPicture = cursor.getInt(1);
        }
        cursor.moveToNext();
    }

    private Bitmap consultarImagen() {
        //Toast.makeText(Teoria_texto.this, "entrando en consultar imagene y el valor de campoIdPic es :" + campoIdPicture, Toast.LENGTH_SHORT).show();
        cursor3 = db.rawQuery("SELECT * FROM BancoImagenes WHERE _id == " + campoIdPicture, null);
        cursor3.moveToFirst();
        imagenData = cursor3.getBlob(2);
        int largo = imagenData.length;
        //Toast.makeText(Teoria_texto.this, "largo es de " + largo, Toast.LENGTH_SHORT).show();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imagenData);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    private void set_id_subtema(int id_subtema) {
        this.id_subtema = id_subtema;
    }

    private void regresarRows(){
        cursor2 = db.rawQuery("SELECT * FROM BancoTextos WHERE SubTemas_id == " + id_subtema, null);
        numRows = cursor2.getCount();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent subtema = new Intent(this,Subtema_Logica.class);
            startActivity(subtema);
        }
        return super.onKeyDown(keyCode, event);
    }
}