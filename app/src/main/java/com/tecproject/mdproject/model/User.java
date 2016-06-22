package com.tecproject.mdproject.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.tecproject.mdproject.bd.Helper;

public class User {
    private Helper mHelper;
    private SQLiteDatabase mDb;
    private Context mContext;

    public User(Context context){
        mContext = context;
        mHelper = new Helper(mContext);
    }

    //open
    public void open() throws SQLiteException {
        mDb = mHelper.getReadableDatabase();

    }

    //close
    public void close(){

    }


    public String usuario, contrasenia;
    //public boolean OnCreate(SQLiteDatabase db){

    public void obtenerDatosUsuario(){
            SQLiteOpenHelper dbHelper=null;
           // db = dbHelper.getReadableDatabase();

        String consulta = "SELECT * FROM Usuario WHERE NumControl " + "=? AND Contrasena =?";

        //Cursor cursor = db.rawQuery(consulta, new String[] { String.valueOf(numcontrol), String.valueOf(contrasena) });
        //cursor.close();
       // db.close();

        //return  cursor.moveToFirst();
    }
}

