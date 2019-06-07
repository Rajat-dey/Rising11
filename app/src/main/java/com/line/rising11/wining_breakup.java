package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class wining_breakup extends AppCompatActivity {
   private EditText amount1,amount2,amount3,amount4,amount5,amount6,amount7,rank1,rank2,rank3,rank4,rank5,rank6,rank7;
    private Button submit;
    int r1=0,r2=0,r3=0,r4=0,r5=0,r6=0,r7=0;
    JsonObject jsonObject;
    JsonArray jsonArray;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wining_breakup);
        amount1=findViewById(R.id.amount1);
        amount2=findViewById(R.id.amount2);
        amount3=findViewById(R.id.amount3);
        amount4=findViewById(R.id.amount4);
        amount5=findViewById(R.id.amount5);
        amount6=findViewById(R.id.amount6);
        amount7=findViewById(R.id.amount7);
        rank1=findViewById(R.id.rank1);
        rank2=findViewById(R.id.rank2);
        rank3=findViewById(R.id.rank3);
        rank4=findViewById(R.id.rank4);
        rank5=findViewById(R.id.rank5);
        rank6=findViewById(R.id.rank6);
        rank7=findViewById(R.id.rank7);
        submit=findViewById(R.id.submit);
        jsonObject=new JsonObject();
        jsonArray=new JsonArray();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!rank1.getText().toString().equals("") && !amount1.getText().toString().equals(""))
                {
                    r1=1;
                }
                if(!rank2.getText().toString().equals("") && !amount2.getText().toString().equals(""))
                {
                    r2=1;
                }
                if(!rank3.getText().toString().equals("") && !amount3.getText().toString().equals(""))
                {
                    r3=1;
                }
                if(!rank4.getText().toString().equals("") && !amount4.getText().toString().equals(""))
                {
                    r4=1;
                }
                if(!rank5.getText().toString().equals("") && !amount5.getText().toString().equals(""))
                {
                    r5=1;
                }
                if(!rank6.getText().toString().equals("") && !amount6.getText().toString().equals(""))
                {
                    r6=1;
                }
                if(!rank7.getText().toString().equals("") && !amount7.getText().toString().equals(""))
                {
                    r7=1;
                }
                if(r1==1 && r2==1 && r3==1 && r4==1 &&  r5==1 && r6==1 && r7==1)
                {
                    jsonObject.addProperty(rank1.getText().toString().trim(),amount1.getText().toString().trim());
                    jsonObject.addProperty(rank2.getText().toString().trim(),amount2.getText().toString().trim());
                    jsonObject.addProperty(rank3.getText().toString().trim(),amount3.getText().toString().trim());
                    jsonObject.addProperty(rank4.getText().toString().trim(),amount4.getText().toString().trim());
                    jsonObject.addProperty(rank5.getText().toString().trim(),amount5.getText().toString().trim());
                    jsonObject.addProperty(rank6.getText().toString().trim(),amount6.getText().toString().trim());
                    jsonObject.addProperty(rank7.getText().toString().trim(),amount7.getText().toString().trim());
                    flag=1;
                }
                else if(r1==1 && r2==1 && r3==1 && r4==1 &&  r5==1 && r6==1)
                {
                    jsonObject.addProperty(rank1.getText().toString().trim(),amount1.getText().toString().trim());
                    jsonObject.addProperty(rank2.getText().toString().trim(),amount2.getText().toString().trim());
                    jsonObject.addProperty(rank3.getText().toString().trim(),amount3.getText().toString().trim());
                    jsonObject.addProperty(rank4.getText().toString().trim(),amount4.getText().toString().trim());
                    jsonObject.addProperty(rank5.getText().toString().trim(),amount5.getText().toString().trim());
                    jsonObject.addProperty(rank6.getText().toString().trim(),amount6.getText().toString().trim());
                    flag=1;
                }
                else if(r1==1 && r2==1 && r3==1 && r4==1 &&  r5==1 )
                {
                    jsonObject.addProperty(rank1.getText().toString().trim(),amount1.getText().toString().trim());
                    jsonObject.addProperty(rank2.getText().toString().trim(),amount2.getText().toString().trim());
                    jsonObject.addProperty(rank3.getText().toString().trim(),amount3.getText().toString().trim());
                    jsonObject.addProperty(rank4.getText().toString().trim(),amount4.getText().toString().trim());
                    jsonObject.addProperty(rank5.getText().toString().trim(),amount5.getText().toString().trim());
                    flag=1;
                }
                else if(r1==1 && r2==1 && r3==1 && r4==1 )
                {
                    jsonObject.addProperty(rank1.getText().toString().trim(),amount1.getText().toString().trim());
                    jsonObject.addProperty(rank2.getText().toString().trim(),amount2.getText().toString().trim());
                    jsonObject.addProperty(rank3.getText().toString().trim(),amount3.getText().toString().trim());
                    jsonObject.addProperty(rank4.getText().toString().trim(),amount4.getText().toString().trim());
                    flag=1;
                }
                else if(r1==1 && r2==1 && r3==1)
                {
                    jsonObject.addProperty(rank1.getText().toString().trim(),amount1.getText().toString().trim());
                    jsonObject.addProperty(rank2.getText().toString().trim(),amount2.getText().toString().trim());
                    jsonObject.addProperty(rank3.getText().toString().trim(),amount3.getText().toString().trim());
                    flag=1;
                }
                else if(r1==1 && r2==1)
                {
                    jsonObject.addProperty(rank1.getText().toString().trim(),amount1.getText().toString().trim());
                    jsonObject.addProperty(rank2.getText().toString().trim(),amount2.getText().toString().trim());
                    flag=1;
                }
                else if(r1==1)
                {
                    jsonObject.addProperty(rank1.getText().toString().trim(),amount1.getText().toString().trim());
                    flag=1;
                }

                if(flag==1)
                {
                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.GET, getString(R.string.add_contets_prize)+"?contest_id="+getIntent().getStringExtra("contest_id")+"&prize_list="+
                                        jsonObject.toString(), null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                           
                                        try {
                                            if(response.getString("code").equals("1"))
                                            {


                                                Toast.makeText(wining_breakup.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(wining_breakup.this, TeamSelectionActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else
                                            {
                                                Toast.makeText(wining_breakup.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

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
                        RestClient.getInstance(wining_breakup.this).addToRequestQueue(jsonObjectRequest);



                        connected = true;
                    }
                    else
                    {
                        Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                }

                    flag=0;
                r1=0;
                r2=0;
                r3=0;
                r4=0;
                r5=0;
                r6=0;
                r7=0;
            }
        });
    }
}
