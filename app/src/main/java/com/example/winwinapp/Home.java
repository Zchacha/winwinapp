package com.example.winwinapp;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView etprofile;
    private  Button pinn;
    private TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       forgot=(TextView) findViewById(R.id.etForgot);
       forgot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               openExpense();
           }
       });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        etprofile = (TextView) findViewById(R.id.etprofile);
        etprofile.setPaintFlags(etprofile.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        etprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView etprofile = (TextView) findViewById(R.id.etprofile);
                Intent intent = new Intent(Home.this, editprofile.class);
                startActivity(intent);
            }
        });

        pinn =(Button) findViewById(R.id.pin);
        pinn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openExpense();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void openEditprofile(){

        Intent intent = new Intent(this,editprofile.class);
        startActivity(intent);
    }

    public void openExpense() {
        Intent intent = new Intent (this,expenses.class);
        startActivity(intent);
    }

}
