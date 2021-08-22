package com.traning.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.traning.R;
import com.traning.database.UserDatabase;
import com.traning.models.User;

public class login extends AppCompatActivity {

    private static final String TAG = "login";
    private TextView phone, Password;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.phone_login);
        Password = findViewById(R.id.password_login);
        Login = findViewById(R.id.login_submit);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        UserDatabase database = new UserDatabase(getApplicationContext());

        if (phone == null) {
            phone.setError("Field empty");
        } else if (Password == null) {
            Password.setError("Field empty");
        } else {

            User userData = database.login(phone.getText().toString());

            if (userData != null) {

                if (Password.getText().toString().equals(userData.getPassword())) {
                    Intent intent;
                    intent = new Intent(login.this, homeActivity.class);
                    intent.putExtra(getString(R.string.fName),userData.getFirstName());
                    intent.putExtra(getString(R.string.lName),userData.getLastName());
                    intent.putExtra(getString(R.string.email),userData.getEmail());
                    intent.putExtra(getString(R.string.phone),userData.getPhone());
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Invalid phone", Toast.LENGTH_LONG).show();
            }

        }
    }
}