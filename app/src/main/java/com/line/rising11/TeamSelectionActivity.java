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
    private TextView tv_player_selected_count,tv_credits_left,howmuch_can_select;
    private ViewPager pageview;
    int tposition=0;
    Float credit_left=100.0f;
    int selectedtotalplayer=0,selectwk=0,selectbat=0,selectar=0,selectbowl=0;
    ArrayList<String> listwk,listbat,listar,listbowl;
    private TabItem t1,t2,t3,t4;


    int mCurrentPage=0;
    int flag=0;
    private PageviewPlayerAdapter pageviewPlayerAdapter;
    JSONArray jsonArrayWK , jsonArrayBAT, jsonArrayAR, jsonArrayBOWL;
    private TabLayout tab_home;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_selection);

        /*rvTeamSelection = findViewById(R.id.rv_team_selection);
        rvTeamSelection.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(new JSONArray()));*/

        btnContinue = findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamSelectionActivity.this, CaptainViceCaptainSelectionActivity.class);
                startActivity(intent);
            }
        });

        btnTeamPreview = findViewById(R.id.btn_team_preview);
        btnTeamPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamSelectionActivity.this, TeamPreviewActivity.class);
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
                    (Request.Method.GET, "http://rising11.com/apps/apis/get-fantasy-squad.php?unique_id=1144501", null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                if(response.getString("code").equals("1"))
                                {
                                    JSONArray jsonArray=response.getJSONArray("players");
                                    jsonArrayWK=new JSONArray();
                                    jsonArrayBAT=new JSONArray();
                                    jsonArrayAR=new JSONArray();
                                    jsonArrayBOWL=new JSONArray();
                                    listwk=new ArrayList<>();
                                    listbat=new ArrayList<>();
                                    listar=new ArrayList<>();
                                    listbowl=new ArrayList<>();
                                    for(int i=0;i<jsonArray.length();i++)
                                    {

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
                                    rvTeamSelection = findViewById(R.id.rv_team_selection);
                                    rvTeamSelection.setLayoutManager(new LinearLayoutManager(TeamSelectionActivity.this, LinearLayoutManager.VERTICAL, false));
                                    rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayWK,TeamSelectionActivity.this,listwk,credit_left,selectedtotalplayer,selectwk,4));

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

                    tposition=0;
                    howmuch_can_select.setText("Pick 1 - 4 Wicket-Keepers");
                    rvTeamSelection = findViewById(R.id.rv_team_selection);
                    rvTeamSelection.setLayoutManager(new LinearLayoutManager(TeamSelectionActivity.this, LinearLayoutManager.VERTICAL, false));
                    rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayWK,TeamSelectionActivity.this,listwk,credit_left,selectedtotalplayer,selectwk,4));
                    for(int i=0;i<listwk.size();i++)
                    {
                        Log.d("data WK LIST",listwk.get(i));
                    }
                }
                else if(tab.getPosition()==1)
                {
                    howmuch_can_select.setText("Pick 3 - 6 Batsmen");
                    tposition=1;
                    rvTeamSelection = findViewById(R.id.rv_team_selection);
                    rvTeamSelection.setLayoutManager(new LinearLayoutManager(TeamSelectionActivity.this, LinearLayoutManager.VERTICAL, false));
                    rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayBAT,TeamSelectionActivity.this,listbat,credit_left,selectedtotalplayer,selectbat,6));
                }
                else if(tab.getPosition()==2)
                {
                    tposition=2;
                    howmuch_can_select.setText("Pick 1 - 4 All-Rounders");
                    rvTeamSelection = findViewById(R.id.rv_team_selection);
                    rvTeamSelection.setLayoutManager(new LinearLayoutManager(TeamSelectionActivity.this, LinearLayoutManager.VERTICAL, false));
                    rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayAR,TeamSelectionActivity.this,listar,credit_left,selectedtotalplayer,selectar,4));
                }
                else if(tab.getPosition()==3)
                {
                    tposition=3;
                    howmuch_can_select.setText("Pick 3 - 6 Bowlers");
                    rvTeamSelection = findViewById(R.id.rv_team_selection);
                    rvTeamSelection.setLayoutManager(new LinearLayoutManager(TeamSelectionActivity.this, LinearLayoutManager.VERTICAL, false));
                    rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayBOWL,TeamSelectionActivity.this,listbowl,credit_left,selectedtotalplayer,selectbowl,6));
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
                {

                    listwk.set(position, "1");
                    selectedtotalplayer=selectedtotalplayer+1;
                    selectwk=selectwk+1;
                    credit_left=credit_left-credit;
                }
                else
                {

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
                    listbat.set(position, "1");
                    selectedtotalplayer=selectedtotalplayer+1;
                    selectbat=selectbat+1;
                    credit_left=credit_left-credit;
                }
                else
                {
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
                }
                else
                {
                    listbowl.set(position, "0");
                    selectedtotalplayer=selectedtotalplayer-1;
                    selectbowl=selectbowl-1;
                    credit_left=credit_left+credit;
                }
            }
            tv_player_selected_count.setText(String.valueOf(selectedtotalplayer));
            tv_credits_left.setText(String.valueOf(credit_left));
            tab_home.getTabAt(0).setText("WK ("+String.valueOf(selectwk)+")");
            tab_home.getTabAt(1).setText("BAT ("+String.valueOf(selectbat)+")");
            tab_home.getTabAt(2).setText("AR ("+String.valueOf(selectar)+")");
            tab_home.getTabAt(3).setText("BOWL ("+String.valueOf(selectbowl)+")");

        }

    }
}
