package com.tecproject.mdproject;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tecproject.mdproject.R;

public class OpciMultText extends AppCompatActivity {
    SQLiteDatabase db = null;
    private int id_subtema;
    private TextView instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opci_mult_text);

        /*//pertence al ejercicio de enviar datos entre actividades
        Intent intentar = getIntent(); //esto cacha lo que le estamos pasando en el activity 1
        Bundle bundle = intentar.getExtras();

        if (bundle != null) {
            int id_subtema_recogido = bundle.getInt("id_subtema");
            set_id_subtema(id_subtema_recogido);
        }*/

        db = openOrCreateDatabase("BaseDatos.sqlite", MODE_PRIVATE, null);
    }

    public void set_id_subtema(int id_subtema) {
        this.id_subtema = id_subtema;
    }
}
