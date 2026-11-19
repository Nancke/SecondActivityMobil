package com.example.secondactivity;

import static android.telephony.PhoneNumberUtils.formatNumber;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MoneyStuff extends AppCompatActivity {

    private SharedPreferences prefs;
    private static final String PREFS_NAME = "MoneyPrefs";
    private static final String KEY_ITEMS = "saved_money_items";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_stuff);

        prefs = getSharedPreferences("", MODE_PRIVATE);

        ImageView back = findViewById(R.id.goback);
        // forbinder knappen til sin id
        Button Send = findViewById(R.id.Send);
        Button Request = findViewById(R.id.Request);
        TextView SendResult = findViewById(R.id.ResultSend);//dont realy get  used anymore but it bugged if deleted so just keep
        TextView RequestResult = findViewById(R.id.ResultRequest); //dont realy get  used anymore but it bugged if deleted so just keep
        LinearLayout linearlayout = findViewById(R.id.Lineargroup);

        loadSavedEntries(linearlayout);

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MoneyStuff.this);
                builder.setTitle("Send");

                final EditText input = new EditText(MoneyStuff.this);
                input.setHint("Enter amount");
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                builder.setView(input);



                builder.setPositiveButton("Confirm", (dialog, which) -> {
                    String amount = input.getText().toString();
                    String entryText = "sent:" + amount + "kr";
                    addEntryView(linearlayout, entryText);
                    saveEntry(entryText);

                    // ændre på TextView for og vise det rigtige svar

                    showMoneyNotification("Money Sent", "You sent " + amount + " kr");
                    //faktisk viser hvor mange penge man requester med en notification
                    dialog.dismiss();
                });
                builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

                //pop-op
                builder.show();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoneyStuff.this, SecoundActivity.class);
                startActivity(intent);
            }

        });

        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MoneyStuff.this);
                builder.setTitle("Request");
                final EditText input = new EditText(MoneyStuff.this);
                input.setHint("Enter amount");
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                builder.setView(input);

                builder.setPositiveButton("Confirm", (dialog, which) -> {
                    String amount = input.getText().toString();
                    String entryText = "Requested:" + amount + "kr";
                    addEntryView(linearlayout, entryText);
                    saveEntry(entryText);

                    //ændre på TextView for og vise det rigtige svar

                    showMoneyNotification("Money Requested", "You requested " + amount + " kr");
                    //faktisk viser hvor mange penge man requester med en notification
                    dialog.dismiss();
                });
                builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());


                //pop-op
                builder.show();
            }

        });
    }
    private void addEntryView(LinearLayout linearlayout, String text) {
        TextView textView = new TextView(MoneyStuff.this);
        textView.setText(text);
        styletextview(textView);
        linearlayout.addView(textView);
    }

    private void saveEntry(String entryText) {
        String existing = prefs.getString(KEY_ITEMS, "MoneyTrack");
        String updated = existing.isEmpty() ? entryText : existing + ";;" + entryText;
        prefs.edit().putString(KEY_ITEMS, updated).apply();
    }

    private void loadSavedEntries(LinearLayout linearlayout) {
        linearlayout.removeAllViews();
        String saved = prefs.getString(KEY_ITEMS, "");
        if (!saved.isEmpty()) {
            String[] items = saved.split(";;");
            for (String item : items) {
                addEntryView(linearlayout, item);
            }
        }
    }
    private void showMoneyNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "messages")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify((int) System.currentTimeMillis(), builder.build());
        }
    }
    void styletextview(TextView view){
        view.setTextSize((float) 16);
        view.setPadding(0, 5, 0,5 );

    }



}