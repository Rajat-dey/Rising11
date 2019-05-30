package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText mob;
    private Button getotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setTitle("Forget Password");
        mob=findViewById(R.id.mob);
        getotp=findViewById(R.id.getotp);
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(mob.getText().toString().trim().equals(""))
                {
                    mob.setError("Enter mobile number");
                }
                else
                {
                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.GET, getString(R.string.forget_pass)+"?mobile="+mob.getText().toString().trim()+"&step=1", null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Log.d("Response: ", response.toString());
                                        //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                                        try {
                                            if(response.getString("code").equals("1"))
                                            {


                                                Toast.makeText(ForgotPasswordActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ForgotPasswordActivity.this, OtpVerify_Activity.class);
                                                intent.putExtra("type","forget_pass");
                                                intent.putExtra("mob",mob.getText().toString().trim());
                                                startActivity(intent);
                                                finish();
                                            }
                                            else
                                            {
                                                Toast.makeText(ForgotPasswordActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
                        RestClient.getInstance(ForgotPasswordActivity.this).addToRequestQueue(jsonObjectRequest);



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
}
