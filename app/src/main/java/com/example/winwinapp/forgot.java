package com.example.winwinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class forgot extends AppCompatActivity implements View.OnClickListener {
    private Button bForgot;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        etEmail = (EditText) findViewById(R.id.etEmail);
        bForgot = (Button) findViewById(R.id.bForgot);

        bForgot.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bForgot:
                Toast.makeText(getApplicationContext(),"ได้ส่งรหัสผ่านใหม่ไปที่อีเมลแล้ว",Toast.LENGTH_LONG).show();
        }
    }
}
