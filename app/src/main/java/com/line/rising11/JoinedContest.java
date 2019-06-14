package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class JoinedContest extends AppCompatActivity {

    TextView playerpoints,TM1,TM2,match_status,score1,score2,statics;

    LinearLayout joinedcontest;

    String scorepart1,scorepart2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joined_contest);
        setTitle("JOINED CONTEST");


        playerpoints=findViewById(R.id.player_points);

        joinedcontest=findViewById(R.id.joined_contest);


        TM1=findViewById(R.id.TM1);
        TM2=findViewById(R.id.TM2);
        match_status=findViewById(R.id.match_status);
        score1=findViewById(R.id.score1);
        score2=findViewById(R.id.score2);
        statics=findViewById(R.id.statics);


        joinedcontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(JoinedContest.this,Contest.class);
                startActivity(intent);
            }
        });


        playerpoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(JoinedContest.this,player_points.class);
                startActivity(intent);
            }
        });





        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, getString(R.string.Cric_cricket_score)+"&unique_id=1144494", null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Response: ", response.toString());
                            //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                            try {
                                if(response != null)
                                {


                                    TM1.setText(response.getString("team-1"));
                                    TM2.setText(response.getString("team-2"));

                                    if(!response.getString("matchStarted").equals("true")) {
                                        match_status.setText("COMPLETED");
                                    }


                                    String ScoreData = response.getString("description");

                                    String[] parts = ScoreData.split("v");

                                    scorepart1 =  parts[0];
                                    scorepart2= parts[1];


                                    score1.setText(scorepart1);
                                    score2.setText(scorepart2);


                                    statics.setText(response.getString("stat"));


                                    /*editor.putString("login","yes");
                                    editor.putString("number",email.getText().toString().trim());
                                    editor.commit();*/
                                    Toast.makeText(JoinedContest.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                    /*Intent intent = new Intent(MySettingsActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();*/
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
            RestClient.getInstance(JoinedContest.this).addToRequestQueue(jsonObjectRequest.setShouldCache(false));



            connected = true;
        }
        else
        {
            /*Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }








    }
}
