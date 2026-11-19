package com.example.secondactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditUser extends AppCompatActivity {

    private EditText etNewName, etNewPassword;
    private Button btnSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        etNewName = findViewById(R.id.etNewName);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Load existing saved name into the field
        String currentName = prefs.getString("user_name", "");
        etNewName.setText(currentName);

        btnSaveChanges.setOnClickListener(v -> {
            String updatedName = etNewName.getText().toString().trim();
            String updatedPassword = etNewPassword.getText().toString().trim();

            SharedPreferences.Editor editor = prefs.edit();

            if (!updatedName.isEmpty()) {
                editor.putString("user_name", updatedName);
            }

            if (!updatedPassword.isEmpty()) {
                if (updatedPassword.length() < 6) {
                    etNewPassword.setError("Password must be at least 6 characters");
                    return;
                }
                editor.putString("user_password", updatedPassword);
            }

            editor.apply();
            Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
            finish(); // Returns to whichever screen opened EditProfileActivity
        });
    }
}




