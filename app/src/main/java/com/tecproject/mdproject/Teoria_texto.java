package com.tecproject.mdproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Teoria_texto extends AppCompatActivity {
    TextView tv;
    String texto = "";
    SQLiteDatabase db= null;
    Cursor cursor = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teoria_texto);

        tv = (TextView)findViewById(R.id.textView);

        db = this.openOrCreateDatabase("BaseDatos.sqlite", MODE_PRIVATE, null);

        ejecutaSQL();
        muestraTabla();
        db.close();
        tv.append(texto);
    }
    private void ejecutaSQL() {
        cursor = db.rawQuery("SELECT * FROM BancoTextos  ", null);
    }
    private void muestraTabla() {

        cursor.moveToFirst();
        int id = cursor.getInt(3);
        String titulo = cursor.getString(3);
        texto = texto + "\n " + id + ". " + titulo;
        cursor.moveToNext();
    }



}
