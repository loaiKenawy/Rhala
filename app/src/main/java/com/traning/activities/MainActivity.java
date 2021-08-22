package com.traning.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.traning.R;


public class MainActivity extends AppCompatActivity {

    private Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.logIn);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister();
            }
        });


    }
    private void toLogin(){

        Intent intent;
        intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }

    private void toRegister(){

        Intent intent;
        intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }

}


