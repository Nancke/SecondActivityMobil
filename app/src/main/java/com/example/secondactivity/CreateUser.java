package com.example.secondactivity;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateUser extends AppCompatActivity {

    private EditText etNavn, enMail, etPassword;
    private Button RegisterBruger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user); //måden du linker xml til koden, bruger du ikke den fucker det lidt

        etNavn=findViewById(R.id.etName); //link Det til hvad det er. skal gøres med alle komponenter
        enMail=findViewById(R.id.enMail);
        etPassword=findViewById(R.id.etPassword);
        RegisterBruger=findViewById(R.id.Register);

        RegisterBruger.setOnClickListener(v ->
        {
            String name = etNavn.getText().toString().trim();
            String email = enMail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                etNavn.setError("Name is required");
                return;
            }
            if (TextUtils.isEmpty(email)) {
                enMail.setError("Email is required");
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("Password must be at least 6 characters");
                return;
            }

            // Input is valid: Send data to database or backend auth service
            registerUser(name, email, password);
        });
    }
    private void registerUser(String name, String email, String password) {

        //aparently realy easy with the use of SharedPrefences. just take there input from earlyer and actuly saves it
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("user_name", name);
        editor.putString("user_email", email);
        editor.putString("user_password", password);
        editor.putBoolean("is_logged_in", true);
        editor.apply();
        Intent intent = new Intent(CreateUser.this, SecoundActivity.class);
        startActivity(intent);



    }
}
