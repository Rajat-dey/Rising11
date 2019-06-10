package com.line.rising11;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.line.rising11.adapters.CustomTeamSelectionAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllRounderFragment extends Fragment {

    RecyclerView rvTeamSelection;
    public AllRounderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_all_rounder, container, false);
        // Inflate the layout for this fragment
                rvTeamSelection = v.findViewById(R.id.rv_team_selection);
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, "http://rising11.com/apps/apis/get-fantasy-squad.php?unique_id=1034809", null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Log.d("Response: ", response.toString());
                            //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                            try {
                                if(response.getString("code").equals("1"))
                                {
                                    JSONArray jsonArray=response.getJSONArray("players");

                                    JSONArray jsonArrayAR=new JSONArray();

                                    for(int i=0;i<jsonArray.length();i++)
                                    {

                                        if(jsonArray.getJSONObject(i).get("role").toString().equals("Bowling allrounder")|| jsonArray.getJSONObject(i).get("role").toString().equals("Batting allrounder")||jsonArray.getJSONObject(i).get("role").toString().equals("Allrounder")||jsonArray.getJSONObject(i).get("role").toString().equals(""))
                                        {
                                            jsonArrayAR.put(jsonArray.getJSONObject(i));
                                        }

                                    }
                                    rvTeamSelection.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                    rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(jsonArrayAR));

                                }
                                else
                                {
                                    Toast.makeText(getContext(), response.getString("msg"), Toast.LENGTH_SHORT).show();

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
            RestClient.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        return v;
    }

}
