package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;
import com.line.rising11.adapters.CustomTeamSelectionAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamSelectionActivity extends AppCompatActivity  implements CustomTeamSelectionAdapter.OnAddListner{

    private RecyclerView rvTeamSelection;
    private Button btnContinue;
    private Button btnTeamPreview;
    private TextView tv_player_selected_count,tv_credits_left,howmuch_can_select,tv_title_team1,tv_title_team2,tv_team1_player_count,tv_team2_player_count;
    private ViewPager pageview;
    int tposition=0;
    Float credit_left=100.0f;
    int dataload=0;
    ProgressBar pb;
    String country1="India",country2="India";
    int selectedtotalplayer=0,selectwk=0,selectbat=0,selectar=0,selectbowl=0,team1count=0,team2count=0;
    ArrayList<String> listwk,listbat,listar,listbowl,listtype2;
    private TabItem t1,t2,t3,t4;


    int mCurrentPage=0;
    int flag=0;
    private PageviewPlayerAdapter pageviewPlayerAdapter;
    JSONArray jsonArrayWK , jsonArrayBAT, jsonArrayAR, jsonArrayBOWL;
    private TabLayout tab_home;
    ArrayList<String> pname,pimage,pteam,pid,puid,prole,pcredit,pteamnew,prole1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_selection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        btnContinue = findViewById(R.id.btn_continue);
        tv_title_team1=findViewById(R.id.tv_title_team1);
        tv_title_team2=findViewById(R.id.tv_title_team2);
        tv_team1_player_count=findViewById(R.id.tv_team1_player_count);
        tv_team2_player_count=findViewById(R.id.tv_team2_player_count);
        pb=findViewById(R.id.pb);
        pname=new ArrayList<>();
        pimage=new ArrayList<>();
        pteam=new ArrayList<>();
        pid=new ArrayList<>();
        puid=new ArrayList<>();
        prole=new ArrayList<>();
        pcredit=new ArrayList<>();
        pteamnew=new ArrayList<>();
        prole1=new ArrayList<>();
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectar>=1&&selectbat>=3&&selectbowl>=3&&selectwk>=1 && selectedtotalplayer==11 && team1count<=7 && team2count<=7)
                {
                    for(int i=0;i<listwk.size();i++)
                    {
                        if(listwk.get(i).equals("1"))
                        {
                            try {
                                pname.add( jsonArrayWK.getJSONObject(i).getString("player_name"));
                                pimage.add( jsonArrayWK.getJSONObject(i).getString("image"));
                                pteam.add( jsonArrayWK.getJSONObject(i).getString("team_name")+" - WK");
                                pteamnew.add( jsonArrayWK.getJSONObject(i).getString("team_name"));
                                pid.add( jsonArrayWK.getJSONObject(i).getString("player_id"));
                                prole1.add("wk");
                                puid.add( jsonArrayWK.getJSONObject(i).getString("unique_id"));
                                prole.add( jsonArrayWK.getJSONObject(i).getString("role"));
                                pcredit.add( jsonArrayWK.getJSONObject(i).getString("credits"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    for(int i=0;i<listbat.size();i++)
                    {
                        if(listbat.get(i).equals("1"))
                        {
                            try {
                                pname.add( jsonArrayBAT.getJSONObject(i).getString("player_name"));
                                pimage.add( jsonArrayBAT.getJSONObject(i).getString("image"));
                                pteam.add( jsonArrayBAT.getJSONObject(i).getString("team_name")+" - BAT");
                                pteamnew.add( jsonArrayBAT.getJSONObject(i).getString("team_name"));
                                pid.add( jsonArrayBAT.getJSONObject(i).getString("player_id"));
                                puid.add( jsonArrayBAT.getJSONObject(i).getString("unique_id"));
                                prole.add( jsonArrayBAT.getJSONObject(i).getString("role"));
                                pcredit.add( jsonArrayBAT.getJSONObject(i).getString("credits"));
                                prole1.add("bat");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    for(int i=0;i<listar.size();i++)
                    {
                        if(listar.get(i).equals("1"))
                        {
                            try {
                                pname.add( jsonArrayAR.getJSONObject(i).getString("player_name"));
                                pimage.add( jsonArrayAR.getJSONObject(i).getString("image"));
                                pteam.add( jsonArrayAR.getJSONObject(i).getString("team_name")+" - ALL");
                                pteamnew.add( jsonArrayAR.getJSONObject(i).getString("team_name"));
                                pid.add( jsonArrayAR.getJSONObject(i).getString("player_id"));
                                puid.add( jsonArrayAR.getJSONObject(i).getString("unique_id"));
                                prole.add( jsonArrayAR.getJSONObject(i).getString("role"));
                                pcredit.add( jsonArrayAR.getJSONObject(i).getString("credits"));
                                prole1.add("ar");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    for(int i=0;i<listbowl.size();i++)
                    {
                        if(listbowl.get(i).equals("1"))
                        {
                            try {
                                pname.add( jsonArrayBOWL.getJSONObject(i).getString("player_name"));
                                pimage.add( jsonArrayBOWL.getJSONObject(i).getString("image"));
                                pteam.add( jsonArrayBOWL.getJSONObject(i).getString("team_name")+" - BOWL");
                                pteamnew.add( jsonArrayBOWL.getJSONObject(i).getString("team_name"));
                                pid.add( jsonArrayBOWL.getJSONObject(i).getString("player_id"));
                                puid.add( jsonArrayBOWL.getJSONObject(i).getString("unique_id"));
                                prole.add( jsonArrayBOWL.getJSONObject(i).getString("role"));
                                pcredit.add( jsonArrayBOWL.getJSONObject(i).getString("credits"));
                                prole1.add("bowl");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    Intent intent = new Intent(TeamSelectionActivity.this, CaptainViceCaptainSelectionActivity.class);
                    intent.putStringArrayListExtra("pname",pname);
                    intent.putStringArrayListExtra("pimage",pimage);
                    intent.putStringArrayListExtra("pteam",pteam);
                    intent.putStringArrayListExtra("pteamnew",pteamnew);
                    intent.putStringArrayListExtra("pid",pid);
                    intent.putStringArrayListExtra("puid",puid);
                    intent.putStringArrayListExtra("prole",prole);
                    intent.putStringArrayListExtra("pcredit",pcredit);
                    intent.putStringArrayListExtra("prole1",prole1);
                    startActivity(intent);
                }
                else
                {
                  if(selectwk<1)
                  {
                      Toast.makeText(TeamSelectionActivity.this, "Please select at least one Player from Wicket Keeper", Toast.LENGTH_LONG).show();

                  }
                  else if(selectbat<3)
                  {
                      Toast.makeText(TeamSelectionActivity.this, "Please select at least three Player from Bating", Toast.LENGTH_LONG).show();

                  }
                  else if(selectar<1)
                  {
                      Toast.makeText(TeamSelectionActivity.this, "Please select at least one Player from All Rounder", Toast.LENGTH_LONG).show();

                  }
                  else if(selectbowl<3)
                  {
                      Toast.makeText(TeamSelectionActivity.this, "Please select at least three Player from Bowler", Toast.LENGTH_LONG).show();

                  }
                  else if(team1count>7)
                  {
                      Toast.makeText(TeamSelectionActivity.this, "Sorry, You can select maximum 7 player from first team", Toast.LENGTH_LONG).show();

                  }
                  else if(team2count>7)
                  {
                      Toast.makeText(TeamSelectionActivity.this, "Sorry, You can select maximum 7 player from second team", Toast.LENGTH_LONG).show();

                  }
                    else
                    {
                        Toast.makeText(TeamSelectionActivity.this, "Please select total 11 Player", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

        btnTeamPreview = findViewById(R.id.btn_team_preview);
        btnTeamPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ArrayList<String> pname1,pimage1,pteam1,prole1,pcredit1;
                    int wk=0,bat=0,ar=0,bowl=0;
                    pname1=new ArrayList<>();
                    pimage1=new ArrayList<>();
                    pteam1=new ArrayList<>();
                    prole1=new ArrayList<>();
                    pcredit1=new ArrayList<>();

                for(int i=0;i<listwk.size();i++)
                {
                    if(listwk.get(i).equals("1"))
                    {
                        try {
                            pname1.add( jsonArrayWK.getJSONObject(i).getString("player_name"));
                            pimage1.add( jsonArrayWK.getJSONObject(i).getString("image"));
                            pteam1.add( jsonArrayWK.getJSONObject(i).getString("team_name"));
                            prole1.add( "wk");
                            pcredit1.add( jsonArrayWK.getJSONObject(i).getString("credits"));
                            wk=wk+1;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                for(int i=0;i<listbat.size();i++)
                {
                    if(listbat.get(i).equals("1"))
                    {
                        try {
                            pname1.add( jsonArrayBAT.getJSONObject(i).getString("player_name"));
                            pimage1.add( jsonArrayBAT.getJSONObject(i).getString("image"));
                            pteam1.add( jsonArrayBAT.getJSONObject(i).getString("team_name"));
                            prole1.add( "bat");
                            pcredit1.add( jsonArrayBAT.getJSONObject(i).getString("credits"));
                            bat=bat+1;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                for(int i=0;i<listar.size();i++)
                {
                    if(listar.get(i).equals("1"))
                    {
                        try {
                            pname1.add( jsonArrayAR.getJSONObject(i).getString("player_name"));
                            pimage1.add( jsonArrayAR.getJSONObject(i).getString("image"));
                            pteam1.add( jsonArrayAR.getJSONObject(i).getString("team_name"));
                            prole1.add( "ar");
                            pcredit1.add( jsonArrayAR.getJSONObject(i).getString("credits"));
                            ar=ar+1;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                for(int i=0;i<listbowl.size();i++)
                {
                    if(listbowl.get(i).equals("1"))
                    {
                        try {
                            pname1.add( jsonArrayBOWL.getJSONObject(i).getString("player_name"));
                            pimage1.add( jsonArrayBOWL.getJSONObject(i).getString("image"));
                            pteam1.add( jsonArrayBOWL.getJSONObject(i).getString("team_name"));
                            prole1.add( "bowl");
                            pcredit1.add( jsonArrayBOWL.getJSONObject(i).getString("credits"));
                            bowl=bowl+1;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Intent intent = new Intent(TeamSelectionActivity.this, TeamPreviewActivity.class);
                intent.putStringArrayListExtra("pname1",pname1);
                intent.putStringArrayListExtra("pimage1",pimage1);
                intent.putStringArrayListExtra("pteam1",pteam1);
                intent.putStringArrayListExtra("prole1",prole1);
                intent.putStringArrayListExtra("pcredit1",pcredit1);
                intent.putExtra("c",String.valueOf(-1));
                intent.putExtra("vc",String.valueOf(-1));
                startActivity(intent);
            }
        });

        tab_home=findViewById(R.id.tb_home);
        tv_player_selected_count=findViewById(R.id.tv_player_selected_count);
        tv_credits_left=findViewById(R.id.tv_credits_left);
        howmuch_can_select=findViewById(R.id.howmuch_can_select);

        howmuch_can_select.setText("Pick 1 - 4 Wicket-Keepers");
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);


       /* pageview=findViewById(R.id.pageview);
        pageviewPlayerAdapter= new PageviewPlayerAdapter(getSupportFragmentManager());
        pageview.setAdapter(pageviewPlayerAdapter);

        pageview.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mCurrentPage=i;

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

*/


        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, "http://rising11.com/apps/apis/get-fantasy-squad.php?unique_id="+getIntent().getStringExtra("uid"), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                if(response.getString("code").equals("1"))
                                {
                                    dataload=1;
                                    JSONArray jsonArray=response.getJSONArray("players");
                                    jsonArrayWK=new JSONArray();
                                    jsonArrayBAT=new JSONArray();
                                    jsonArrayAR=new JSONArray();
                                    jsonArrayBOWL=new JSONArray();
                                    listwk=new ArrayList<>();
                                    listbat=new ArrayList<>();
                                    listar=new ArrayList<>();
                                    listbowl=new ArrayList<>();
                                    int p=0;
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        if(p==0)
                                        {
                                            country1=jsonArray.getJSONObject(i).getString("team_name");
                                            p=1;
                                        }
                                        else if(p!=2)
                                        {
                                            if(!jsonArray.getJSONObject(i).getString("team_name").equals(country1))
                                            {
                                                country2=jsonArray.getJSONObject(i).getString("team_name");
                                                p=2;
                                            }

                                        }

                                        if(jsonArray.getJSONObject(i).get("role").toString().equals("Bowler"))
                                        {
                                            jsonArrayBOWL.put(jsonArray.getJSONObject(i));

                                            listbowl.add("0");
                                        }
                                        else if(jsonArray.getJSONObject(i).get("role").toString().equals("Bowling allrounder")|| jsonArray.getJSONObject(i).get("role").toString().equals("Batting allrounder")||jsonArray.getJSONObject(i).get("role").toString().equals("Allrounder")||jsonArray.getJSONObject(i).get("role").toString().equals(""))
                                        {
                                            jsonArrayAR.put(jsonArray.getJSONObject(i));
                                            listar.add("0");
                                        }
                                        else if(jsonArray.getJSONObject(i).get("role").toString().equals("Wicketkeeper batsman"))
                                        {
                                            jsonArrayWK.put(jsonArray.getJSONObject(i));
                                            listwk.add("0");
                                        }
                                        else
                                        {
                                            jsonArrayBAT.put(jsonArray.getJSONObject(i));
                                            listbat.add("0");
                                        }
                                    }
                                    tv_title_team1.setText(country1);
                                    tv_title_team2.setText(country2);
                                    rvTeamSelection = findViewById(R.id.rv_team_selection);
                                    rvTeamSelection.setLayoutManager(new LinearLayoutManager(TeamSelectionActivity.this, LinearLayoutManager.VERTICAL, false));
                                    rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayWK,TeamSelectionActivity.this,listwk,credit_left,selectedtotalplayer,selectwk,4, tposition));

                                }
                                else
                                {
                                    Toast.makeText(TeamSelectionActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener()
                    {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error

                        }
                    });

            // Access the RequestQueue through your singleton class.
            RestClient.getInstance(TeamSelectionActivity.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            /*Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }


        tab_home.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                /*pageview.setCurrentItem(tab.getPosition());*/
                if(tab.getPosition()==0)
                {

                   if(dataload==1)
                   {
                       tposition=0;
                       howmuch_can_select.setText("Pick 1 - 4 Wicket-Keepers");
                       rvTeamSelection = findViewById(R.id.rv_team_selection);
                       rvTeamSelection.setLayoutManager(new LinearLayoutManager(TeamSelectionActivity.this, LinearLayoutManager.VERTICAL, false));
                       rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayWK,TeamSelectionActivity.this,listwk,credit_left,selectedtotalplayer,selectwk,4,tposition));
                       for(int i=0;i<listwk.size();i++)
                       {
                           Log.d("data WK LIST",listwk.get(i));
                       }
                   }
                }
                else if(tab.getPosition()==1)
                {
                        if(dataload==1)
                        {
                            howmuch_can_select.setText("Pick 3 - 6 Batsmen");
                            tposition=1;
                            rvTeamSelection = findViewById(R.id.rv_team_selection);
                            rvTeamSelection.setLayoutManager(new LinearLayoutManager(TeamSelectionActivity.this, LinearLayoutManager.VERTICAL, false));
                            rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayBAT,TeamSelectionActivity.this,listbat,credit_left,selectedtotalplayer,selectbat,6,tposition));

                        }
                }
                else if(tab.getPosition()==2)
                {
                   if(dataload==1)
                   {
                       tposition=2;
                       howmuch_can_select.setText("Pick 1 - 4 All-Rounders");
                       rvTeamSelection = findViewById(R.id.rv_team_selection);
                       rvTeamSelection.setLayoutManager(new LinearLayoutManager(TeamSelectionActivity.this, LinearLayoutManager.VERTICAL, false));
                       rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayAR,TeamSelectionActivity.this,listar,credit_left,selectedtotalplayer,selectar,4,tposition));

                   }
                }
                else if(tab.getPosition()==3)
                {
                    if(dataload==1)
                    {
                        tposition=3;
                        howmuch_can_select.setText("Pick 3 - 6 Bowlers");
                        rvTeamSelection = findViewById(R.id.rv_team_selection);
                        rvTeamSelection.setLayoutManager(new LinearLayoutManager(TeamSelectionActivity.this, LinearLayoutManager.VERTICAL, false));
                        rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayBOWL,TeamSelectionActivity.this,listbowl,credit_left,selectedtotalplayer,selectbowl,6,tposition));

                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });


    }


    @Override
    public void onAddClick(int position)
    {
        if(position==-1)
        {
            Toast.makeText(this, "Sorry, You Reached Maximum Limit Of Selecting Players", Toast.LENGTH_SHORT).show();
        }
        else if(position==-2)
        {
            Toast.makeText(this, "Soory, You have not sufficient credit left", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(tposition==0)
            {
                float credit=0.0f;
                try {
                    credit=Float.parseFloat(jsonArrayWK.getJSONObject(position).getString("credits"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(listwk.get(position).equals("0"))
                { String contry="India";
                    try {
                       contry =jsonArrayWK.getJSONObject(position).getString("team_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(contry.equals(country1))
                    {
                        team1count=team1count+1;
                    }
                    else {
                        team2count=team2count+1;
                    }
                    listwk.set(position, "1");
                    selectedtotalplayer=selectedtotalplayer+1;
                    selectwk=selectwk+1;
                    credit_left=credit_left-credit;
                }
                else
                {
                    String contry="India";
                    try {
                        contry =jsonArrayWK.getJSONObject(position).getString("team_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(contry.equals(country1))
                    {
                        team1count=team1count-1;
                    }
                    else {
                        team2count=team2count-1;
                    }
                    listwk.set(position, "0");
                    selectedtotalplayer=selectedtotalplayer-1;
                    selectwk=selectwk-1;
                    credit_left=credit_left+credit;

                }
            }
            else if(tposition==1)
            {
                float credit=0.0f;
                try {
                    credit=Float.parseFloat(jsonArrayBAT.getJSONObject(position).getString("credits"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(listbat.get(position).equals("0")) {
                    String contry="India";
                    try {
                        contry =jsonArrayBAT.getJSONObject(position).getString("team_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(contry.equals(country1))
                    {
                        team1count=team1count+1;
                    }
                    else {
                        team2count=team2count+1;
                    }
                    listbat.set(position, "1");
                    selectedtotalplayer=selectedtotalplayer+1;
                    selectbat=selectbat+1;
                    credit_left=credit_left-credit;
                }
                else
                {
                    String contry="India";
                    try {
                        contry =jsonArrayBAT.getJSONObject(position).getString("team_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(contry.equals(country1))
                    {
                        team1count=team1count-1;
                    }
                    else {
                        team2count=team2count-1;
                    }
                    listbat.set(position, "0");
                    selectedtotalplayer=selectedtotalplayer-1;
                    selectbat=selectbat-1;
                    credit_left=credit_left+credit;
                }
            }
            else if(tposition==2)
            {
                float credit=0.0f;
                try {
                    credit=Float.parseFloat(jsonArrayAR.getJSONObject(position).getString("credits"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(listar.get(position).equals("0")) {
                    String contry="India";
                    try {
                        contry =jsonArrayAR.getJSONObject(position).getString("team_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(contry.equals(country1))
                    {
                        team1count=team1count+1;
                    }
                    else {
                        team2count=team2count+1;
                    }
                    listar.set(position, "1");
                    selectedtotalplayer=selectedtotalplayer+1;
                    selectar=selectar+1;
                    credit_left=credit_left-credit;
                }
                else
                {
                    listar.set(position, "0");
                    selectedtotalplayer=selectedtotalplayer-1;
                    selectar=selectar-1;
                    credit_left=credit_left+credit;
                    String contry="India";
                    try {
                        contry =jsonArrayAR.getJSONObject(position).getString("team_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(contry.equals(country1))
                    {
                        team1count=team1count-1;
                    }
                    else {
                        team2count=team2count-1;
                    }
                }
            }
            else if(tposition==3)
            {
                float credit=0.0f;
                try {
                    credit=Float.parseFloat(jsonArrayBOWL.getJSONObject(position).getString("credits"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(listbowl.get(position).equals("0"))
                {
                    listbowl.set(position, "1");
                    selectedtotalplayer=selectedtotalplayer+1;
                    selectbowl=selectbowl+1;
                    credit_left=credit_left-credit;
                    String contry="India";
                    try {
                        contry =jsonArrayBOWL.getJSONObject(position).getString("team_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(contry.equals(country1))
                    {
                        team1count=team1count+1;
                    }
                    else {
                        team2count=team2count+1;
                    }
                }
                else
                {
                    listbowl.set(position, "0");
                    selectedtotalplayer=selectedtotalplayer-1;
                    selectbowl=selectbowl-1;
                    credit_left=credit_left+credit;
                    String contry="India";
                    try {
                        contry =jsonArrayBOWL.getJSONObject(position).getString("team_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(contry.equals(country1))
                    {
                        team1count=team1count-1;
                    }
                    else {
                        team2count=team2count-1;
                    }
                }
            }
            tv_player_selected_count.setText(String.valueOf(selectedtotalplayer));
            tv_credits_left.setText(String.valueOf(credit_left));
            tab_home.getTabAt(0).setText("WK ("+String.valueOf(selectwk)+")");
            tab_home.getTabAt(1).setText("BAT ("+String.valueOf(selectbat)+")");
            tab_home.getTabAt(2).setText("AR ("+String.valueOf(selectar)+")");
            tab_home.getTabAt(3).setText("BOWL ("+String.valueOf(selectbowl)+")");
            tv_team1_player_count.setText(String.valueOf(team1count));
            tv_team2_player_count.setText(String.valueOf(team2count));
            pb.setProgress(selectedtotalplayer);

        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
