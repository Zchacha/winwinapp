
package com.example.winwinapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot extends AppCompatActivity {

    private EditText etEmail;
    private Button bForgot;
    private ProgressBar loadingProgress;
    private FirebaseAuth auth;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        etEmail = (EditText) findViewById(R.id.etEmail);
        bForgot = (Button) findViewById(R.id.bForgot);
        loadingProgress = findViewById(R.id.progressBar1);
        auth = FirebaseAuth.getInstance();
        loadingProgress.setVisibility(View.INVISIBLE);

        bForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                bForgot.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "กรุณากรอกอีเมล", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(forgot.this, "กรุณาตรวจสอบอีเมล", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(forgot.this, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show();
                                }
                                bForgot.setVisibility(View.VISIBLE);
                                loadingProgress.setVisibility((View.INVISIBLE));
                            }
                        });
            }
        });
    }

    public void NavigateSignUp(View v) {
        Intent inent = new Intent(this, login.class);
        startActivity(inent);
    }
}