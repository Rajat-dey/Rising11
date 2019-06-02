package com.line.rising11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Contest extends AppCompatActivity {

    TextView playerpoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        setTitle("JOINED CONTEST");


        playerpoints=findViewById(R.id.player_points);


        playerpoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Contest.this,player_points.class);
                startActivity(intent);
            }
        });

    }
}
