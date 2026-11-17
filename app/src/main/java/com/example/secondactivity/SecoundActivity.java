package com.example.secondactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
public class SecoundActivity extends AppCompatActivity{

    ImageView Settings;
    Button ContactAnna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound);

        Settings = findViewById(R.id.gotoSettings);
        ContactAnna = findViewById(R.id.AnnaContact);

        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecoundActivity.this, Settings.class);
                startActivity(intent);
            }


        });

        ContactAnna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecoundActivity.this, MoneyStuff.class);
                startActivity(intent);
            }
        });

    }



}
