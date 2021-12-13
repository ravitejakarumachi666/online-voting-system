package com.example.endproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText username,password;
    Button signup,signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        signin = (Button) findViewById(R.id.login_button);
        signup = (Button) findViewById(R.id.register_button);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),register.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("admin")&&pass.equals("admin")){
                    Intent intent = new Intent(getApplicationContext(),admin.class);
                    startActivity(intent);
                }
                else{
                    if(user.equals("")||pass.equals("")){
                        Toast.makeText(login.this,"please enter all the fields",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                        if(checkuserpass==true){
                            Toast.makeText(login.this,"sign in successfull",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(login.this,"Invalid credentials",Toast.LENGTH_SHORT).show();

                        }
                    }
                }

            }
        });

    }
}