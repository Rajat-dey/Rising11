package com.line.rising11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class How_To_Play_Activity extends AppCompatActivity {

    RelativeLayout introduction,create_team,manage_team,points,acc_balance,faqs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how__to__play_);
        setTitle("HOW TO PLAY");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        introduction=findViewById(R.id.introduction);
        create_team=findViewById(R.id.Create_team);
        manage_team=findViewById(R.id.manage_team);
        points=findViewById(R.id.fanatsy_points);
        acc_balance=findViewById(R.id.account_balance);
        faqs=findViewById(R.id.faqs);



        introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(How_To_Play_Activity.this,more_introduction.class);
                startActivity(intent);
            }
        });
       create_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(How_To_Play_Activity.this,more_createteam.class);
                startActivity(intent);
            }
        }); manage_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(How_To_Play_Activity.this,more_managing_team.class);
                startActivity(intent);
            }
        }); points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(How_To_Play_Activity.this,FantasyPointSystemActivity.class);
                startActivity(intent);
            }
        }); acc_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(How_To_Play_Activity.this,more_Account_Balance.class);
                startActivity(intent);
            }
        });
        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(How_To_Play_Activity.this,more_faqs.class);
                startActivity(intent);
            }
        });





    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
