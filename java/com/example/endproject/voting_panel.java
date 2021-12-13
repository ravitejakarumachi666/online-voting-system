package com.example.endproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class voting_panel extends AppCompatActivity {
Button one,two,three;
DBadmin db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_panel);

        one = findViewById(R.id.submit1);
        two= findViewById(R.id.submit2);
        three=findViewById(R.id.submit3);
        db=new DBadmin(this);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updatevotes("candidate1");
                Intent intent = new Intent(getApplicationContext(),finalPage.class);
                startActivity(intent);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updatevotes("candidate2");
                Intent intent = new Intent(getApplicationContext(),finalPage.class);
                startActivity(intent);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updatevotes("candidate3");
                Intent intent = new Intent(getApplicationContext(),finalPage.class);
                startActivity(intent);
            }
        });
    }
}