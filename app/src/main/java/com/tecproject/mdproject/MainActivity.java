package com.tecproject.mdproject;

import android.content.Intent;
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
import android.widget.Toast;

import com.tecproject.mdproject.bd.Helper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button b1, b2, b3, b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 =(Button)findViewById(R.id.bSistNumericos);
        b2=(Button) findViewById(R.id.bConjuntos);
        b3=(Button) findViewById(R.id.bLogica);
        b4=(Button) findViewById(R.id.bExamen);

        Helper mydb = new Helper(getApplicationContext());

       b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this, Subtemas.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                /*Intent intent= new Intent(MainActivity.this, Subtemas_Conjuntos.class);
                startActivity(intent);*/

            }
        });

        b3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                /*Intent intent= new Intent(MainActivity.this, Subtemas_Logica.class);
                startActivity(intent);*/

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //noinspection deprecation
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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

        } else if (id == R.id.nav_estadisticas) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Método que controla la acción despues de pulsar el botón de atrás del celular
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //event.setSource(0); //Deshabilita el botón
            Toast.makeText(this,"Saliendo de la aplicacion", Toast.LENGTH_SHORT).show();
           finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}