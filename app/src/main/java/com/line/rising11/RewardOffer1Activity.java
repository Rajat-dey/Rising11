package com.line.rising11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RewardOffer1Activity extends AppCompatActivity {
    private TextView getcoupons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward_offer1);
        setTitle("REWARDS AND OFFERS");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getcoupons=findViewById(R.id.getcoupons);

        getcoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(RewardOffer1Activity.this,RewardOffer2Activity.class);
                startActivity(intent);
            }
        });

        MyActiveOfferDataClass[] myListData = new MyActiveOfferDataClass[] {
                new MyActiveOfferDataClass("₹25","Expires in 6 Days!","you've got a Rs.25 discount to any Rs.40+ contest!","Received on 11 Apr, 2019"),
                new MyActiveOfferDataClass("₹35","Expires in 7 Days!","you've got a Rs.25 discount to any Rs.50+ contest!","Received on 12 Apr, 2019")

        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        MyListAdapterActiveOffer adapter = new MyListAdapterActiveOffer(myListData);
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
