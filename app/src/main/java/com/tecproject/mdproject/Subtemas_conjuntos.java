package com.tecproject.mdproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Subtemas_conjuntos extends AppCompatActivity implements View.OnClickListener{

    int  id_subtema;
    int cont;

    Integer recibido;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtemas_conjuntos);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Bloquea la orientación en vertical, LANDSCAPE es horizontal
        //pertence al ejercicio de enviar datos entre actividades
        Intent intentar = getIntent(); //esto cacha lo que le estamos pasando en el activity 1
        Bundle bundle = intentar.getExtras();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button b1 = (Button) findViewById(R.id.intro);
        Button b2 = (Button) findViewById(R.id.conjuntouniverso);
        Button b3 = (Button) findViewById(R.id.complemento);
        Button b4 = (Button) findViewById(R.id.conjvacio);
        Button b5 = (Button) findViewById(R.id.numnaturales);
        Button b6 = (Button) findViewById(R.id.diagvenn);
        Button b7 = (Button) findViewById(R.id.subconjuntos);
        Button b8 = (Button) findViewById(R.id.potencia);
        Button b9 = (Button) findViewById(R.id.operaciones);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);

        if (bundle != null) {
            //recibido = bundle.getBoolean("loquesea");
            recibido = bundle.getInt("id_subtema");
            switch (recibido){
                case 4: b2.setEnabled(true);
                    break;
                case 5: b2.setEnabled(true);
                        b3.setEnabled(true);
                    break;
                case 6: b2.setEnabled(true);
                        b3.setEnabled(true);
                        b4.setEnabled(true);
                    break;
                case 7: b2.setEnabled(true);
                        b3.setEnabled(true);
                        b4.setEnabled(true);
                        b5.setEnabled(true);

                        break;
                case 8: b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    b5.setEnabled(true);
                    b6.setEnabled(true);

                    break;
                case 9: b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    b5.setEnabled(true);
                    b6.setEnabled(true);
                    b7.setEnabled(true);

                    break;
                case 10: b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    b5.setEnabled(true);
                    b6.setEnabled(true);
                    b7.setEnabled(true);
                    b8.setEnabled(true);

                    break;
                case 11: b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    b5.setEnabled(true);
                    b6.setEnabled(true);
                    b7.setEnabled(true);
                    b8.setEnabled(true);
                    b9.setEnabled(true);

                    break;
                case 12: b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    b5.setEnabled(true);
                    b6.setEnabled(true);
                    b7.setEnabled(true);
                    b8.setEnabled(true);
                    b9.setEnabled(true);
                    break;

            }

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.intro: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_conjuntos.class);
                id_subtema = 4;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;

            case R.id.conjuntouniverso: //boton para llamar otra activida
                intent= new Intent(this,Teoria_conjuntos.class);
                id_subtema = 5;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            case R.id.complemento: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_conjuntos.class);
                id_subtema = 6;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            case R.id.conjvacio: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_conjuntos.class);
                id_subtema = 7;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            case R.id.numnaturales: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_conjuntos.class);
                id_subtema = 8;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            case R.id.diagvenn: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_conjuntos.class);
                id_subtema = 9;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            case R.id.subconjuntos: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_conjuntos.class);
                id_subtema = 10;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            case R.id.potencia: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_conjuntos.class);
                id_subtema = 11;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            case R.id.operaciones: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_conjuntos.class);
                id_subtema = 12;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
