package com.example.winwinapp;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView etprofile;
    private Button logout;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView txUsername,txFirstname,txLastname,txEail;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
      // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       //NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
       //NavigationUI.setupWithNavController(navigationView, navController);

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button logout = (Button) findViewById(R.id.logout);
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });

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
    }

    public void basicReadWrite() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        // [END read_message]
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    //@Override
    //public boolean onSupportNavigateUp() {
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //return NavigationUI.navigateUp(navController, mAppBarConfiguration)
          //      || super.onSupportNavigateUp();
    //}

    public void openEditprofile(){
        TextView etprofile = (TextView) findViewById(R.id.etprofile);
        Intent intent = new Intent(Home.this,editprofile.class);
        startActivity(intent);
    }
    public  void profile(){
        auth = FirebaseAuth.getInstance();
        txEail = (TextView) findViewById(R.id.textView4_1);
        txFirstname = (TextView) findViewById(R.id.textText2_1);
        txLastname = (TextView) findViewById(R.id.textView3_1);
        txUsername = (TextView) findViewById(R.id.textText1_1);
        user = auth.getCurrentUser();

        txEail.setText(user.getEmail());

        reference = FirebaseDatabase.getInstance().getReference().child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue().toString();
                String firstname = dataSnapshot.child("fistname").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}