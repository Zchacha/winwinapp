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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class register extends AppCompatActivity  {

    private Button bRegister;
    private EditText etFirstname,etLastname,etEmail,etUsername,etPassword,etConfirmPassword;
    private FirebaseAuth firebaseAuth;
    String email,firstname,lastname,username,password,confirm;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getReference();


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String user_email = etEmail.getText().toString().trim();
                    String user_password = etPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //sendEmailVerification();
                                sendUserData();
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


    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        showMessage("ลงทะเบียนสำเร็จกรุณาตรวจสอบอีเมล");
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(register.this, Home.class));
                    }else{

                        showMessage("ไม่สามารถส่งอีเมลยืนยันตัวตนได้");

                    }
                }
            });
        }
    }

    private void sendUserData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        User userProfile = new User(firstname,lastname,username,email);
        ref = database.getReference().child("userProfile");
        ref.setValue(userProfile);

    }

    //method to show toast message
    private void showMessage (String text){

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}


