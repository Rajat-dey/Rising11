package com.line.rising11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ContestsActivity extends AppCompatActivity {

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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
