package com.tecproject.mdproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button b1, b2, b3, b4, banterior, bsiguiente;

    TextView tv;
    String texto = "";
    SQLiteDatabase db= null;
    Cursor cursor = null;
    int contador =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);
        b1 =(Button)findViewById(R.id.bttema1);
        banterior =(Button)findViewById(R.id.bAnterior);
        bsiguiente=(Button)findViewById(R.id.bSiguiente);

        db = this.openOrCreateDatabase("BaseDatos.sqlite", MODE_PRIVATE, null);
        ejecutaSQL();
        muestraTabla();
        db.close();
        tv.append(texto);



        /*BaseDeDatos myDbHelper = new BaseDeDatos(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }*/


        //b1=(Button) findViewById(R.id.bttema1);
        b2=(Button) findViewById(R.id.button2);
        b3=(Button) findViewById(R.id.button3);
        b4=(Button) findViewById(R.id.button4);

        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this, Subtemas.class);
                startActivity(intent);

            }
        });


        banterior.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


            }
        });

        bsiguiente.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                contador++;


            }





        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }





    private void ejecutaSQL() {
        cursor = db.rawQuery("select * from BancoTextos", null);
    }

    private void muestraTabla() {


        //int numeroDeFilas = cursor.getCount();

        cursor.moveToFirst();
        //for (int i = 1; i <= numeroDeFilas; i++){
            int id = cursor.getInt(3);
            String titulo = cursor.getString(3);
            texto = texto + "\n " + id + ". " + titulo;
            cursor.moveToNext();
        //}
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
            return true;
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


}