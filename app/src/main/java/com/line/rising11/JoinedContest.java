package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JoinedContest extends AppCompatActivity implements JoinedContest_Recycler_adapter.OnAddListner2
{

    TextView playerpoints,TM1,TM2,match_status,score1,score2,statics;

    TextView prizepool,spots,entry,joined_with,points,rank;

    LinearLayout joinedcontest;

    String scorepart1,scorepart2;
    SharedPreferences sharedPreferences;

    joinedcontest_recyclerdataclass[] myListData1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joined_contest);
        setTitle("JOINED CONTEST");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        playerpoints=findViewById(R.id.player_points);

        sharedPreferences=getSharedPreferences("loginstatus", Context.MODE_PRIVATE);

      //  joinedcontest=findViewById(R.id.joined_contest);

        TM1=findViewById(R.id.TM1);
        TM2=findViewById(R.id.TM2);
        match_status=findViewById(R.id.match_status);
        score1=findViewById(R.id.score1);
        score2=findViewById(R.id.score2);
        statics=findViewById(R.id.statics);

        prizepool=findViewById(R.id.prize_pool);
        spots=findViewById(R.id.spots);
        entry=findViewById(R.id.entry);
        joined_with=findViewById(R.id.joined_with);
        points=findViewById(R.id.points);
        rank=findViewById(R.id.rank);



/*
        joinedcontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(JoinedContest.this,Contest.class);
                intent.putExtra("uid",getIntent().getStringExtra("uid"));
                startActivity(intent);
            }
        });*/


        playerpoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

               // Intent intent=new Intent(JoinedContest.this,player_points.class);


                Intent intent=new Intent(JoinedContest.this,Coming_soon.class);


                startActivity(intent);
            }
        });

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
                                    Toast.makeText(JoinedContest.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
            RestClient.getInstance(JoinedContest.this).addToRequestQueue(jsonObjectRequest);



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
                    (Request.Method.GET,getString(R.string.Cric_ranking)+"contest_id=526"+"&mobile="+sharedPreferences.getString("number","") , null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                             Log.d("Response: ", response.toString());
                            //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                            try {
                                if(response.getString("code").equals("1"))
                                {

                                    JSONArray jsonArray=response.getJSONArray("contest");
                                    myListData1 = new joinedcontest_recyclerdataclass[jsonArray.length()];

                                    JSONArray jsonArrayAR=new JSONArray();

                                    for(int i=0;i<jsonArray.length();i++)
                                    {

                                       myListData1[i]=new joinedcontest_recyclerdataclass("₹"+jsonArray.getJSONObject(i).getString("total_winning_amount"),"₹"+jsonArray.getJSONObject(i).getString("contest_size"),jsonArray.getJSONObject(i).getString("entry_fees"),jsonArray.getJSONObject(i).getString("team_id"),jsonArray.getJSONObject(i).getString("points"),jsonArray.getJSONObject(i).getString("rank"));

                                        //jsonArrayAR.put(jsonArray.getJSONObject(i));


                                    }
                                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                                    JoinedContest_Recycler_adapter adapter = new JoinedContest_Recycler_adapter(myListData1,JoinedContest.this);
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(JoinedContest.this));

                                    recyclerView.setAdapter(adapter);

                                }
                                else
                                {
                                    Toast.makeText(JoinedContest.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                           /* Toast.makeText(getApplicationContext(), "Error: "
                                    + error.getLocalizedMessage(), Toast.LENGTH_LONG)
                                    .show();*/
                        }
                    });

            // Access the RequestQueue through your singleton class.
            RestClient.getInstance(JoinedContest.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            /*Snackbar.make(, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }
















































    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onAddClick(int position) {

    }
}
