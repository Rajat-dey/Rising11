package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageView right;

    TextView username,verified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences=getSharedPreferences("loginstatus", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerview=navigationView.getHeaderView(0);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.rl_fragment, new HomeFragment()).commit();

        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                if(menuItem.getItemId()==R.id.navigation_home)
                {

                }
                else if (menuItem.getItemId()==R.id.navigation_dashboard)
                {
                    Intent intent=new Intent(HomeActivity.this,MyMatchActivity.class);
                    startActivity(intent);
                }
                else if(menuItem.getItemId()==R.id.navigation_notifications)
                {
                    Intent intent=new Intent(HomeActivity.this,HomeMoreActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });


        username=headerview.findViewById(R.id.tv_user_name);
        verified=headerview.findViewById(R.id.tv_verified);
        right=headerview.findViewById(R.id.right);

        verified.setText("Non Verified");
        right.setVisibility(View.INVISIBLE);


        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, getString(R.string.userdetails)+"?mobile="+sharedPreferences.getString("number",""), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                            try {
                                if(response.getString("code").equals("1"))
                                {
                                    JSONObject obj=response.getJSONObject("contest");

                                username.setText(obj.getString("name"));
                                    /*
                                  //  Toast.makeText(HomeActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();

                                    if(!obj.getString("is_verified").equals("1")) {
                                        verified.setText("Verified");
                                    }*/
                                }
                                else
                                {
                                    Toast.makeText(HomeActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
            RestClient.getInstance(HomeActivity.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            /*Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }







    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_notification) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
          if(id==R.id.nav_my_balance)
          {
              Intent intent=new Intent(HomeActivity.this,MyBalanceActivity.class);
              startActivity(intent);
          }
          else if(id==R.id.nav_my_referrals)
          {
              Intent intent=new Intent(HomeActivity.this,MyReferralsActivity.class);
              startActivity(intent);
          }
          else if(id==R.id.nav_my_rewards_offers)
          {
              Intent intent=new Intent(HomeActivity.this,RewardOffer1Activity.class);
              startActivity(intent);
          }
          else if(id==R.id.nav_my_info_settings)
          {
              Intent intent=new Intent(HomeActivity.this,MySettingsActivity.class);
              startActivity(intent);
          }
          else if(id==R.id.nav_profile)
          {
              Intent intent=new Intent(HomeActivity.this,MyProfileActivity.class);
              startActivity(intent);
          }
          else if(id==R.id.nav_logout)
          {
              editor.putString("login","no");
              editor.commit();
              Intent intent = new Intent(HomeActivity.this, Login.class);
              startActivity(intent);
              finish();
          }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openJoinContest(View view) {
        Intent intent = new Intent(HomeActivity.this, JoinedContest.class);
        startActivity(intent);
    }
}
