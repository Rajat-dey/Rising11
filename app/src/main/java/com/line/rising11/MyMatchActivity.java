package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.line.rising11.adapters.CustomMatchAdapter;
import com.line.rising11.adapters.CustomMyMatchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyMatchActivity extends AppCompatActivity {

    Button upcoming_matches;
    RecyclerView rv_my_matches;
    LinearLayout ll_no_matches;
    TabLayout tablayout2;
    ArrayList<String> uid,teamname1,teamname2,time,joincontestlist,tabtype;
    ArrayList<String> uid1,teamname11,teamname21,time1,joincontestlist1,tabtype1;
    SharedPreferences sharedPreferences;
    JSONArray joincontestJSON;
    int tab1=0,tab2=0,tab3=0;
    int tabcount=0;
    int flagjoinany=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_match);
        setTitle("MY MATCHES");

        upcoming_matches=findViewById(R.id.upcoming_matches);
        rv_my_matches=findViewById(R.id.rv_my_matches);
        ll_no_matches=findViewById(R.id.ll_no_matches);
        tablayout2=findViewById(R.id.tablayout2);
        ll_no_matches.setVisibility(View.GONE);
        sharedPreferences=getSharedPreferences("loginstatus", Context.MODE_PRIVATE);

       uid=getIntent().getStringArrayListExtra("uid");
       teamname1=getIntent().getStringArrayListExtra("teamname1");
       teamname2=getIntent().getStringArrayListExtra("teamname2");
       time=getIntent().getStringArrayListExtra("time");
       joincontestlist=new ArrayList<>();
       tabtype=new ArrayList<>();

       for(int i=0;i<time.size();i++)
       {
           Date date = null;
           try {
               SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
               SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
               SimpleDateFormat outputFormat1 = new SimpleDateFormat("HH:mm:ss");
               date = inputFormat.parse(time.get(i));
               String formattedDate = outputFormat.format(date);
               String formattedDate1 = outputFormat1.format(date);
               Calendar cal = Calendar.getInstance();
               String currentdate=outputFormat.format(cal.getTime());
               String currenttime=outputFormat1.format(cal.getTime());
               Date time1=outputFormat1.parse(formattedDate1);
               Date time2=outputFormat1.parse(currenttime);
               long diffInMillies = outputFormat.parse(formattedDate).getTime() - outputFormat.parse(currentdate).getTime();
               Long tday = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
               Long ttime = (time1.getTime() - time2.getTime())+330*60*1000;
               if(tday>0)
               {
                   tabtype.add("upcoming");
                   tab1=1;
               }
               else if(tday==0)
               {
                   if(ttime>=0)
                   {
                       tabtype.add("upcoming");
                       tab1=1;
                   }
                   else
                   {
                       tabtype.add("live");
                       tab2=1;
                   }
               }
               else
               {
                   tabtype.add("live");
                   tab2=1;
               }

           } catch (ParseException e) {
               e.printStackTrace();
           }
       }





        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, "http://rising11.com/apps/apis/get-reg-contest.php"+"?mobile="+sharedPreferences.getString("number",""), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            joincontestJSON=new JSONArray();

                            try {

                                if(response.getString("code").equals("1"))
                                {

                                    joincontestJSON=response.getJSONArray("contests");



                                    for(int i=0;i<uid.size();i++)
                                    { int n=0;
                                        for(int j=0;j<joincontestJSON.length();j++)
                                        {
                                            if(joincontestJSON.getJSONObject(j).getString("unique_id").equals(uid.get(i)))
                                            {
                                                n=n+1;
                                                flagjoinany=1;
                                            }
                                        }

                                        joincontestlist.add(String.valueOf(n));
                                        Toast.makeText(MyMatchActivity.this, String.valueOf(n), Toast.LENGTH_SHORT).show();
                                    }
                                    if(flagjoinany==1 && tab1==1)
                                    {
                                        uid1=new ArrayList<>();
                                        teamname11=new ArrayList<>();
                                        teamname21=new ArrayList<>();
                                        time1=new ArrayList<>();
                                        joincontestlist1=new ArrayList<>();
                                        tabtype1=new ArrayList<>();

                                        for(int i=0;i<tabtype.size();i++)
                                        {
                                            if(tabtype.get(i).equals("upcoming"))
                                            {
                                                uid1.add(uid.get(i));
                                                teamname11.add(teamname1.get(i));
                                                teamname21.add(teamname2.get(i));
                                                time1.add(time.get(i));
                                                joincontestlist1.add(joincontestlist.get(i));
                                                tabtype1.add(tabtype.get(i));
                                            }
                                        }

                                        rv_my_matches.setLayoutManager(new LinearLayoutManager(MyMatchActivity.this, LinearLayoutManager.VERTICAL, false));
                                        rv_my_matches.setAdapter(new CustomMyMatchAdapter(MyMatchActivity.this,uid1,teamname11,teamname21,time1,joincontestlist1,tabtype1,tabcount));

                                    }
                                    else
                                    {
                                        rv_my_matches.setVisibility(View.GONE);
                                        ll_no_matches.setVisibility(View.VISIBLE);
                                    }
                                }
                                else
                                {
                                    rv_my_matches.setVisibility(View.GONE);
                                    ll_no_matches.setVisibility(View.VISIBLE);
                                    Toast.makeText(MyMatchActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

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
            RestClient.getInstance(MyMatchActivity.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
                        /*Snackbar.make(view, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
        }



        tablayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0)
                {
                    tabcount=0;
                    if(tab1==1)
                    {
                        if(flagjoinany==1)
                        {
                            ll_no_matches.setVisibility(View.GONE);
                            rv_my_matches.setVisibility(View.VISIBLE);
                            rv_my_matches=findViewById(R.id.rv_my_matches);
                            uid1=new ArrayList<>();
                            teamname11=new ArrayList<>();
                            teamname21=new ArrayList<>();
                            time1=new ArrayList<>();
                            joincontestlist1=new ArrayList<>();
                            tabtype1=new ArrayList<>();

                            for(int i=0;i<tabtype.size();i++)
                            {
                                if(tabtype.get(i).equals("upcoming"))
                                {
                                    uid1.add(uid.get(i));
                                    teamname11.add(teamname1.get(i));
                                    teamname21.add(teamname2.get(i));
                                    time1.add(time.get(i));
                                    joincontestlist1.add(joincontestlist.get(i));
                                    tabtype1.add(tabtype.get(i));
                                }
                            }

                            rv_my_matches.setLayoutManager(new LinearLayoutManager(MyMatchActivity.this, LinearLayoutManager.VERTICAL, false));
                            rv_my_matches.setAdapter(new CustomMyMatchAdapter(MyMatchActivity.this,uid1,teamname11,teamname21,time1,joincontestlist1,tabtype1,tabcount));
                        }
                        else
                        {
                            ll_no_matches.setVisibility(View.VISIBLE);
                            rv_my_matches.setVisibility(View.GONE);
                        }
                    }
                    else
                    {
                        ll_no_matches.setVisibility(View.VISIBLE);
                        rv_my_matches.setVisibility(View.GONE);
                    }
                }
                else if(tab.getPosition()==1)
                {
                    tabcount=1;
                    if(tab2==1)
                    {
                        if(flagjoinany==1)
                        {
                            ll_no_matches.setVisibility(View.GONE);
                            rv_my_matches.setVisibility(View.VISIBLE);
                            rv_my_matches=findViewById(R.id.rv_my_matches);
                            uid1=new ArrayList<>();
                            teamname11=new ArrayList<>();
                            teamname21=new ArrayList<>();
                            time1=new ArrayList<>();
                            joincontestlist1=new ArrayList<>();
                            tabtype1=new ArrayList<>();

                            for(int i=0;i<tabtype.size();i++)
                            {
                                if(tabtype.get(i).equals("live"))
                                {
                                    uid1.add(uid.get(i));
                                    teamname11.add(teamname1.get(i));
                                    teamname21.add(teamname2.get(i));
                                    time1.add(time.get(i));
                                    joincontestlist1.add(joincontestlist.get(i));
                                    tabtype1.add(tabtype.get(i));
                                }
                            }

                            rv_my_matches.setLayoutManager(new LinearLayoutManager(MyMatchActivity.this, LinearLayoutManager.VERTICAL, false));
                            rv_my_matches.setAdapter(new CustomMyMatchAdapter(MyMatchActivity.this,uid1,teamname11,teamname21,time1,joincontestlist1,tabtype1,tabcount));
                        }
                        else
                        {
                            ll_no_matches.setVisibility(View.VISIBLE);
                            rv_my_matches.setVisibility(View.GONE);
                        }
                    }
                    else
                    {
                        ll_no_matches.setVisibility(View.VISIBLE);
                        rv_my_matches.setVisibility(View.GONE);
                    }

                }
                else if(tab.getPosition()==2)
                {
                    tabcount=2;
                    if(tab3==1)
                    {
                        if(flagjoinany==1)
                        {
                            ll_no_matches.setVisibility(View.GONE);
                            rv_my_matches.setVisibility(View.VISIBLE);
                            rv_my_matches=findViewById(R.id.rv_my_matches);
                            rv_my_matches.setLayoutManager(new LinearLayoutManager(MyMatchActivity.this, LinearLayoutManager.VERTICAL, false));
                            rv_my_matches.setAdapter(new CustomMyMatchAdapter(MyMatchActivity.this,uid,teamname1,teamname2,time,joincontestlist,tabtype,tabcount));

                        }
                        else
                        {
                            ll_no_matches.setVisibility(View.VISIBLE);
                            rv_my_matches.setVisibility(View.GONE);
                        }
                    }
                    else
                    {
                        ll_no_matches.setVisibility(View.VISIBLE);
                        rv_my_matches.setVisibility(View.GONE);
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

        upcoming_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MyMatchActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                if(menuItem.getItemId()==R.id.navigation_home)
                {
                    finish();
                }
                else if (menuItem.getItemId()==R.id.navigation_dashboard)
                {

                }
                else if(menuItem.getItemId()==R.id.navigation_notifications)
                {
                    Intent intent=new Intent(MyMatchActivity.this,HomeMoreActivity.class);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
