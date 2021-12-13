package com.example.endproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText voter_id,username;
ImageView image;
Button submit;
DBvoters DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voter_id=findViewById(R.id.voter_id);
        image=findViewById(R.id.image);
        submit=findViewById(R.id.submit);
        username=findViewById(R.id.username);
        DB = new DBvoters(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String votera= voter_id.getText().toString();
                int voter = Integer.valueOf(voter_id.getText().toString());
                if(user.indexOf("@gmail.com")==-1){
                    Toast.makeText(MainActivity.this,"please enter correct email",Toast.LENGTH_SHORT).show();
                }
                else if(votera.length()!=7){
                    Toast.makeText(MainActivity.this,"please enter correct voter id",Toast.LENGTH_SHORT).show();
                }
                else if(user.equals("")||votera.equals("")){
                    Toast.makeText(MainActivity.this,"please enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass=DB.checkusernameandvoter(user);
                    if(checkuserpass==false){
                        Toast.makeText(MainActivity.this,"verification successfull",Toast.LENGTH_SHORT).show();
                        Boolean insert = DB.insertvoterid(user,voter);
                        Intent intent = new Intent(getApplicationContext(),voting_panel.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"user already voted",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}