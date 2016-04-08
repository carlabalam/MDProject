package com.tecproject.mdproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class Teoria_texto extends AppCompatActivity {
    TextView tv;
    Button next;
    String texto = "";
    SQLiteDatabase db= null;
    Cursor cursor = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teoria_texto);

        tv = (TextView)findViewById(R.id.textView);
        next = (Button)findViewById(R.id.btnNext);

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
