package com.tecproject.mdproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Bloquea la orientación en vertical, LANDSCAPE es horizontal
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