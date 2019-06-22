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

import java.util.ArrayList;

public class ContestsActivity extends AppCompatActivity implements ContestRecyclerAdapter.OnAddListner2 {

    TextView create_team,contest_code,create_contest,more_contest,all_filters,team1,team2;
    CardView entryfee,contest_size;
    ContestRecyclerDataClass[] myListData;
    MycontestRecyclerDataClass[] myContestDatalist;
    int loaddata=0;
    SharedPreferences sharedPreferences;
    JSONArray jsonArray1;
    RelativeLayout rl_for_hide;
    int countjoincontest=0,countteam=0,privatecontest=0;
    RecyclerView rv_my_team,rv_my_contest;
    TabLayout tabLayout1;
    int q=0;
    int ldata=0;
     ArrayList<String> teamnumber,teamname1,teamname2,team1count,team2count,cname,cimage,vcname,vcimage,wk,bat,ar,bowl;



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

        team1=findViewById(R.id.team1);
        team2=findViewById(R.id.team2);

        team1.setText(getIntent().getStringExtra("team1"));
        team2.setText(getIntent().getStringExtra("team2"));

        /*boolean connected1 = false;
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
                           *//* Toast.makeText(getApplicationContext(), "Error: "
                                    + error.getLocalizedMessage(), Toast.LENGTH_LONG)
                                    .show();*//*
                        }
                    });

            // Access the RequestQueue through your singleton class.
            RestClient.getInstance(ContestsActivity.this).addToRequestQueue(jsonObjectRequest);



            connected1 = true;
        }
        else
        {
                        *//*Snackbar.make(view, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*//*
        }

*/




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
                                    myContestDatalist=new MycontestRecyclerDataClass[jsonArray.length()];

