package com.line.rising11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class SignUp extends AppCompatActivity {
private EditText name,email,number,password;
private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        number=findViewById(R.id.number);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.btn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().trim().equals("")|| email.getText().toString().trim().equals("") || number.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
                {

                    if(name.getText().toString().trim().equals(""))
                    {
                        name.setError("Enter name");
                    }
                    else if(email.getText().toString().trim().equals(""))
                    {
                        email.setError("Enter name");
                    }
                    else if(number.getText().toString().trim().equals(""))
                    {
                        number.setError("Enter name");
                    }
                    else
                    {
                        password.setError("Enter password");
                    }
                }
                else
                {
                    //http://rising11.com/apps/apis/register-user.php?name=Vivek&mobile=7034234575&password=123&email=v@gmail.com

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, "http://rising11.com/apps/apis/register-user.php"+"?name="+name.getText().toString()+"&mobile="+number.getText().toString()+"&password="+password.getText().toString()+"&email="+email.getText().toString(), null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Log.d("Response: ", response.toString());
                                    //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                                    try {
                                        if(response.getString("code").equals("1"))
                                        {
                                            Intent intent = new Intent(SignUp.this, number_verify_activity.class);

                                            intent.putExtra("mob",number.getText().toString().trim());

                                            startActivity(intent);

                                            Toast.makeText(SignUp.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(SignUp.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                               /* Toast.makeText(getApplicationContext(), "Error "+ response.getString("code") + ": "
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
                    RestClient.getInstance(SignUp.this).addToRequestQueue(jsonObjectRequest);


                }
            }
        });
    }
}
