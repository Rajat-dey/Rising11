package com.line.rising11;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.cketti.mailto.EmailIntentBuilder;

public class Help_desk extends AppCompatActivity {

    ImageView fb, insta, twitter, yt;
    TextView sup,info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);
        setTitle("Help Desk");

        fb = findViewById(R.id.fb);
        yt = findViewById(R.id.youtube);
        insta = findViewById(R.id.instagram);
        twitter = findViewById(R.id.twitter);
        sup = findViewById(R.id.support);
        info = findViewById(R.id.info);



        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent emailIntent = EmailIntentBuilder.from(Help_desk.this)
                        .to("support@rising11.com")
                        .subject("HelpDesk")
                        .build();


                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Help_desk.this, "Gmail not found",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent emailIntent = EmailIntentBuilder.from(Help_desk.this)
                        .to("info@rising11.com")
                        .subject("HelpDesk")
                        .build();


                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Help_desk.this, "Gmail not found",
                            Toast.LENGTH_LONG).show();
                }
            }
        });













        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenFacebookPage();
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenInstaPage();
            }
        });


        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                twitterpage();
            }
        });


        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                youtubepage();
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


    protected  void youtubepage()
    {
        Intent intent = null;
        try {
            // get the Twitter app if possible
            this.getPackageManager().getPackageInfo("com.google.android.youtube", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCMWcWW4oLaTOZGDo1enSpHg"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCMWcWW4oLaTOZGDo1enSpHg"));
        }
        this.startActivity(intent);

    }




    protected void OpenInstaPage ()
    {
        Intent likeIng = new Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.instagram.com/rising11kk"));
        likeIng.setPackage("com.instagram.android");
        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/rising11kk")));
        }
    }


    protected  void twitterpage()
    {
        Intent intent = null;
        try {
            // get the Twitter app if possible
            this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Rising11_kk?s=08"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Rising11_kk?s=08"));
        }
        this.startActivity(intent);

    }


}