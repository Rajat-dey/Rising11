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

public class ForgetSetPasswordActivity extends AppCompatActivity {
    private EditText pass,cpass;
    private Button setpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_set_password);
        setTitle("Set Password");
        pass=findViewById(R.id.pass);
        cpass=findViewById(R.id.cpass);
        setpass=findViewById(R.id.setpass);

        setpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              if(pass.getText().toString().trim().equals("")|| cpass.getText().toString().trim().equals(""))
              {
                  if(pass.getText().toString().trim().equals(""))
                  {
                      pass.setError("Enter New Password");
                  }
                  else
                  {
                      cpass.setError("Enter Confirm New Password");
                  }
              }

              else if(pass.getText().toString().trim().equals(cpass.getText().toString().trim()))
              {
                  boolean connected = false;
                  ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                  if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                          connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                      JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                              (Request.Method.GET, getString(R.string.forgetpass)+"?mobile="+getIntent().getStringExtra("mob")+"&step=3&password="+pass.getText().toString().trim(), null, new Response.Listener<JSONObject>() {
                                  @Override
                                  public void onResponse(JSONObject response) {
                                      // Log.d("Response: ", response.toString());
                                      //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                                      try {
                                          if(response.getString("code").equals("1"))
                                          {


                                              Toast.makeText(ForgetSetPasswordActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                              Intent intent = new Intent(ForgetSetPasswordActivity.this, Login.class);
                                              startActivity(intent);
                                              finish();
                                          }
                                          else
                                          {
                                              Toast.makeText(ForgetSetPasswordActivity.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
                      RestClient.getInstance(ForgetSetPasswordActivity.this).addToRequestQueue(jsonObjectRequest);



                      connected = true;
                  }
                  else
                  {
                      Snackbar.make(v, "Please check your Internet connection", Snackbar.LENGTH_LONG)
                              .setAction("Action", null).show();
                  }

              }
              else
              {
                  Toast.makeText(ForgetSetPasswordActivity.this, "Password not matches", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
}
