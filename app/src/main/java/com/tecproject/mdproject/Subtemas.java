package com.tecproject.mdproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Subtemas extends AppCompatActivity implements View.OnClickListener {
    Button btnSistDecimal, btnHexaDecimal, btnBinario;
    int  id_subtema;
    int cont;

    Integer recibido;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pertence al ejercicio de enviar datos entre actividades
        Intent intentar = getIntent(); //esto cacha lo que le estamos pasando en el activity 1
        Bundle bundle = intentar.getExtras();

        setContentView(R.layout.activity_subtemas);
        btnSistDecimal = (Button)findViewById(R.id.bSistNums);
        btnHexaDecimal = (Button)findViewById(R.id.bHexaDecimal);
        btnBinario = (Button)findViewById(R.id.bBinario);

        btnSistDecimal.setOnClickListener(this);
        btnHexaDecimal.setOnClickListener(this);
        btnBinario.setOnClickListener(this);
        //Creación del botón de atrás (icono)
        //android.support.v7.app.ActionBar actionBar= getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        if (bundle != null) {
            //recibido = bundle.getBoolean("loquesea");
            recibido = bundle.getInt("id_subtema");
            switch (recibido){
                case 1: btnHexaDecimal.setEnabled(true);
                    break;
                case 2: btnBinario.setEnabled(true);
                    btnHexaDecimal.setEnabled(true);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bSistNums: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_texto.class);
                id_subtema = 1;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;

            case R.id.bHexaDecimal: //boton para llamar otra activida
                intent= new Intent(this,Teoria_texto.class);
                id_subtema = 2;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            case R.id.bBinario: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_texto.class);
                id_subtema = 3;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
//Método que controla la acción despues de pulsar el botón de atrás del celular
   public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //event.setSource(0); //Deshabilita el botón
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //Botón atrás
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }


}
