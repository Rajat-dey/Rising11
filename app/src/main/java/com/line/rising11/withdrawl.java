package com.line.rising11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class withdrawl extends AppCompatActivity {

    Button bank,paytm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawl);
        setTitle("WITHDRAW BALANCE");



        bank=findViewById(R.id.bnk_withdraw);
        paytm=findViewById(R.id.paytm_withdrawl);

        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(withdrawl.this,bank_withdrawl.class);
                startActivity(intent);
            }
        });

        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(withdrawl.this,paytm_withdrawl.class);
                startActivity(intent);
            }
        });

    }
}
