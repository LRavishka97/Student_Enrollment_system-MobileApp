package com.test.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText etName, etEmail, etUsername, etPassword;
    private Button signupButton;
     private  TextView textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        databaseHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etUsername = findViewById(R.id.uname);
        etPassword = findViewById(R.id.pw);

        signupButton = findViewById(R.id.btnsignup);
        textLogin =findViewById(R.id.txtLogin);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertUser();
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the SignupActivity
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

        private void insertUser(){
            // Get user details
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            // Insert user into database
            databaseHelper.insertUser(name, email, username, password);

            //  success message
            Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show();
        }









}