                                    JSONArray jsonArrayAR=new JSONArray();
                                    int p=0;
                                    for(int i=0;i<jsonArray.length();i++)
                                    {

                                            if(jsonArray.getJSONObject(i).getString("type").equals("public"))
                                            {
                                                if(jsonArray.getJSONObject(i).getString("is_multiple").equals("1")) {
                                                    myListData[p] = new ContestRecyclerDataClass("₹" + jsonArray.getJSONObject(i).getString("total_winning_amount"), "₹" + jsonArray.getJSONObject(i).getString("entry_fees"), jsonArray.getJSONObject(i).getString("contest_size") + " spots", "1,654 spots left", "2,500 Winners", "C", "M", jsonArray.getJSONObject(i).getString("contest_id"));
                                                }
                                                else
                                                {
                                                    myListData[p] = new ContestRecyclerDataClass("₹" + jsonArray.getJSONObject(i).getString("total_winning_amount"), "₹" + jsonArray.getJSONObject(i).getString("entry_fees"), jsonArray.getJSONObject(i).getString("contest_size") + " spots", "1,654 spots left", "2,500 Winners", "C", "S", jsonArray.getJSONObject(i).getString("contest_id"));

                                                }
                                                p=p+1;
                                            }

                                            else if(jsonArray.getJSONObject(i).getString("type").equals("private") && jsonArray.getJSONObject(i).getString("user_id").equals(sharedPreferences.getString("number","")))
                                            {
                                                if(jsonArray.getJSONObject(i).getString("is_multiple").equals("1")) {
                                                    myContestDatalist[q] = new MycontestRecyclerDataClass("₹" + jsonArray.getJSONObject(i).getString("total_winning_amount"), "₹" + jsonArray.getJSONObject(i).getString("entry_fees"), jsonArray.getJSONObject(i).getString("contest_size") + " spots", "1,654 spots left", "2,500 Winners", "C", "M", jsonArray.getJSONObject(i).getString("contest_id"));
                                                }
                                                else
                                                {
                                                    myContestDatalist[q] = new MycontestRecyclerDataClass("₹" + jsonArray.getJSONObject(i).getString("total_winning_amount"), "₹" + jsonArray.getJSONObject(i).getString("entry_fees"), jsonArray.getJSONObject(i).getString("contest_size") + " spots", "1,654 spots left", "2,500 Winners", "C", "S", jsonArray.getJSONObject(i).getString("contest_id"));

                                                }

                                                privatecontest=privatecontest+1;
                                                q=q+1;
                                            }

                                            //jsonArrayAR.put(jsonArray.getJSONObject(i));


                                    }
                                    tabLayout1.getTabAt(1).setText("MY CONTESTS ("+String.valueOf(privatecontest)+")");
                                    if(p>0)
                                    {
                                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                                        ContestRecyclerAdapter adapter = new ContestRecyclerAdapter(myListData,ContestsActivity.this,p);
                                        recyclerView.setHasFixedSize(true);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(ContestsActivity.this));
                                        recyclerView.setAdapter(adapter);
                                    }

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
                                 //   Toast.makeText(ContestsActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

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
                    if(q>0)
                    {
                        rv_my_contest.setLayoutManager(new LinearLayoutManager(ContestsActivity.this, LinearLayoutManager.VERTICAL, false));
                        rv_my_contest.setAdapter(new CustomMyContestAdapter(ContestsActivity.this));
                        MycontestRecyclerAdapter adapter1 = new MycontestRecyclerAdapter(myContestDatalist,q);

                        rv_my_contest.setLayoutManager(new LinearLayoutManager(ContestsActivity.this));
                        rv_my_contest.setAdapter(adapter1);
                    }
                }
                else if(tab.getPosition()==2)
                {
                    rl_for_hide.setVisibility(View.GONE);
                    rv_my_team.setVisibility(View.VISIBLE);
                    rv_my_contest.setVisibility(View.GONE);

                        ldata=0;
                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
                    {

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.GET, "http://rising11.com/apps/apis/get-create-team-players.php"+"?mobile="+sharedPreferences.getString("number","")+"&unique_id="+getIntent().getStringExtra("uid"), null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        try {
                                            JSONArray array=response.getJSONArray("list");
                                            if(response.getString("code").equals("1"))
                                            {
                                                teamnumber=new ArrayList<>();
                                                teamname1=new ArrayList<>();
                                                teamname2=new ArrayList<>();
                                                team1count=new ArrayList<>();
                                                team2count=new ArrayList<>();
                                                cname=new ArrayList<>();
                                                cimage=new ArrayList<>();
                                                vcname=new ArrayList<>();
                                                vcimage=new ArrayList<>();
                                                wk=new ArrayList<>();
                                                bat=new ArrayList<>();
                                                ar=new ArrayList<>();
                                                bowl=new ArrayList<>();

                                                for(int i=0;i<array.length();i=i+11)
                                                {
                                                    ldata=1;
                                                    int x=0;
                                                    int c=0;
                                                    c=c+1;
                                                    int wkc=0,batc=0,arc=0,bowlc=0,t1=0,t2=0;
                                                    String capimage="",capname="",vcapimage="",vcapname="",team1="IND",team2="IND";
                                                    for(int j=i;j<i+11;j++)
                                                    {
                                                        if(x==0)
                                                        {
                                                            team1=array.getJSONObject(j).getString("country");
                                                            x=x+1;
                                                        }
                                                        if(!array.getJSONObject(j).getString("country").equals(team1))
                                                        {
                                                            team2=   array.getJSONObject(j).getString("country");
                                                            t2=t2+1;
                                                            x=x+1;
                                                        }
                                                        if(array.getJSONObject(j).getString("tag").equals("captain"))
                                                        {
                                                            capname=array.getJSONObject(j).getString("player_name");
                                                            capimage=array.getJSONObject(j).getString("image");
                                                        }
                                                        if(array.getJSONObject(j).getString("tag").equals("vice captain"))
                                                        {
                                                            vcapname=array.getJSONObject(j).getString("player_name");
                                                            vcapimage=array.getJSONObject(j).getString("image");
                                                        }
                                                        if(array.getJSONObject(j).getString("country").equals(team1))
                                                        {
                                                            t1=t1+1;
                                                        }
                                                        if(array.getJSONObject(j).getString("role").equals("wk"))
                                                        {
                                                           wkc=wkc+1;
                                                        }
                                                        if(array.getJSONObject(j).getString("role").equals("bat"))
                                                        {
                                                            batc=batc+1;
                                                        }
                                                        if(array.getJSONObject(j).getString("role").equals("ar"))
                                                        {
                                                            arc=arc+1;
                                                        }
                                                        if(array.getJSONObject(j).getString("role").equals("bowl"))
                                                        {
                                                            bowlc=bowlc+1;
                                                        }

                                                    }
                                                    teamnumber.add(String.valueOf(c));
                                                    teamname1.add(team1);
                                                    teamname2.add(team2);
                                                    team1count.add(String.valueOf(t1));
                                                    team2count.add(String.valueOf(t2));
                                                    cimage.add(capimage);
                                                    cname.add(capname);
                                                    vcimage.add(vcapimage);
                                                    vcname.add(vcapname);
                                                    wk.add(String.valueOf(wkc));
                                                    bat.add(String.valueOf(batc));
                                                    ar.add(String.valueOf(arc));
                                                    bowl.add(String.valueOf(bowlc));
                                                }
                                                if(ldata==1)
                                                {
                                                    rv_my_team.setLayoutManager(new LinearLayoutManager(ContestsActivity.this, LinearLayoutManager.VERTICAL, false));
                                                    rv_my_team.setAdapter(new CustomMyTeamAdapter(ContestsActivity.this,teamnumber,teamname1,teamname2,team1count,team2count,cimage,cname,vcimage,vcname,wk,bat,ar,bowl));

                                                }


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



                        connected = true;
                    }
                    else
                    {
                       /* Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
                    }




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
                intent.putExtra("uid",getIntent().getStringExtra("uid"));
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
                intent.putExtra("uid",getIntent().getStringExtra("uid"));
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
