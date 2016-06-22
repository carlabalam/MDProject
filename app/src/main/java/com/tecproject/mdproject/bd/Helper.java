package com.tecproject.mdproject.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Helper extends SQLiteAssetHelper {


    SQLiteDatabase db = null;

    private static final String DB_NAME = "BaseDatos.sqlite";
    private static final int DB_VERSION = 1;


    public Helper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }
}


