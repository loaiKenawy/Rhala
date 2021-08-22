package com.traning.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.traning.R;
import com.traning.database.UserDatabase;
import com.traning.models.User;

public class Register extends AppCompatActivity {

    private User newUser;
    private TextView firstName, lastName, Phone, Email, Password;
    private Button Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName = findViewById(R.id.text_FisrtName);
        lastName = findViewById(R.id.text_LastName);
        Email = findViewById(R.id.text_Email);
        Password = findViewById(R.id.text_Password);
        Phone = findViewById(R.id.text_Phone);

        Register = findViewById(R.id.submit);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void register() {
        UserDatabase userDatabase = new UserDatabase(getApplicationContext());
        if (firstName == null) {
            firstName.setError("Field empty");
        } else if (lastName == null) {
            lastName.setError("Field empty");
        } else if (Phone == null) {
            Phone.setError("Field empty");
        } else if (Email == null) {
            Email.setError("Field empty");
        } else if (Password == null) {
            Password.setError("Field empty");
        } else if (Phone.getText().toString().length() > 11 || Phone.getText().toString().length() < 11) {
            Phone.setError("Invalid phone");
        } else {
            newUser = new User(Phone.getText().toString(), Email.getText().toString(),
                    firstName.getText().toString(), lastName.getText().toString(), Password.getText().toString());

            if (userDatabase.registerNewUser(newUser)) {
                Toast.makeText(getApplicationContext(), "Register successfully", Toast.LENGTH_LONG).show();
                Intent intent;
                intent = new Intent(Register.this, login.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                Intent intent;
                intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        }

    }
}