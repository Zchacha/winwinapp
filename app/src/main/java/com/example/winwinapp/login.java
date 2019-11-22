package com.example.winwinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity  {

    private Button bLogin;
    private EditText etUsername,etPassword;
    private TextView txForgot;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername =  (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button)  findViewById(R.id.bLogin);
        txForgot = (TextView) findViewById(R.id.txForgot);
        txForgot.setPaintFlags(txForgot.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        txForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgot();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();


        if (user !=null ){
            finish();
            startActivity(new Intent(login.this,Home.class));
        }

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    validate(etUsername.getText().toString(), etPassword.getText().toString());
                }
            }
        });

    }
    private  void validate(String userName,String userPassword){

        mAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    showMessage("เข้าสู่ระบบเรียบร้อย");
                    startActivity(new Intent(login.this,Home.class));
                }else {

                    showMessage("กรุณาเข้าระบบอีกครั้ง");

                    }
                }

        });
    }
    public void openForgot(){
        Intent intent = new Intent(this,forgot.class);
        startActivity(intent);
    }

    private Boolean validate() {
        Boolean result = false;

        String email = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty()) {

            showMessage("กรุณากรอกอีเมล");

        } else if (password.isEmpty()) {

            showMessage("กรุณารหัสผ่าน");

        } else if (email.isEmpty() && password.isEmpty()) {

            showMessage("กรุณากรอกข้อมูล");
        }else{
            result = true;
        }

        return result;
    }

        //method to show toast message
        private void showMessage (String text){

            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        }
    }


