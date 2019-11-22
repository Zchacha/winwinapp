package com.example.winwinapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import  com.example.winwinapp.User;


public class register extends AppCompatActivity  {

    private Button bRegister;
    private EditText etFirstname,etLastname,etEmail,etUsername,etPassword,etConfirmPassword;
    private FirebaseAuth firebaseAuth;
    String email,firstname,lastname,username,password,confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
                if(validate() && User != null){

                    //Upload data to the database
                    String user_email = etEmail.getText().toString().trim();
                    String user_password = etPassword.getText().toString().trim();


                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendData();
                                showMessage("ลงทะเบียนสำเร็จ");
                                startActivity(new Intent(register.this, Home.class));
                            }else{
                                showMessage("อีเมลนี้มีผู้ใช้งานแล้ว");
                            }

                        }
                    });
                }
            }
        });
    }

    private void setupUIViews(){
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etEmail = (EditText)findViewById(R.id.etEmail);
        bRegister = (Button)findViewById(R.id.bRegister);
        etConfirmPassword = (EditText)findViewById(R.id.etConfirmPassword);
        etFirstname = (EditText) findViewById(R.id.etFirstname);
        etLastname = (EditText)findViewById(R.id.etLastname);
    }

    private Boolean validate(){
        Boolean result = false;

        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        email = etEmail.getText().toString();
        firstname = etFirstname.getText().toString();
        lastname = etLastname.getText().toString();
        confirm = etConfirmPassword.getText().toString();


        if( email.isEmpty() ){

            showMessage("กรุณากรอกอีเมล");

        } else if( password.isEmpty() ){

            showMessage("กรุณากรอกรหัสผ่าน");

        }else if ( confirm.isEmpty()){

            showMessage("กรุณายืนยันรหัสผ่าน");

        }else if ( firstname.isEmpty() ){

            showMessage("กรุณากรอกชื่อจริง");

        }else  if ( lastname.isEmpty() ){

            showMessage("กรุณากรอกนามสกุล");

        }else if ( username.isEmpty() ){

            showMessage("กรุณากรอกชื่อผู้ใช้");

        }else if ( email.isEmpty() && password.isEmpty() && confirm.isEmpty() &&
                firstname.isEmpty() && lastname.isEmpty() && username.isEmpty()){

            showMessage("กรุณากรอกข้อมูล");
        }else{
            result = true;
        }

        return result;
    }


    private  void sendData() {
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mUsersRef = mRootRef.child("users");
        User userdata = new User(firstname,lastname,username,email,password);
        mUsersRef.push().setValue(userdata);
    }
    //method to show toast message
    private void showMessage (String text){

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}


