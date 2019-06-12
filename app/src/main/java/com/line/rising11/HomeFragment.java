package com.line.rising11;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.line.rising11.adapters.CustomMatchAdapter;
import com.line.rising11.adapters.CustomTeamSelectionAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView rvHomeMatches;
    private TabLayout tb_home;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.findViewById(R.id.rv_home_matches).setFocusable(false);
        view.findViewById(R.id.rl).requestFocus();
        tb_home=view.findViewById(R.id.tb_home);

        rvHomeMatches = view.findViewById(R.id.rv_home_matches);

        tb_home.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1 )
                {
                    Intent intent=new Intent(getContext(),Football_Home_Activity.class);
                    startActivity(intent);
                }
                else if(tab.getPosition()==2)
                {
                    Intent intent=new Intent(getContext(),Kabaddi_Home_Activity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, "http://rising11.com/apps/apis/get-upcoming-matches.php", null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Log.d("Response: ", response.toString());
                            //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                            try {

                                if(response.getString("code").equals("1"))
                                {
                                    JSONObject obj=response.getJSONObject("data");
                                    JSONArray array=obj.getJSONArray("matches");


                                    JSONArray jsonArray=new JSONArray();

                                    for(int i=0;i<array.length();i++)
                                    {


                                            jsonArray.put(array.getJSONObject(i));



                                    }
                                    rvHomeMatches.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                    rvHomeMatches.setAdapter(new CustomMatchAdapter(getContext(), jsonArray));

                                }
                                else
                                {
                                    Toast.makeText(getContext(), response.getString("msg"), Toast.LENGTH_SHORT).show();

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
            RestClient.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            Snackbar.make(view, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        return view;
    }

    public void openJoinContest(View view) {
        Intent intent = new Intent(getContext(), JoinedContest.class);
        startActivity(intent);
    }



}
