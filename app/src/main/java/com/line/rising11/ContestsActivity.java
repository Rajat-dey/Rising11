package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.line.rising11.adapters.CustomTeamSelectionAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContestsActivity extends AppCompatActivity {

    TextView create_team,contest_code,create_contest,more_contest;
    CardView entryfee;
    ContestRecyclerDataClass[] myListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);
        setTitle("CONTESTS");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, "http://rising11.com/apps/apis/get-all-contest.php", null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Log.d("Response: ", response.toString());
                            //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                            try {
                                if(response.getString("code").equals("1"))
                                {

                                    JSONArray jsonArray=response.getJSONArray("contests");
                                    myListData = new ContestRecyclerDataClass[jsonArray.length()];

                                    JSONArray jsonArrayAR=new JSONArray();

                                    for(int i=0;i<jsonArray.length();i++)
                                    {

                                            myListData[i]=new ContestRecyclerDataClass("₹"+jsonArray.getJSONObject(i).getString("total_winning_amount"),"₹"+jsonArray.getJSONObject(i).getString("entry_fees"),jsonArray.getJSONObject(i).getString("contest_size")+" spots","1,654 spots left","2,500 Winners","C","M");

                                            //jsonArrayAR.put(jsonArray.getJSONObject(i));


                                    }
                                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                                    ContestRecyclerAdapter adapter = new ContestRecyclerAdapter(myListData);
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(ContestsActivity.this));
                                    recyclerView.setAdapter(adapter);

                                }
                                else
                                {
                                    Toast.makeText(ContestsActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

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
            RestClient.getInstance(ContestsActivity.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            /*Snackbar.make(, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }





        create_team=findViewById(R.id.create_team);
        contest_code=findViewById(R.id.contest_code);
        create_contest=findViewById(R.id.create_contest);
        entryfee=findViewById(R.id.entryfee);
        more_contest=findViewById(R.id.more_contest);

        entryfee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContestsActivity.this,ContestsFilterActivity.class);
                startActivity(intent);
            }
        });

        create_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContestsActivity.this, TeamSelectionActivity.class);
                startActivity(intent);
            }
        });

        contest_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContestsActivity.this, Invite_Code_Activity.class);
                startActivity(intent);
            }
        });


        create_contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContestsActivity.this, CreateContest.class);
                startActivity(intent);
            }
        });


        more_contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContestsActivity.this, ContestsFilterActivity.class);
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
