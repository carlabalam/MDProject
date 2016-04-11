package com.tecproject.mdproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Subtemas extends AppCompatActivity implements View.OnClickListener {
    Button botonS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtemas);
        botonS = (Button)findViewById(R.id.button6);

        botonS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button6: //boton para llamar otra actividad
                Intent intent= new Intent(this,Teoria_texto.class);
                int  id_subtema = 2;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            case R.id.bSiguiente: //boton para llamar otra actividad
                Intent intent2= new Intent(this,Teoria_texto.class);
                startActivity(intent2);
                break;
            default:
                break;
        }

    }

}
