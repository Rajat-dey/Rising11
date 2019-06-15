package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.line.rising11.adapters.CustomCapViceCapSelectionAdapter;
import com.paytm.pgsdk.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.paytm.pgsdk.easypay.manager.PaytmAssist.getContext;

public class CaptainViceCaptainSelectionActivity extends AppCompatActivity implements CustomCapViceCapSelectionAdapter.OnAddListner1 {

    private RecyclerView rvHomeMatches;
    ArrayList<String> pname,pimage,pteam,ctype,vctype,pid,puid,prole,pcredit,pteamnew;
    CustomCapViceCapSelectionAdapter customCapViceCapSelectionAdapter;
    SharedPreferences sharedPreferences;
    int c=-1,vc=-1;
    JSONArray playerarrayjson;
    JSONObject  playerobjectjson;
    private Button save_team;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain_vice_captain_selection);

        pname=getIntent().getStringArrayListExtra("pname");
        pimage=getIntent().getStringArrayListExtra("pimage");
        pteam=getIntent().getStringArrayListExtra("pteam");
        pid=getIntent().getStringArrayListExtra("pid");
        puid=getIntent().getStringArrayListExtra("puid");
        prole=getIntent().getStringArrayListExtra("prole");
        pteamnew=getIntent().getStringArrayListExtra("pteamnew");
        pcredit=getIntent().getStringArrayListExtra("pcredit");
        save_team=findViewById(R.id.save_team);
        ctype=new ArrayList<>();
        vctype=new ArrayList<>();
        sharedPreferences=getSharedPreferences("loginstatus", Context.MODE_PRIVATE);
        playerarrayjson=new JSONArray();

        playerobjectjson=new JSONObject();

        for(int i=0;i<11;i++)
        {
            ctype.add("0");
            vctype.add("0");
        }
        rvHomeMatches = findViewById(R.id.rv_cap_vice_cap);
        rvHomeMatches.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        customCapViceCapSelectionAdapter=new CustomCapViceCapSelectionAdapter(getContext(),pname,pimage,CaptainViceCaptainSelectionActivity.this,pteam,ctype,vctype);
        rvHomeMatches.setAdapter(customCapViceCapSelectionAdapter);

        save_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if( c<0 || vc<0)
                {

                }
                else
                {
                    for(int i=0;i<pname.size();i++)
                    {
                        playerobjectjson=new JSONObject();
                        try {
                            playerobjectjson.put("unique_id",puid.get(i));
                            playerobjectjson.put("player_id",pid.get(i));
                            playerobjectjson.put("player_name",pname.get(i));
                            playerobjectjson.put("team_name",pteamnew.get(i));
                            playerobjectjson.put("role",prole.get(i));
                            playerobjectjson.put("image",pimage.get(i));
                            playerobjectjson.put("credits",pcredit.get(i));

                            if(i==c || i==vc)
                            {
                                if(i==c)
                                {
                                    playerobjectjson.put("tag","captain");
                                }
                                else
                                {
                                    playerobjectjson.put("tag","vice captain");
                                }
                            }
                            else
                            {
                                playerobjectjson.put("tag","player");
                            }
                            playerarrayjson.put(i,playerobjectjson);



                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }

                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.GET, "http://rising11.com/apps/apis/add-user-team.php"+"?mobile="+sharedPreferences.getString("number","")+"&player_list="+playerarrayjson, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Log.d("Response: ", response.toString());
                                        //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                                        try {
                                            if(response.getString("code").equals("1"))
                                            {


                                                Toast.makeText(CaptainViceCaptainSelectionActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(CaptainViceCaptainSelectionActivity.this, HomeActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else
                                            {
                                                Toast.makeText(CaptainViceCaptainSelectionActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
                        RestClient.getInstance(CaptainViceCaptainSelectionActivity.this).addToRequestQueue(jsonObjectRequest);



                        connected = true;
                    }
                    else
                    {
                        Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                }
            }
        });
    }

    @Override
    public void onAddClick(int position,String type)
    {

        if(type.equals("c"))
        {
            ctype=new ArrayList<>();
            for(int i=0;i<11;i++)
            {
                if(i==position)
                {
                    ctype.add("1");
                    vctype.set(position,"0");


                }
                else {
                    ctype.add("0");

                }
                c=position;
            }

            rvHomeMatches = findViewById(R.id.rv_cap_vice_cap);
            rvHomeMatches.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            customCapViceCapSelectionAdapter=new CustomCapViceCapSelectionAdapter(getContext(),pname,pimage,CaptainViceCaptainSelectionActivity.this,pteam,ctype,vctype);
            rvHomeMatches.setAdapter(customCapViceCapSelectionAdapter);
        }
        else if(type.equals("vc"))
        {
            vctype=new ArrayList<>();
            for(int i=0;i<11;i++)
            {
                if(i==position)
                {
                    vctype.add("1");
                    ctype.set(position,"0");


                }
                else {
                    vctype.add("0");
                }
                vc=position;
            }

            rvHomeMatches = findViewById(R.id.rv_cap_vice_cap);
            rvHomeMatches.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            customCapViceCapSelectionAdapter=new CustomCapViceCapSelectionAdapter(getContext(),pname,pimage,CaptainViceCaptainSelectionActivity.this,pteam,ctype,vctype);
            rvHomeMatches.setAdapter(customCapViceCapSelectionAdapter);
        }
    }
}
