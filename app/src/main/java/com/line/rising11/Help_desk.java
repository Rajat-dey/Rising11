package com.line.rising11;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Help_desk extends AppCompatActivity {

    ImageView fb,insta,twitter,yt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);
        setTitle("Help Desk");

        fb=findViewById(R.id.fb);








        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = getIntent();
                Uri data = intent.getData();


               startActivity(intent);


             //   Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));

            }
        });



    }
}
