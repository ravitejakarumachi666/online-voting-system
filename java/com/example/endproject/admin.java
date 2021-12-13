package com.example.endproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class admin extends AppCompatActivity {
TextView tv1,tv2,tv3;
Button showdata,logout;
DBadmin db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        logout=findViewById(R.id.logout);
        showdata=findViewById(R.id.showdata);
        db=new DBadmin(this);

        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.fetchData();
                int[] res=new int[3];
                int i=0;
               while(cursor.moveToNext()){
                   res[i]=cursor.getInt(1);
                   i++;
               }
               tv1.setText("Candidate 1 = "+res[0]);
                tv2.setText("Candidate 2 = "+res[1]);
                tv3.setText("Candidate 3 = "+res[2]);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });


    }
}