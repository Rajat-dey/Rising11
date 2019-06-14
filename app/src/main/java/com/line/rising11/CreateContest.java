package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class CreateContest extends AppCompatActivity {

    Button winning_breakup;
    private EditText cname,csize,wammount;
    private TextView eammount;
    String efees="10",pamount="1";
    Switch switch1;
    int swithval=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contest);

        winning_breakup=findViewById(R.id.winning_breakup);
        cname=findViewById(R.id.cname);
        wammount=findViewById(R.id.wammount);
        csize=findViewById(R.id.csize);
        eammount=findViewById(R.id.eammount);
        switch1=findViewById(R.id.switch1);
        csize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!csize.getText().toString().trim().equals("") && !wammount.getText().toString().trim().equals(""))
                {
                    DecimalFormat df=new DecimalFormat("0.0");
                    int a=Integer.parseInt(csize.getText().toString().trim());
                    float b=Float.parseFloat(wammount.getText().toString().trim());
                    pamount=String.valueOf(b);

                    b= b+ (b*25)/100;
                    b=b/a;
                    efees=String.valueOf(df.format(b));
                    eammount.setText("RS. "+String.valueOf(df.format(b)));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        wammount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!csize.getText().toString().trim().equals("") && !wammount.getText().toString().trim().equals(""))
                {
                    DecimalFormat df=new DecimalFormat("0.0");
                    int a=Integer.parseInt(csize.getText().toString().trim());
                    float b=Float.parseFloat(wammount.getText().toString().trim());
                    pamount=String.valueOf(b);
                    b= b+ (b*25)/100;
                    b=b/a;
                    efees=String.valueOf(df.format(b));
                    eammount.setText("RS. "+String.valueOf(df.format(b)));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        winning_breakup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(cname.getText().toString().trim().equals("") || csize.getText().toString().trim().equals("")|| wammount.getText().toString().trim().equals(""))
               {
                   if(cname.getText().toString().trim().equals(""))
                   {
                       cname.setError("Enter Contest Name");
                   }
                   else if(csize.getText().toString().trim().equals(""))
                   {
                       csize.setError("Enter Contest Size");
                   }
                   else
                   {
                       wammount.setError("Enter Winning Amount");
                   }

               }
               else
               {
                   if(switch1.isChecked())
                   {
                       swithval=1;
                   }
                   boolean connected = false;
                   ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                   if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                           connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                       SharedPreferences sharedPreferences=getSharedPreferences("loginstatus", Context.MODE_PRIVATE);
                       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                               (Request.Method.GET, getString(R.string.create_contest)+"?contest_name="+cname.getText().toString().trim()+"&total_winning_amount="+wammount.getText().toString().trim()+"&contest_size="+csize.getText().toString().trim()+"&entry_fees="+efees+"&mobile="+sharedPreferences.getString("number","")+"&is_multiple="+String.valueOf(swithval)+"&type=private", null, new Response.Listener<JSONObject>() {
                                   @Override
                                   public void onResponse(JSONObject response) {
                                       Log.d("Response: ", response.toString());
                                       //Log.d("Link",getString(R.string.signup) +"?mobile="+email.getText().toString().trim()+"&password="+password.getText().toString().trim());


                                       try {
                                           if(response.getString("code").equals("1"))
                                           {


                                               Toast.makeText(CreateContest.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
                                               Intent intent = new Intent(CreateContest.this, wining_breakup.class);
                                               intent.putExtra("pamount",pamount);
                                               intent.putExtra("contest_id",response.getString("contest_id"));
                                               startActivity(intent);
                                               finish();
                                           }
                                           else
                                           {
                                               Toast.makeText(CreateContest.this, response.getString("msg"), Toast.LENGTH_SHORT).show();
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
                       RestClient.getInstance(CreateContest.this).addToRequestQueue(jsonObjectRequest);



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
