package com.example.winwinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity implements View.OnClickListener {

    private Button bLogin;
    private TextView etForgot;
    EditText etUsername,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etForgot = (TextView) findViewById(R.id.etForgot);
        etForgot = (TextView) findViewById(R.id.etForgot);
        bLogin = (Button) findViewById(R.id.bLogin);

        etForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgot();
            }
        });
        bLogin.setOnClickListener(this);
        etForgot.setPaintFlags(etForgot.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bLogin:
                Intent intent = new Intent(this,Home.class);
                startActivity(intent);

        }
    }
    public void openForgot(){
        Intent intent = new Intent(this,forgot.class);
        startActivity(intent);
    }


}
