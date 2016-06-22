package com.tecproject.mdproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Subtemas extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{
    Button btnSistDecimal, btnHexaDecimal, btnBinario;
    int  id_subtema;

    Integer recibido;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Bloquea la orientación en vertical, LANDSCAPE es horizontal
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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



        /************************************
         * *********************************
         * **********************************
         * LO DESHABILITE PORQUE NO ME DEJA AVANZAR
        * Recoge lo enviado desde Teoria_sistemas.java
        * que permite ir activando los subtemas
        */
        intent = getIntent();
        Bundle bundle = intent.getExtras();


        if (bundle != null) {
            recibido = bundle.getInt("id_subtema");
       //     Toast.makeText(Subtemas.this, "veamos que se mando: " + recibido, Toast.LENGTH_SHORT).show();
            switch (recibido){
                case 1: btnHexaDecimal.setEnabled(true);
                    break;
                case 2: btnBinario.setEnabled(true);
                        btnHexaDecimal.setEnabled(true);
                    break;
                case 3:btnHexaDecimal.setEnabled(true);
                    btnBinario.setEnabled(true);
                    break;
            }

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bSistNums: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_sistemas.class);
                id_subtema = 1;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;

            case R.id.bHexaDecimal: //boton para llamar otra activida
                intent= new Intent(this,Teoria_sistemas.class);
                id_subtema = 2;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            case R.id.bBinario: //boton para llamar otra actividad
                intent = new Intent(this,Teoria_sistemas.class);
                id_subtema = 3;
                intent.putExtra("id_subtema", id_subtema);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_examen) {
            Intent intent= new Intent(this, Examen.class);
            startActivity(intent);

        } else if (id == R.id.nav_ejercicios) {
            Intent intent= new Intent(this, Ejercicio.class);
            startActivity(intent);

        } else if (id == R.id.nav_estudiar) {
            Intent intent= new Intent(this, Home.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent tema = new Intent(this, Home.class);
            startActivity(tema);
        }
        return super.onKeyDown(keyCode, event);
    }
}
