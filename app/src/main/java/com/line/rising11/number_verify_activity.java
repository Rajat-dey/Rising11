package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class number_verify_activity extends AppCompatActivity {

    private TextView one,two,three,four,five,six,seven,eight,nine,zero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_verify_activity);
        setTitle("OTP VERIFICATION");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five1);
        six = findViewById(R.id.six1);
        seven = findViewById(R.id.seven1);
        eight = findViewById(R.id.eight1);
        nine = findViewById(R.id.nine1);
        zero = findViewById(R.id.zero1);
        final PinEntryEditText pinEntry = (PinEntryEditText) findViewById(R.id.txt_pin_entry);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinEntry.append("1");

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinEntry.append("2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinEntry.append("3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinEntry.append("4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinEntry.append("5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinEntry.append("6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinEntry.append("7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinEntry.append("8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinEntry.append("9");
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pinEntry.append("0");
            }
        });
        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener()
            {
                @Override
                public void onPinEntered(CharSequence str)
                {

                    if (str.toString().length()==4)
                    {
                      //  if(getIntent().getStringExtra("mob").equals("mob"))
                        {
                            boolean connected = false;
                            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                        (Request.Method.GET, getString(R.string.verifymobile)+"?mobile="+getIntent().getStringExtra("mob")+"&otp="+str.toString(), null, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                // Log.d("Response: ", response.toString());
                                                //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                                                try {
                                                    if(response.getString("code").equals("1"))
                                                    {


                                                        Toast.makeText(number_verify_activity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(number_verify_activity.this, HomeActivity.class);
                                                        intent.putExtra("mob",getIntent().getStringExtra("mob"));
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                    else
                                                    {

                                                        pinEntry.setText(null);
                                                        Toast.makeText(number_verify_activity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
                                RestClient.getInstance(number_verify_activity.this).addToRequestQueue(jsonObjectRequest);



                                connected = true;
                            }
                            else
                            {
                               /* Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();*/
                            }

                        }

                    }
                }
            });

        }
    }
}