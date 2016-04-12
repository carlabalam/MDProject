package com.tecproject.mdproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Subtemas extends AppCompatActivity implements View.OnClickListener {
    Button btnSistDecimal, btnHexaDecimal, btnBinario;
    int  id_subtema;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtemas);
        btnSistDecimal = (Button)findViewById(R.id.bSistNums);
        btnHexaDecimal = (Button)findViewById(R.id.bHexaDecimal);
        btnBinario = (Button)findViewById(R.id.bBinario);

        btnSistDecimal.setOnClickListener(this);
        btnHexaDecimal.setOnClickListener(this);
        btnBinario.setOnClickListener(this);
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

            case R.id.bHexaDecimal: //boton para llamar otra actividad
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

}
