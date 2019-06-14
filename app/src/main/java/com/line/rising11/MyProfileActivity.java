package com.line.rising11;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MyProfileActivity extends AppCompatActivity {

    TextView user_name, user_id;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        setTitle("MY PROFILE");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPreferences=getSharedPreferences("loginstatus", Context.MODE_PRIVATE);
        user_name=findViewById(R.id.name);
        user_id=findViewById(R.id.user_unique_id);










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

                                    user_name.setText(obj.getString("name"));
                                    user_id.setText(obj.getString("id"));



                                    /*editor.putString("login","yes");
                                    editor.putString("number",email.getText().toString().trim());
                                    editor.commit();*/
                                    Toast.makeText(MyProfileActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                    /*Intent intent = new Intent(MySettingsActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();*/
                                }
                                else
                                {
                                    Toast.makeText(MyProfileActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
            RestClient.getInstance(MyProfileActivity.this).addToRequestQueue(jsonObjectRequest.setShouldCache(false));



            connected = true;
        }
        else
        {
            /*Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        }





















    }







    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
