package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MySettingsActivity extends AppCompatActivity {
    EditText name,email,password,dob,address,city,pin,country;
    SharedPreferences sharedPreferences;
    LinearLayout male,female;
    String sex="female";
    SharedPreferences.Editor editor;
    private TextView update,logout,mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);
        setTitle("MY SETTINGS");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sharedPreferences=getSharedPreferences("loginstatus", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        dob=findViewById(R.id.dob);
        mob=findViewById(R.id.mob);
        address=findViewById(R.id.address);
        city=findViewById(R.id.city);
        pin=findViewById(R.id.pin);
        country=findViewById(R.id.country);
        update=findViewById(R.id.update);
        logout=findViewById(R.id.logout);

        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setBackgroundResource(R.drawable.button_design_round_simple);
                female.setBackgroundResource(R.drawable.button_design_round_simple_white_gray_border);
                sex="male";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                female.setBackgroundResource(R.drawable.button_design_round_simple);
                male.setBackgroundResource(R.drawable.button_design_round_simple_white_gray_border);
                sex="female";

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("login","no");
                editor.commit();
                Intent intent = new Intent(MySettingsActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, getString(R.string.userdetails)+"?mobile="+sharedPreferences.getString("number",""), null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                             Log.d("Response: ", response.toString());
                            //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                            try {
                                if(response.getString("code").equals("1"))
                                {
                                    JSONObject obj=response.getJSONObject("contest");

                                    name.setText(obj.getString("name"));
                                    email.setText(obj.getString("email"));
                                    password.setText(obj.getString("password"));
                                    if(!obj.getString("dob").equals("null")) {
                                        dob.setText(obj.getString("dob"));
                                    }
                                    mob.setText(obj.getString("mobile"));
                                    if(!obj.getString("address").equals("null")) {
                                        address.setText(obj.getString("address"));
                                    }
                                    if(!obj.getString("city").equals("null")) {
                                        city.setText(obj.getString("city"));
                                    }
                                    if(!obj.getString("pin_code").equals("null")) {
                                        pin.setText(obj.getString("pin_code"));
                                    }
                                    if(!obj.getString("country").equals("null")) {
                                        country.setText(obj.getString("country"));
                                    }
                                    String gender=obj.getString("gender");
                                    if(gender.equals("male"))
                                    {
                                        male.setBackgroundResource(R.drawable.button_design_round_simple);
                                        female.setBackgroundResource(R.drawable.button_design_round_simple_white_gray_border);

                                    }
                                    else
                                    {
                                        female.setBackgroundResource(R.drawable.button_design_round_simple);
                                        male.setBackgroundResource(R.drawable.button_design_round_simple_white_gray_border);

                                    }


                                    /*editor.putString("login","yes");
                                    editor.putString("number",email.getText().toString().trim());
                                    editor.commit();*/
                                    Toast.makeText(MySettingsActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                    /*Intent intent = new Intent(MySettingsActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();*/
                                }
                                else
                                {
                                    Toast.makeText(MySettingsActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
            RestClient.getInstance(MySettingsActivity.this).addToRequestQueue(jsonObjectRequest);



            connected = true;
        }
        else
        {
            /*Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, getString(R.string.update_profile)+"?name="+name.getText().toString().trim()+"&mobile="+mob.getText().toString().trim()+"&password="+password.getText().toString().trim()+"&email="+email.getText().toString().trim()+"&dob="+dob.getText().toString().trim()+"&gender="+sex+"&address="+address.getText().toString().trim()+"&city="+city.getText().toString()+"&pin_code="+pin.getText().toString().trim()+"&country="+country.getText().toString().trim(), null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("Response: ", response.toString());
                                    //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                                    try {
                                        if(response.getString("code").equals("1"))
                                        {



                                            Toast.makeText(MySettingsActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                    finish();
                                        }
                                        else
                                        {
                                            Toast.makeText(MySettingsActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
                    RestClient.getInstance(MySettingsActivity.this).addToRequestQueue(jsonObjectRequest);



                    connected = true;
                }
                else
                {
                Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
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
