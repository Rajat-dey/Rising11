package com.line.rising11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ContestsFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests_filter);
        setTitle("CONTESTS");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ContestFilterRecyclerDataClass[] myListData = new ContestFilterRecyclerDataClass[]
                {
                        new ContestFilterRecyclerDataClass("₹50,000","₹20","3,333 spots","1,654 spots left","2,500 Winners","C","M"),
                        new ContestFilterRecyclerDataClass("₹10,000","₹5,750","2 spots","1 spots left","1 Winners","C","M"),
                        new ContestFilterRecyclerDataClass("₹20,000","₹6,750","6 spots","3 spots left","2 Winners","C","M"),

                };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        ContestFilterRecyclerAdapter adapter = new ContestFilterRecyclerAdapter(myListData);
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
