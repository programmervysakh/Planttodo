package com.example.plantcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button mLoginBtn;
    EditText mUserNameText,mPasswordText;
    TextView mEmail,mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginBtn = findViewById(R.id.loginButton);
        mUserNameText = findViewById(R.id.userNameText);
        mPasswordText = findViewById(R.id.passwordText);

        mUserNameText.requestFocus();


//        if(mUserNameText.getText().toString().trim().equals("")){
//            mEmail.setBackgroundColor(Color.parseColor("#ffff"));
//        }
//        else{
//            mEmail.setBackgroundColor(Color.parseColor("#00994D"));
//
//        }



        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Log.i("USER",mUserNameText.getText().toString());
                if(mUserNameText.getText().toString().equals("test@planttodo.com") && mPasswordText.getText().toString().equals("123456")){

                    Intent homeIntent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(homeIntent);
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                }

                else{

                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}