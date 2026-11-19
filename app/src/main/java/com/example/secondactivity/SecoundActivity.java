package com.example.secondactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

public class SecoundActivity extends AppCompatActivity{

    public static String[] group1;

    boolean[] checkedItems;


    private LinearLayout linearLayout;

    String[] members = {"Anna", "Peter", "John", "Mike", "michael"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound);
        //moved these down becasue it seem to crash if they are placed before OnCreate
        ImageView Settings = findViewById(R.id.gotoSettings);

        Button ContactAnna = findViewById(R.id.AnnaContact);

        linearLayout = findViewById(R.id.grouplist);

        Button create = findViewById(R.id.button3);

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

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SecoundActivity.this);
                builder.setTitle("Choose members for group");

// Add a checkbox list
                builder.setMultiChoiceItems(members, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedItems[which] = !checkedItems[which];
                    }
                });

// Add OK and Cancel buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i < checkedItems.length; i++){
                            if(checkedItems[i]) { group1[i] = members[i]; }
                        }
                        makeview();
                    }
                });
                builder.setNegativeButton("Cancel", null);

// Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    void makeview(){
        Button button = new Button(SecoundActivity.this);
        button.setText("Group1");
        button.setTextSize((float) 30);
        button.setHeight(75);

        linearLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecoundActivity.this, MoneyStuff.class);
                startActivity(intent);
            }
        });
    }







}
