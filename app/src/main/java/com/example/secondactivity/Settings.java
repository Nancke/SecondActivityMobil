package com.example.secondactivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    ImageView Settings;

    private SwitchCompat switchNotifications;

    // Register permission launcher for Android 13+
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                // Update switch position based on user choice in the pop-up
                switchNotifications.setChecked(isGranted);
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchNotifications = findViewById(R.id.switchNotifications);

        OnUserEdit();
        // 1. Set initial state of the switch based on current permission
        updateSwitchState();

        // 2. Handle switch toggling
        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Prevent triggering code programmatically when updating switch state
            if (!buttonView.isPressed()) return;

            if (isChecked) {
                // User wants to turn notifications ON
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Request permission pop-up
                        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                    }
                }
            } else {
                // User wants to turn notifications OFF.
                // Android doesn't allow apps to revoke runtime permissions programmatically,
                // so we send them to the app's system settings page to toggle it off.
                Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        });
        Settings = findViewById(R.id.goback);
        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, SecoundActivity.class);
                startActivity(intent);
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh switch state if the user returns from system settings
        updateSwitchState();
    }

    private void updateSwitchState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            boolean hasPermission = ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
            switchNotifications.setChecked(hasPermission);
        } else {
            // Below Android 13, notifications are enabled by default unless blocked globally
            switchNotifications.setChecked(true);
        }
    }
    private void OnUserEdit()
    {

        Button editUser=findViewById(R.id.EditUser);

        editUser.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.this, EditUser.class);
            startActivity(intent);
        });
    }
}

