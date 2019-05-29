package com.line.rising11;

import android.content.Intent;
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

public class Login extends AppCompatActivity {

    Button contest,login;
    private EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        contest=findViewById(R.id.signup);
        login=findViewById(R.id.btn_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);


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
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, getString(R.string.signin)+"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim(), null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Log.d("Response: ", response.toString());
                                    //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                                    try {
                                        if(response.getString("code").equals("1"))
                                        {
                                            Intent intent = new Intent(Login.this, HomeActivity.class);
                                            startActivity(intent);

                                            Toast.makeText(Login.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent(Login.this, HomeActivity.class);
                    startActivity(intent);


                }

            }
        });



    }
}
