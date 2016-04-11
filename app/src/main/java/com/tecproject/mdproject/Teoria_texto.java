package com.tecproject.mdproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Teoria_texto extends AppCompatActivity {
    int id=0;
    int id_subtema;
    private TextView tv;
    private Button next;
    private String texto = "";
    SQLiteDatabase db= null;
    Cursor cursor = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teoria_texto);

        tv = (TextView)findViewById(R.id.textView);
        next = (Button)findViewById(R.id.btnNext);

        db = this.openOrCreateDatabase("BaseDatos.sqlite", MODE_PRIVATE, null);

        //pertence al ejercicio de enviar datos entre actividades
        Intent intentar = getIntent(); //esto cacha lo que le estamos pasando en el activity 1
        Bundle bundle = intentar.getExtras();

        if (bundle !=null){
            int id_subtema_recogido = bundle.getInt("id_subtema");
            get_id_subtema(id_subtema_recogido);
        }


        View.OnClickListener listener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        };

        next.setOnClickListener(listener);
        ejecutaSQL(id);
        muestraTabla();
        db.close();
        tv.setText(texto);

    }

    private void ejecutaSQL(int id) {
        cursor = db.rawQuery("SELECT * FROM BancoTextos WHERE SubTemas_id == "+ id_subtema + "  AND Secuencia =="+ id, null);
    }
    private void muestraTabla() {
        cursor.moveToFirst();
        String titulo = cursor.getString(3);
        texto = texto + "\n " + titulo;
        cursor.moveToNext();
    }


    private void get_id_subtema(int id_subtema) {
        this.id_subtema = id_subtema;
    }

}
