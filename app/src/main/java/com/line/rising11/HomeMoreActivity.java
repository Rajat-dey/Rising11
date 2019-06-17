package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeMoreActivity extends AppCompatActivity {
    RelativeLayout  invite_rl,invite_code_rl,whatsapp_rl,how_to_play_rl,fantasy_rl,helpdesk,job,aboutus,legality;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_more);
        setTitle("MORE");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        invite_rl=findViewById(R.id.invite_rl);
        invite_code_rl=findViewById(R.id.invite_code_rl);
        whatsapp_rl=findViewById(R.id.whatsapp_rl);
        how_to_play_rl=findViewById(R.id.how_to_play_rl);
        fantasy_rl=findViewById(R.id.fantasy_rl);
        helpdesk=findViewById(R.id.help_desk);
        job=findViewById(R.id.jobs);
        aboutus=findViewById(R.id.about);
        legality=findViewById(R.id.legality);

        invite_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,MyReferralsActivity.class);
                startActivity(intent);
            }
        });
        invite_code_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,Invite_Code_Activity.class);
               // Intent intent=new Intent(HomeMoreActivity.this,ContestsActivity.class);
                startActivity(intent);
            }
        });
        whatsapp_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,Whatsapp_Update_Activity.class);
                startActivity(intent);

            }
        });
        how_to_play_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,How_To_Play_Activity.class);
                startActivity(intent);
                
            }
        });


        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,Help_desk.class);
                startActivity(intent);

            }
        });


        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,jobs.class);
                startActivity(intent);

            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,about_us.class);
                startActivity(intent);

            }
        });

        legality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,legality.class);
                startActivity(intent);

            }
        });











        fantasy_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeMoreActivity.this,FantasyPointSystemActivity.class);
                startActivity(intent);

            }
        });
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
                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.GET, "http://rising11.com/apps/apis/get-upcoming-matches.php", null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        ArrayList<String> uid=new ArrayList<>();
                                        ArrayList<String> teamname1=new ArrayList<>();
                                        ArrayList<String> teamname2=new ArrayList<>();
                                        ArrayList<String> time=new ArrayList<>();


                                        try {

                                            if(response.getString("code").equals("1"))
                                            {
                                                JSONObject obj=response.getJSONObject("data");
                                                JSONArray array=obj.getJSONArray("matches");


                                                JSONArray jsonArray=new JSONArray();

                                                for(int i=0;i<array.length();i++)
                                                {

                                                    uid.add(array.getJSONObject(i).getString("unique_id"));
                                                    teamname1.add(array.getJSONObject(i).getString("team-1"));
                                                    teamname2.add(array.getJSONObject(i).getString("team-2"));
                                                    time.add(array.getJSONObject(i).getString("dateTimeGMT"));



                                                }
                                                Intent intent=new Intent(HomeMoreActivity.this,MyMatchActivity.class);
                                                intent.putStringArrayListExtra("uid",uid);
                                                intent.putStringArrayListExtra("teamname1",teamname1);
                                                intent.putStringArrayListExtra("teamname2",teamname2);
                                                intent.putStringArrayListExtra("time",time);
                                                startActivity(intent);
                                            }
                                            else
                                            {
                                                Toast.makeText(HomeMoreActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

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
                        RestClient.getInstance(HomeMoreActivity.this).addToRequestQueue(jsonObjectRequest);



                        connected = true;
                    }
                    else
                    {
                        /*Snackbar.make(view, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
                    }

                }
                else if(menuItem.getItemId()==R.id.navigation_notifications)
                {

                }
                return true;
            }
        });


    }
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}
