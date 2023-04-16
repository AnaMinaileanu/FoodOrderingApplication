package com.example.foodorderingapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.foodorderingapplication.MainActivity;
import com.example.foodorderingapplication.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Finding id's for each widget
        final EditText etEmail = (EditText) findViewById(R.id.email);
        final EditText etPassword = (EditText) findViewById(R.id.password);
        Button btnLogin = (Button) findViewById(R.id.loginButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting Text from user and converting to String
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

                String data = preferences.getString(email, null);
                String dataSplit[] = data.split(" ", 3);
                String savedUser = dataSplit[0];
                String savedEmail = dataSplit[1];
                String savedPassword = dataSplit[2];

                //Comparing email & password inserted by user while sign up and login
                //if same,navigate to home screen.If not,Display the message.
                if(email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "All fields need to be completed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(email.equals(savedEmail) && password.equals(savedPassword))
                    {
                        Intent displayScreen = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(displayScreen);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Please Enter Correct details", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

//    public void mainActivity(View view) {
//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//    }
}