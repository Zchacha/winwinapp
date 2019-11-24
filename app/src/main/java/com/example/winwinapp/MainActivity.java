package com.example.winwinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private Button bLoginLink,bRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bLoginLink = (Button) findViewById(R.id.bLoginLink);
        bRegisterLink = (Button) findViewById(R.id.bRegisterLink);
        bLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
        bRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });


        }
    public void openLogin(){
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }
    public void openRegister(){
        Intent intent = new Intent(this,expenses.class);
        startActivity(intent);
    }


}