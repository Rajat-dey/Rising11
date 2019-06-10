package com.line.rising11;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Help_desk extends AppCompatActivity {

    ImageView fb, insta, twitter, yt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);
        setTitle("Help Desk");

        fb = findViewById(R.id.fb);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenFacebookPage();
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenFacebookPage();
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenFacebookPage();
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenFacebookPage();
            }
        });


    }
        protected void OpenFacebookPage () {

            String facebookPageID = "Rising11kk/?ti=as";

            // URL
            String facebookUrl = "https://www.facebook.com/" + facebookPageID;

            String facebookUrlScheme = "fb://page/" + facebookPageID;

            try {
                int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;

                if (versionCode >= 3002850) {
                    Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } else {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrlScheme)));
                }
            } catch (PackageManager.NameNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));

            }

        }


}