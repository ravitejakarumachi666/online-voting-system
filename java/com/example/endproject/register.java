package com.example.endproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText username,password;
    Button signup,signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.register_email);
        password = (EditText) findViewById(R.id.register_password);
        signin = (Button) findViewById(R.id.login_button);
        signup = (Button) findViewById(R.id.register_button);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("")|| pass.equals("")){
                    Toast.makeText(register.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuser = DB.checkusername(user);
                    if(checkuser==false){
                        Boolean insert = DB.insertData(user,pass);
                        if(insert==true){
                            Toast.makeText(register.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(register.this,"Registration failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(register.this,"User already exists ! please sign in",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });
    }
}