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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Contest extends AppCompatActivity {


    String scorepart1,scorepart2;
    TextView playerpoints,TM1,TM2,match_status,score1,score2,statics;

    TextView prizepool,spots,entry;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        setTitle("Leader Board");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        playerpoints=findViewById(R.id.player_points);


        playerpoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Contest.this,Coming_soon.class);
                startActivity(intent);
            }
        });


        TM1=findViewById(R.id.TM1);
        TM2=findViewById(R.id.TM2);
        match_status=findViewById(R.id.match_status);
        score1=findViewById(R.id.score1);
        score2=findViewById(R.id.score2);
        statics=findViewById(R.id.statics);

        prizepool=findViewById(R.id.prize_pool);
        spots=findViewById(R.id.spots);
        entry=findViewById(R.id.entry);




        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, getString(R.string.Cric_cricket_score)+"&unique_id="+getIntent().getStringExtra("uid"), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Response: ", response.toString());
                            //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());

                          /*  Toast.makeText(JoinedContest.this, getString(R.string.Cric_cricket_score)+"&unique_id="+getIntent().getStringExtra("uid"), Toast.LENGTH_LONG).show();
                            Toast.makeText(JoinedContest.this, response.toString(), Toast.LENGTH_SHORT).show();

                          */

                            try {
                                if(response.getString("code").equals("1"))
                                {


                                    TM1.setText(response.getJSONObject("data").getString("team-1"));
                                    TM2.setText(response.getJSONObject("data").getString("team-2"));


                                    if(!response.getJSONObject("data").getString("matchStarted").equals("true")) {
                                        match_status.setText("COMPLETED");
                                    }


                                    if(response.getJSONObject("data").has("description"))
                                    {
                                        String ScoreData = response.getJSONObject("data").getString("description");

                                        String[] parts = ScoreData.split("v");

                                        scorepart1 =  parts[0];
                                        scorepart2= parts[1];


                                        score1.setText(scorepart1);
                                        score2.setText(scorepart2);
                                    }

                                    else
                                    {
                                        score1.setText("Match not started");
                                        score2.setText("Match not started");
                                    }


                                    statics.setText(response.getJSONObject("data").getString("stat"));



                                }
                                else
                                {
                                    Toast.makeText(Contest.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
            RestClient.getInstance(Contest.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            /*Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }





        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, getString(R.string.Cric_ranking)+"contest_id=27"+"&mobile=7062018163", null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Response: ", response.toString());
                            //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                            try {
                                if(response.getString("code").equals("1"))
                                {


                                    prizepool.setText(response.getJSONObject("contest").getString("total_winning_amount"));
                                    spots.setText(response.getJSONObject("contest").getString("contest_size"));
                                    entry.setText(response.getJSONObject("contest").getString("entry_fees"));



                                }
                                else
                                {
                                    Toast.makeText(Contest.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

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
            RestClient.getInstance(Contest.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
          /*  Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/

        }










    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
