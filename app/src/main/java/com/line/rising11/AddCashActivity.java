package com.line.rising11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCashActivity extends AppCompatActivity {
    private Button add_cash,r10,r20,r100,r200;
    private EditText etcash;
    int money=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash);
        setTitle("Add Cash");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        add_cash=findViewById(R.id.add_cash);
        r10=findViewById(R.id.r10);
        r20=findViewById(R.id.r20);
        r100=findViewById(R.id.r100);
        r200=findViewById(R.id.r200);
        etcash=findViewById(R.id.etcash);

        r10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etcash.getText().toString().equals(""))
                {
                    etcash.setText("10");
                }
                else
                {
                    etcash.setText(String.valueOf(Integer.parseInt(etcash.getText().toString().trim())+10));
                }
            }
        });
        r20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etcash.getText().toString().equals(""))
                {
                    etcash.setText("20");
                }
                else
                {
                    etcash.setText(String.valueOf(Integer.parseInt(etcash.getText().toString().trim())+20));
                }
            }
        });
        r100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etcash.getText().toString().equals(""))
                {
                    etcash.setText("100");
                }
                else
                {
                    etcash.setText(String.valueOf(Integer.parseInt(etcash.getText().toString().trim())+100));
                }

            }
        });
        r200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etcash.getText().toString().equals(""))
                {
                    etcash.setText("10");
                }
                else
                {
                    etcash.setText(String.valueOf(Integer.parseInt(etcash.getText().toString().trim())+200));
                }

            }
        });

        add_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             if(etcash.getText().toString().trim().equals(""))
             {
                 etcash.setError("Enter Money");
             }
             else
             {
                 money=Integer.parseInt(etcash.getText().toString().trim());
                 Toast.makeText(AddCashActivity.this, String.valueOf(money) +"add ho gai", Toast.LENGTH_SHORT).show();

                 // ----------------------add paytm api here-----------------------
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
