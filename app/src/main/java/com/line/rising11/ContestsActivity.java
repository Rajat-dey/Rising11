package com.line.rising11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ContestsActivity extends AppCompatActivity {

    TextView create_team,contest_code,create_contest,more_contest;
    CardView entryfee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);
        setTitle("CONTESTS");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ContestRecyclerDataClass[] myListData = new ContestRecyclerDataClass[]
                {
                new ContestRecyclerDataClass("₹50,000","₹20","3,333 spots","1,654 spots left","2,500 Winners","C","M"),
                new ContestRecyclerDataClass("₹10,000","₹5,750","2 spots","1 spots left","1 Winners","C","M"),
                new ContestRecyclerDataClass("₹20,000","₹6,750","6 spots","3 spots left","2 Winners","C","M"),

        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        ContestRecyclerAdapter adapter = new ContestRecyclerAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        create_team=findViewById(R.id.create_team);
        contest_code=findViewById(R.id.contest_code);
        create_contest=findViewById(R.id.create_contest);
        entryfee=findViewById(R.id.entryfee);
        more_contest=findViewById(R.id.more_contest);

        entryfee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContestsActivity.this,ContestsFilterActivity.class);
                startActivity(intent);
            }
        });

        create_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContestsActivity.this, TeamSelectionActivity.class);
                startActivity(intent);
            }
        });

        contest_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContestsActivity.this, Invite_Code_Activity.class);
                startActivity(intent);
            }
        });


        create_contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContestsActivity.this, CreateContest.class);
                startActivity(intent);
            }
        });


        more_contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContestsActivity.this, ContestsFilterActivity.class);
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
