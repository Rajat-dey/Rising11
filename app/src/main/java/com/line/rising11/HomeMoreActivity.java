package com.line.rising11;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class HomeMoreActivity extends AppCompatActivity {
    RelativeLayout  invite_rl,invite_code_rl,whatsapp_rl,how_to_play_rl,fantasy_rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_more);
        setTitle("MORE");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        invite_rl=findViewById(R.id.invite_rl);
        invite_code_rl=findViewById(R.id.invite_code_rl);
        whatsapp_rl=findViewById(R.id.whatsapp_rl);
        how_to_play_rl=findViewById(R.id.how_to_play_rl);
        fantasy_rl=findViewById(R.id.fantasy_rl);

        invite_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,MyReferralsActivity.class);
                startActivity(intent);
            }
        });
        invite_code_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(HomeMoreActivity.this,Invite_Code_Activity.class);
                Intent intent=new Intent(HomeMoreActivity.this,ContestsActivity.class);
                startActivity(intent);
            }
        });
        whatsapp_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,Whatsapp_Update_Activity.class);
                startActivity(intent);

            }
        });
        how_to_play_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,How_To_Play_Activity.class);
                startActivity(intent);
                
            }
        });
        fantasy_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,FantasyPointSystemActivity.class);
                startActivity(intent);

            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                if(menuItem.getItemId()==R.id.navigation_home)
                {
                    finish();
                }
                else if (menuItem.getItemId()==R.id.navigation_dashboard)
                {
                    Intent intent=new Intent(HomeMoreActivity.this,MyMatchActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if(menuItem.getItemId()==R.id.navigation_notifications)
                {

                }
                return true;
            }
        });


    }
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}
