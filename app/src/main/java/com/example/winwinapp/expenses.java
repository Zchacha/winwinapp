package com.example.winwinapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class expenses extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private ImageView pencil_edit;
    private TextView btBudget;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);







        tabLayout=(TabLayout) findViewById(R.id.expensetablayout_id);
        appBarLayout=(AppBarLayout) findViewById(R.id.expensebar_id);
        viewPager =(ViewPager) findViewById(R.id.expenseviewpager_id);
        expenses_Pageadapter adapter = new expenses_Pageadapter(getSupportFragmentManager());

        //adding Fragment
        adapter.AddFragement(new currentmonth(),"เดือนปัจจุบัน");
        adapter.AddFragement(new lastmonth(),"เดือนที่แล้ว");

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);





        /**
        pencil_edit = (ImageView) findViewById(R.id.pencil);
        pencil_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder pBuilder = new AlertDialog.Builder(expenses.this);
                View pView =getLayoutInflater().inflate(R.layout.alert_editbudget,null);

                pBuilder.setView(pView);
                AlertDialog dialog =pBuilder.create();
                dialog.show();
            }
        }); **/

    }



}
