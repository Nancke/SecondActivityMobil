package com.example.secondactivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MoneyStuff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_stuff);

        // forbinder knappen til sin id
        Button Send = findViewById(R.id.Send);
        Button Request = findViewById(R.id.Request);
        TextView SendResult = findViewById(R.id.ResultSend);
        TextView RequestResult = findViewById(R.id.ResultRequest);
        LinearLayout linearlayout = findViewById(R.id.Lineargroup);

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
                    TextView textView = new TextView(MoneyStuff.this);
                    textView.setText("sent:" + amount + "kr");
                    styletextview(textView);

                    
                    linearlayout.addView(textView);
                    // ændre på TextView for og vise det rigtige svar
                    dialog.dismiss();
                });
                builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

                //pop-op
                builder.show();
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
                    TextView textView = new TextView(MoneyStuff.this);
                    textView.setText("Requested:" + amount + "kr");
                    styletextview(textView);


                    linearlayout.addView(textView);
                    // ændre på TextView for og vise det rigtige svar
                    dialog.dismiss();
                });
                builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

                //pop-op
                builder.show();
            }
        });
    }

    void styletextview(TextView view){
        view.setTextSize((float) 16);
        view.setPadding(0, 5, 0,5 );

    }




}