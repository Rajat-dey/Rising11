package com.line.rising11;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MyBalanceActivity extends AppCompatActivity  {
    private Button add_cash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_balance);
        setTitle("MY BALANCE");

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (ContextCompat.checkSelfPermission(MyBalanceActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MyBalanceActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }

       add_cash=findViewById(R.id.add_cash);
       add_cash.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               Intent intent=new Intent(MyBalanceActivity.this,AddCashActivity.class);
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
