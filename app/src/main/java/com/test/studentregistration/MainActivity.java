package com.test.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    EditText username;
    EditText password;
    Button loginbutton;
    TextView txtSingup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        username = findViewById(R.id.uname);
        password = findViewById(R.id.pw);
        loginbutton = findViewById(R.id.btnlog);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();

                if (validateLogin(enteredUsername, enteredPassword)) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    // Proceed to the DashboardActivity
                    Intent intent = new Intent(MainActivity.this,dashbordActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Finish the current activity to prevent going back to it with the back button
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

         txtSingup = findViewById(R.id.txtSingup);
        txtSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the SignupActivity
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean validateLogin(String enteredUsername, String enteredPassword) {
        return databaseHelper.validateLogin(enteredUsername, enteredPassword);
    }

}