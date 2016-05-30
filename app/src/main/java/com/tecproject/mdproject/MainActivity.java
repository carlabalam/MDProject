package com.tecproject.mdproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;



public class MainActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button b1=(Button) findViewById(R.id.btlogin);


        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String usuario= ((AutoCompleteTextView) findViewById(R.id.email)).getText().toString();
                String password= ((EditText) findViewById(R.id.password)).getText().toString();

                if (usuario.equals("admin")&& password.equals("admin")){

                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);

                } else{
                    Toast.makeText(getApplication(), "Usuario Incorecto", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }






}