package com.line.rising11;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MyMatchActivity extends AppCompatActivity {

    Button upcoming_matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_match);
        setTitle("MY MATCHES");

        upcoming_matches=findViewById(R.id.upcoming_matches);




        upcoming_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MyMatchActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

                }
                else if(menuItem.getItemId()==R.id.navigation_notifications)
                {
                    Intent intent=new Intent(MyMatchActivity.this,HomeMoreActivity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
