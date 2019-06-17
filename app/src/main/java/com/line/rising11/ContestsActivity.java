package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.line.rising11.adapters.CustomMyContestAdapter;
import com.line.rising11.adapters.CustomMyMatchAdapter;
import com.line.rising11.adapters.CustomMyTeamAdapter;
import com.line.rising11.adapters.CustomTeamSelectionAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContestsActivity extends AppCompatActivity implements ContestRecyclerAdapter.OnAddListner2 {

    TextView create_team,contest_code,create_contest,more_contest,all_filters;
    CardView entryfee,contest_size;
    ContestRecyclerDataClass[] myListData;
    int loaddata=0;
    SharedPreferences sharedPreferences;
    JSONArray jsonArray1;
    RelativeLayout rl_for_hide;
    int countjoincontest=0,countteam=0;
    RecyclerView rv_my_team,rv_my_contest;
    TabLayout tabLayout1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);
        setTitle("CONTESTS");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        jsonArray1 = new JSONArray();
        rl_for_hide=findViewById(R.id.rl_for_hide);
        tabLayout1=findViewById(R.id.tablayout1);
        sharedPreferences=getSharedPreferences("loginstatus", Context.MODE_PRIVATE);

        rv_my_team=findViewById(R.id.rv_my_team);
        rv_my_contest=findViewById(R.id.rv_my_contest);
        rv_my_contest.setVisibility(View.GONE);
        rv_my_team.setVisibility(View.GONE);

        boolean connected1 = false;
        ConnectivityManager connectivityManager1 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager1.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager1.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, "http://rising11.com/apps/apis/get-reg-contest.php"+"?mobile="+sharedPreferences.getString("number",""), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                           JSONArray joincontestJSON=new JSONArray();

                            try {

                                if(response.getString("code").equals("1"))
                                {

                                    joincontestJSON=response.getJSONArray("contests");




                                        for(int j=0;j<joincontestJSON.length();j++)
                                        {
                                            if(joincontestJSON.getJSONObject(j).getString("unique_id").equals(getIntent().getStringExtra("uid")))
                                            {
                                                countjoincontest=countjoincontest+1;
                                            }
                                        }
                                        tabLayout1.getTabAt(1).setText("MY CONTESTS ("+String.valueOf(countjoincontest)+")");

                                }
                                else
                                {

                                    Toast.makeText(ContestsActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },new Response.ErrorListener() {

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



            connected1 = true;
        }
        else
        {
                        /*Snackbar.make(view, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
        }






        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, "http://rising11.com/apps/apis/get-all-contest.php?unique_id="+getIntent().getStringExtra("uid"), null, new Response.Listener<JSONObject>() {
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

                                            myListData[i]=new ContestRecyclerDataClass("₹"+jsonArray.getJSONObject(i).getString("total_winning_amount"),"₹"+jsonArray.getJSONObject(i).getString("entry_fees"),jsonArray.getJSONObject(i).getString("contest_size")+" spots","1,654 spots left","2,500 Winners","C","M",jsonArray.getJSONObject(i).getString("contest_id"));

                                            //jsonArrayAR.put(jsonArray.getJSONObject(i));


                                    }
                                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                                    ContestRecyclerAdapter adapter = new ContestRecyclerAdapter(myListData,ContestsActivity.this);
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




        boolean connected2 = false;
        ConnectivityManager connectivityManager2 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, "http://rising11.com/apps/apis/get-user-teams.php?unique_id="+getIntent().getStringExtra("uid")+"&user_id="+sharedPreferences.getString("number",""), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            try {
                                if(response.getString("code").equals("1"))
                                {
                                    loaddata=1;
                                    for(int i=0;i<response.getJSONArray("teams").length();i++)
                                    {
                                        loaddata=2;
                                        jsonArray1.put(response.getJSONArray("teams").get(i));
                                        countteam=countteam+1;
                                    }
                                    tabLayout1.getTabAt(2).setText("MY TEAMS ("+String.valueOf(countteam)+")");
                                        }
                                else
                                {
                                    loaddata=3;
                                    Toast.makeText(ContestsActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
            RestClient.getInstance(ContestsActivity.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            /*Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }



        create_team=findViewById(R.id.create_team);
        contest_code=findViewById(R.id.contest_code);
        create_contest=findViewById(R.id.create_contest);
        entryfee=findViewById(R.id.entryfee);
        more_contest=findViewById(R.id.more_contest);
        contest_size=findViewById(R.id.contestsize);
        all_filters=findViewById(R.id.all_filters);

        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                if(tab.getPosition()==0)
                {
                    rl_for_hide.setVisibility(View.VISIBLE);
                    rv_my_team.setVisibility(View.GONE);
                    rv_my_contest.setVisibility(View.GONE);

                }
                else if(tab.getPosition()==1)
                {
                    rl_for_hide.setVisibility(View.GONE);
                    rv_my_team.setVisibility(View.GONE);
                    rv_my_contest.setVisibility(View.VISIBLE);
                    rv_my_contest.setLayoutManager(new LinearLayoutManager(ContestsActivity.this, LinearLayoutManager.VERTICAL, false));
                    rv_my_contest.setAdapter(new CustomMyContestAdapter(ContestsActivity.this));
                }
                else if(tab.getPosition()==2)
                {
                    rl_for_hide.setVisibility(View.GONE);
                    rv_my_team.setVisibility(View.VISIBLE);
                    rv_my_contest.setVisibility(View.GONE);
                    rv_my_team.setLayoutManager(new LinearLayoutManager(ContestsActivity.this, LinearLayoutManager.VERTICAL, false));
                    rv_my_team.setAdapter(new CustomMyTeamAdapter(ContestsActivity.this));

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        entryfee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContestsActivity.this,ContestsFilterActivity.class);
                startActivity(intent);
            }
        });

        all_filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContestsActivity.this,contest_filter.class);
                startActivity(intent);
            }
        });

        contest_size.setOnClickListener(new View.OnClickListener() {
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
                intent.putExtra("uid",getIntent().getStringExtra("uid"));
                intent.putExtra("for","teamcreate");
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
                intent.putExtra("uid",getIntent().getStringExtra("uid"));
                startActivity(intent);
            }
        });


        more_contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContestsActivity.this, ContestsFilterActivity.class);
                intent.putExtra("uid",getIntent().getStringExtra("uid"));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onAddClick(int position)
    {



        if(loaddata==2)
        {
            String tid=null;
            try {
                tid=jsonArray1.getJSONObject(0).getString("team_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            boolean connected1 = false;
            ConnectivityManager connectivityManager1 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

            if(connectivityManager1.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager1.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, "http://rising11.com/apps/apis/join-contest.php?user_id="+sharedPreferences.getString("number","")+"&contest_id="+myListData[position].getCid()+"&user_name="+sharedPreferences.getString("name","")+"&team_id="+tid+"&status=pending", null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                 try {
                                    if(response.getString("code").equals("1"))
                                    {

                                        Toast.makeText(ContestsActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {

                                        Toast.makeText(ContestsActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
                RestClient.getInstance(ContestsActivity.this).addToRequestQueue(jsonObjectRequest);



                connected1 = true;
            }
            else
            {
            /*Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
            }


        }
        if(loaddata==3)
        {
            Toast.makeText(this, "Please create team first", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ContestsActivity.this, TeamSelectionActivity.class);
            intent.putExtra("uid",getIntent().getStringExtra("uid"));
            intent.putExtra("for","teamcreate");
            startActivity(intent);
            finish();
        }

    }
}
