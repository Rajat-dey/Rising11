package com.line.rising11;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContestsFilterActivity extends AppCompatActivity {
    ContestFilterRecyclerDataClass[] myListData;
    private TextView price,spots,winners,entry,contestavail;
    RecyclerView recyclerView;
    int loaddata=0;
    int p=0;
    ContestFilterRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests_filter);
        setTitle("CONTESTS");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        price=findViewById(R.id.price);
        spots=findViewById(R.id.spots);
        winners=findViewById(R.id.winners);
        entry=findViewById(R.id.entry);
        contestavail=findViewById(R.id.contestavail);


        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, "http://rising11.com/apps/apis/get-all-contest.php?unique_id="+getIntent().getStringExtra("uid"), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                if(response.getString("code").equals("1"))
                                {

                                    JSONArray jsonArray=response.getJSONArray("contests");
                                    myListData = new ContestFilterRecyclerDataClass[jsonArray.length()];

                                    JSONArray jsonArrayAR=new JSONArray();
                                    contestavail.setText(String.valueOf(jsonArray.length())+" Contests Available");

                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        if(jsonArray.getJSONObject(i).getString("type").equals("public")) {

                                            if(jsonArray.getJSONObject(i).getString("is_multiple").equals("1")) {
                                                myListData[p] = new ContestFilterRecyclerDataClass(jsonArray.getJSONObject(i).getString("total_winning_amount"), jsonArray.getJSONObject(i).getString("entry_fees"), jsonArray.getJSONObject(i).getString("contest_size"), "1654", "2500", "C", "M");
                                            }
                                            else
                                            {
                                                myListData[p] = new ContestFilterRecyclerDataClass(jsonArray.getJSONObject(i).getString("total_winning_amount"), jsonArray.getJSONObject(i).getString("entry_fees"), jsonArray.getJSONObject(i).getString("contest_size"), "1654", "2500", "C", "S");

                                            }
                                            p=p+1;
                                            loaddata=1;
                                        }
                                        //jsonArrayAR.put(jsonArray.getJSONObject(i));


                                    }
                                    if(loaddata==1) {
                                        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                                        adapter = new ContestFilterRecyclerAdapter(myListData,p);
                                        recyclerView.setHasFixedSize(true);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(ContestsFilterActivity.this));
                                        recyclerView.setAdapter(adapter);
                                    }

                                }
                                else
                                {
                                    Toast.makeText(ContestsFilterActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

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

            RestClient.getInstance(ContestsFilterActivity.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            /*Snackbar.make(, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }

        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                price.setTextSize(15.0f);
                price.setTextColor(Color.BLUE);
                spots.setTextColor(Color.BLACK);
                spots.setTextSize(14.0f);
                winners.setTextColor(Color.BLACK);
                winners.setTextSize(14.0f);
                entry.setTextColor(Color.BLACK);
                entry.setTextSize(14.0f);

                if(loaddata==1)
                {
                    String tempprize=null,tempentry=null,tempwinners=null,tempspotstotal=null,tempspotsleft=null;
                    boolean swapped;
                    for (int i = 0; i < p - 1; i++)
                    {
                        swapped = false;
                        for (int j = 0; j < p - i - 1; j++)
                        {

                            if ( Float.parseFloat(myListData[j].getPrize())>Float.parseFloat(myListData[j+1].getPrize()) )
                            {
                                tempprize=myListData[j].getPrize();
                                myListData[j].setPrize(myListData[j+1].getPrize());
                                myListData[j+1].setPrize(tempprize);

                                tempentry=myListData[j].getEntry();
                                myListData[j].setEntry(myListData[j+1].getEntry());
                                myListData[j+1].setEntry(tempentry);

                                tempwinners=myListData[j].getWinner();
                                myListData[j].setWinner(myListData[j+1].getWinner());
                                myListData[j+1].setWinner(tempwinners);

                                tempspotstotal=myListData[j].getMax_pb_val();
                                myListData[j].setMax_pb_val(myListData[j+1].getMax_pb_val());
                                myListData[j+1].setMax_pb_val(tempspotstotal);

                                tempspotsleft=myListData[j].getMin_pb_val();
                                myListData[j].setMin_pb_val(myListData[j+1].getMin_pb_val());
                                myListData[j+1].setMin_pb_val(tempspotsleft);


                                swapped = true;
                            }
                        }

                        if (swapped == false)
                            break;
                    }
                    adapter.notifyDataSetChanged();

                }
            }
        });

        spots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                spots.setTextSize(15.0f);
                spots.setTextColor(Color.BLUE);
                price.setTextColor(Color.BLACK);
                price.setTextSize(14.0f);
                winners.setTextColor(Color.BLACK);
                winners.setTextSize(14.0f);
                entry.setTextColor(Color.BLACK);
                entry.setTextSize(14.0f);
               if(loaddata==1)
               {
                   String tempprize=null,tempentry=null,tempwinners=null,tempspotstotal=null,tempspotsleft=null;
                   boolean swapped;
                   for (int i = 0; i < p - 1; i++)
                   {
                       swapped = false;
                       for (int j = 0; j < p - i - 1; j++)
                       {
                           if (Integer.parseInt(myListData[j].getMax_pb_val()) > Integer.parseInt(myListData[j+1].getMax_pb_val()))
                           {
                               // swap arr[j] and arr[j+1]

                               tempprize=myListData[j].getPrize();
                               myListData[j].setPrize(myListData[j+1].getPrize());
                               myListData[j+1].setPrize(tempprize);

                               tempentry=myListData[j].getEntry();
                               myListData[j].setEntry(myListData[j+1].getEntry());
                               myListData[j+1].setEntry(tempentry);

                               tempwinners=myListData[j].getWinner();
                               myListData[j].setWinner(myListData[j+1].getWinner());
                               myListData[j+1].setWinner(tempwinners);

                               tempspotstotal=myListData[j].getMax_pb_val();
                               myListData[j].setMax_pb_val(myListData[j+1].getMax_pb_val());
                               myListData[j+1].setMax_pb_val(tempspotstotal);

                               tempspotsleft=myListData[j].getMin_pb_val();
                               myListData[j].setMin_pb_val(myListData[j+1].getMin_pb_val());
                               myListData[j+1].setMin_pb_val(tempspotsleft);


                               swapped = true;
                           }
                       }

                       // IF no two elements were
                       // swapped by inner loop, then break
                       if (swapped == false)
                           break;
                   }
                   adapter.notifyDataSetChanged();
               }
            }
        });

        winners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                winners.setTextSize(15.0f);
                winners.setTextColor(Color.BLUE);
                spots.setTextColor(Color.BLACK);
                spots.setTextSize(14.0f);
                price.setTextColor(Color.BLACK);
                price.setTextSize(14.0f);
                entry.setTextColor(Color.BLACK);
                entry.setTextSize(14.0f);
                if(loaddata==1)
                {
                    String tempprize=null,tempentry=null,tempwinners=null,tempspotstotal=null,tempspotsleft=null;
                    boolean swapped;
                    for (int i = 0; i <p - 1; i++)
                    {
                        swapped = false;
                        for (int j = 0; j < p - i - 1; j++)
                        {
                            if (Integer.parseInt(myListData[j].getWinner()) > Integer.parseInt(myListData[j+1].getWinner()))
                            {
                                // swap arr[j] and arr[j+1]

                                tempprize=myListData[j].getPrize();
                                myListData[j].setPrize(myListData[j+1].getPrize());
                                myListData[j+1].setPrize(tempprize);

                                tempentry=myListData[j].getEntry();
                                myListData[j].setEntry(myListData[j+1].getEntry());
                                myListData[j+1].setEntry(tempentry);

                                tempwinners=myListData[j].getWinner();
                                myListData[j].setWinner(myListData[j+1].getWinner());
                                myListData[j+1].setWinner(tempwinners);

                                tempspotstotal=myListData[j].getMax_pb_val();
                                myListData[j].setMax_pb_val(myListData[j+1].getMax_pb_val());
                                myListData[j+1].setMax_pb_val(tempspotstotal);

                                tempspotsleft=myListData[j].getMin_pb_val();
                                myListData[j].setMin_pb_val(myListData[j+1].getMin_pb_val());
                                myListData[j+1].setMin_pb_val(tempspotsleft);


                                swapped = true;
                            }
                        }

                        // IF no two elements were
                        // swapped by inner loop, then break
                        if (swapped == false)
                            break;
                    }
                    adapter.notifyDataSetChanged();
                }

            }
        });

        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                entry.setTextSize(15.0f);
                entry.setTextColor(Color.BLUE);
                spots.setTextColor(Color.BLACK);
                spots.setTextSize(14.0f);
                winners.setTextColor(Color.BLACK);
                winners.setTextSize(14.0f);
                price.setTextColor(Color.BLACK);
                price.setTextSize(14.0f);
                if(loaddata==1)
                {
                    String tempprize=null,tempentry=null,tempwinners=null,tempspotstotal=null,tempspotsleft=null;
                    boolean swapped;
                    for (int i = 0; i < p - 1; i++)
                    {
                        swapped = false;
                        for (int j = 0; j < p - i - 1; j++)
                        {
                            if (Float.parseFloat(myListData[j].getEntry()) > Float.parseFloat(myListData[j+1].getEntry()))
                            {
                                // swap arr[j] and arr[j+1]

                                tempprize=myListData[j].getPrize();
                                myListData[j].setPrize(myListData[j+1].getPrize());
                                myListData[j+1].setPrize(tempprize);

                                tempentry=myListData[j].getEntry();
                                myListData[j].setEntry(myListData[j+1].getEntry());
                                myListData[j+1].setEntry(tempentry);

                                tempwinners=myListData[j].getWinner();
                                myListData[j].setWinner(myListData[j+1].getWinner());
                                myListData[j+1].setWinner(tempwinners);

                                tempspotstotal=myListData[j].getMax_pb_val();
                                myListData[j].setMax_pb_val(myListData[j+1].getMax_pb_val());
                                myListData[j+1].setMax_pb_val(tempspotstotal);

                                tempspotsleft=myListData[j].getMin_pb_val();
                                myListData[j].setMin_pb_val(myListData[j+1].getMin_pb_val());
                                myListData[j+1].setMin_pb_val(tempspotsleft);


                                swapped = true;
                            }
                        }

                        // IF no two elements were
                        // swapped by inner loop, then break
                        if (swapped == false)
                            break;
                    }

                    adapter.notifyDataSetChanged();
                }

            }
        });





    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
