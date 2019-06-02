package com.line.rising11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class JoinedContest extends AppCompatActivity {

    TextView prizepool,spots,entry,joined_with,points,rank,playerpoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joined_contest);
        setTitle("JOINED CONTEST");

        prizepool=findViewById(R.id.prize_pool);
        spots=findViewById(R.id.spots);
        entry=findViewById(R.id.entry);
        joined_with=findViewById(R.id.joined_with);
        points=findViewById(R.id.points);
        rank=findViewById(R.id.rank);
        playerpoints=findViewById(R.id.player_points);



        prizepool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(JoinedContest.this,Contest.class);
                startActivity(intent);
            }
        });


        spots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(JoinedContest.this,Contest.class);
                startActivity(intent);
            }
        });
        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(JoinedContest.this,Contest.class);
                startActivity(intent);
            }
        });
        joined_with.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(JoinedContest.this,Contest.class);
                startActivity(intent);
            }
        }); points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(JoinedContest.this,Contest.class);
                startActivity(intent);
            }
        }); rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(JoinedContest.this,Contest.class);
                startActivity(intent);
            }
        });


        playerpoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(JoinedContest.this,player_points.class);
                startActivity(intent);
            }
        });

    }
}
