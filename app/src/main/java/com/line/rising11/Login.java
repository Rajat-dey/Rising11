package com.line.rising11;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    Button contest,login,btn_reset_password;
    private EditText email,password;

    ImageView fb,insta,twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sharedPreferences=getSharedPreferences("loginstatus", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

        contest=findViewById(R.id.signup);
        login=findViewById(R.id.btn_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btn_reset_password=findViewById(R.id.btn_reset_password);

        fb=findViewById(R.id.fb);
        insta=findViewById(R.id.instagram);
        twitter=findViewById(R.id.twitter);



        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenFacebookPage();
            }
        });




        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenInstaPage();
            }
        });




        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                twitterpage();
            }
        });






        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.getText().toString().trim().equals("")||password.getText().toString().trim().equals(""))
                {
                    if(email.getText().toString().trim().equals(""))
                    {
                        email.setError("Enter email");
                    }
                    else
                    {
                        password.setError("Enter password");
                    }
                }
                else
                {
                    //http://rising11.com/apps/apis/login.php
                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.GET, getString(R.string.signin)+"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim(), null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Log.d("Response: ", response.toString());
                                        //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                                        try {
                                            if(response.getString("code").equals("1"))
                                            {

                                                editor.putString("login","yes");
                                                editor.putString("number",email.getText().toString().trim());
                                                editor.commit();
                                                Toast.makeText(Login.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Login.this, HomeActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else
                                            {
                                                Toast.makeText(Login.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
                        RestClient.getInstance(Login.this).addToRequestQueue(jsonObjectRequest);



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




    protected void OpenFacebookPage () {

        String facebookPageID = "Rising11kk/?ti=as";

        // URL
        String facebookUrl = "https://www.facebook.com/" + facebookPageID;

        String facebookUrlScheme = "fb://page/" + facebookPageID;

        try {
            int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;

            if (versionCode >= 3002850) {
                Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            } else {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrlScheme)));
            }
        } catch (PackageManager.NameNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));

        }

    }




    protected void OpenInstaPage ()
    {
        Intent likeIng = new Intent(Intent.ACTION_VIEW,  Uri.parse("https://www.instagram.com/rising11kk"));
        likeIng.setPackage("com.instagram.android");
        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/rising11kk")));
        }
    }


    protected  void twitterpage()
    {
        Intent intent = null;
        try {
            // get the Twitter app if possible
            this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Rising11_kk?s=08"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Rising11_kk?s=08"));
        }
        this.startActivity(intent);

    }


}
