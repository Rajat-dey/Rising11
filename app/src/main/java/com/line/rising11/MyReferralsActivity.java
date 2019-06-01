package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyReferralsActivity extends AppCompatActivity {
    private TextView sharetv,moreshare;
    RelativeLayout sharerl;
    String number="1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_referrals);
        setTitle("MY REFERRALS");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

            SharedPreferences sharedPreferences = getSharedPreferences("loginstatus", Context.MODE_PRIVATE);
            sharetv = findViewById(R.id.sharetv);
            sharerl=findViewById(R.id.sharerl);
            moreshare=findViewById(R.id.moreshare);
            number = sharedPreferences.getString("number", "");

            sharetv.setText(String.valueOf(Long.parseLong(number, 16)));

            sharerl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                    whatsappIntent.setType("text/plain");
                    whatsappIntent.setPackage("com.whatsapp");
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Use this referral code "+String.valueOf(Long.parseLong(number, 16))+" and earn Rs. 50 Cahsback and earn Rs. 50 cashback each time your friend joins Rising11 by your referral code. Thank you for intesting in Rising11.");
                    try {
                        startActivity(whatsappIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(MyReferralsActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            moreshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Use this referral code "+String.valueOf(Long.parseLong(number, 16))+" and earn Rs. 50 Cahsback and earn Rs. 50 cashback each time your friend joins Rising11 by your referral code. Thank you for intesting in Rising11.");
                    sendIntent.setType("text/plain");

                    try {
                        startActivity(sendIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(MyReferralsActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
