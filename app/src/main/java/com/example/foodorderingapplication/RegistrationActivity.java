package com.example.foodorderingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText userName = (EditText) findViewById(R.id.newName);
        final EditText password = (EditText) findViewById(R.id.newPassword);
        final EditText email = (EditText) findViewById(R.id.newEmail);
        Button btnRegister = (Button) findViewById(R.id.newRegisterButton);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
                String newUser  = userName.getText().toString();
                String newPassword = password.getText().toString();
                String newEmail = email.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();

                if(newUser.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty())
                {
                    Toast.makeText(RegistrationActivity.this, "All fields need to be completed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (preferences.getString(newEmail, null) == null)
                    {
                        String regex = "/^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$/g";
                        Pattern pattern = Pattern.compile(regex);
                        if(pattern.matcher(newEmail).matches())
                        {
                            editor.putString(newEmail,  newUser + " " + newEmail + " " + newPassword);
                            editor.commit();
                            Intent displayScreen = new Intent(RegistrationActivity.this, MainActivity.class);
                            startActivity(displayScreen);
                        }
                        else
                        {
                            Toast.makeText(RegistrationActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else
                    {
                        Toast.makeText(RegistrationActivity.this, "Email has already benn taken", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void login(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

//    public void mainActivity(View view) {
//        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
//    }
}