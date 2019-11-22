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
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername =  (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button)  findViewById(R.id.bLogin);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user !=null){
            finish();
            startActivity(new Intent(login.this,Home.class));
        }

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(etUsername.getText().toString(),etPassword.getText().toString());
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

















        //method to show toast message
        private void showMessage (String text){

            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        }
    }


