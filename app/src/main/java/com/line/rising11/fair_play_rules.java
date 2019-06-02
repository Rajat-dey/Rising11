package com.line.rising11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class fair_play_rules extends AppCompatActivity {

    Button TC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fair_play);
        setTitle("Fair Play Rules");

        TC = findViewById(R.id.fair_play_TC);


        TC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(fair_play_rules.this,fairPlay_TC.class);
                startActivity(intent);
            }
        });





    }
}
