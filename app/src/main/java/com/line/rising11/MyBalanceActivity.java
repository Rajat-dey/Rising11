package com.line.rising11;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MyBalanceActivity extends AppCompatActivity  {
    private Button add_cash, verify_user;
    TextView bonus,deposits,win,Balance;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_balance);
        setTitle("MY BALANCE");

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPreferences=getSharedPreferences("loginstatus", Context.MODE_PRIVATE);

        if (ContextCompat.checkSelfPermission(MyBalanceActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MyBalanceActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }

       add_cash=findViewById(R.id.add_cash);
       verify_user=findViewById(R.id.verify);
       bonus=findViewById(R.id.cash_bonus);
       deposits=findViewById(R.id.deposit);
       win=findViewById(R.id.winnings);
       Balance=findViewById(R.id.balance);

       add_cash.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               Intent intent=new Intent(MyBalanceActivity.this,AddCashActivity.class);
               startActivity(intent);
           }
       });


        verify_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MyBalanceActivity.this,Verify_User_card.class);
                startActivity(intent);
            }
        });



        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, getString(R.string.wallet)+"?mobile="+sharedPreferences.getString("number",""), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Response: ", response.toString());
                            //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                            try {
                                if(response.getString("code").equals("1"))
                                {
                                    JSONObject obj=response.getJSONObject("balance");

                                   bonus.setText(obj.getString("bonus"));
                                   win.setText(obj.getString("winning"));
                                   deposits.setText(obj.getString("deposite"));
                                   Balance.setText(obj.getString("total_balance"));

                                  //  Toast.makeText(HomeActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();


                                }
                                else
                                {
                                    Toast.makeText(MyBalanceActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                               /* Toast.makeText(getApplicationContext(), "Error "
                                                        + response.getString("code") + ": "
                                                        + response.getString("message"), Toast.LENGTH_LONG)
                                                        .show();*/
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            Toast.makeText(getApplicationContext(), "Error: "
                                    + error.getLocalizedMessage(), Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

            // Access the RequestQueue through your singleton class.
            RestClient.getInstance(MyBalanceActivity.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            /*Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }














    }





    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}