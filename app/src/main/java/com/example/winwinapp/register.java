package com.example.winwinapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class register extends AppCompatActivity  {

    private Button bRegister;
    private EditText etFirstname,etLastname,etEmail,etUsername,etPassword,etConfirmPassword;
    String email,firstname,lastname,username,password,confirm;
    private ProgressBar loadingProgress;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        //view
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etFirstname = findViewById(R.id.etFirstname);
        etLastname = findViewById(R.id.etLastname);
        etUsername = findViewById(R.id.etUsername);
        loadingProgress = findViewById(R.id.progressBar1);
        bRegister = findViewById(R.id.bRegister);
        loadingProgress.setVisibility(View.INVISIBLE);

        mAuth=FirebaseAuth.getInstance();


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bRegister.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                final String confirm = etConfirmPassword.getText().toString();
                final String firstname = etFirstname.getText().toString();
                final String lastname = etLastname.getText().toString();
                final String username = etUsername.getText().toString();

                if (validate()){
                    //everything is ok and all field are filled now we can start creating user account
                    //CreateUserAccount method will try to create the user if the email is valid

                    CreateUserAccount(email,password,confirm,firstname,lastname,username);

                }else {
                    //something goes wrong : all fields must be filled
                    //we need to display an error message
                    bRegister.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility((View.INVISIBLE));
                }

            }
        });

    }

    private void CreateUserAccount(final String email, final String password, String confirm, final String firstname, final String lastname, final String username) {

        //this method create user account with specific email and password
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    sendEmailVerification();
                    //user account created successfully
                    showMessage("กรุณายืนยันตัวตนที่ "+email);
                    //after we created user account we need to update his profile name
                    updateUserInfo(firstname,lastname,email,password,username,mAuth.getCurrentUser());
                    startActivity(new Intent(register.this, MainActivity.class));
                }
                else {

                    //account creation failed
                    showMessage("สร้างไม่สำเร็จ" + task.getException().getMessage());
                    bRegister.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
    //update user name
    private void updateUserInfo(String firstname, String lastname, String email, String password, String username, FirebaseUser currentUser) {

        //fist we need to upload user
        sendData();
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

        } else if ( email.isEmpty() && password.isEmpty() && confirm.isEmpty() &&
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
    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.bRegister).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        findViewById(R.id.bRegister).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(register.this,
                                    "กรุณายืนยันตัวตนที่ " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(register.this,
                                    "ไม่สามารถยืนยันตัวตนได้",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }
}


