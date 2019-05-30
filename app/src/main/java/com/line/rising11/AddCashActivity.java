package com.line.rising11;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AddCashActivity extends AppCompatActivity implements PaytmPaymentTransactionCallback {
    private Button add_cash,r10,r20,r100,r200;
    private EditText etcash;
    int money=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash);
        setTitle("Add  Money from Paytm");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        add_cash=findViewById(R.id.add_cash);
        r10=findViewById(R.id.r10);
        r20=findViewById(R.id.r20);
        r100=findViewById(R.id.r100);
        r200=findViewById(R.id.r200);
        etcash=findViewById(R.id.etcash);

        r10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etcash.getText().toString().equals(""))
                {
                    etcash.setText("10");
                }
                else
                {
                    etcash.setText(String.valueOf(Integer.parseInt(etcash.getText().toString().trim())+10));
                }
            }
        });
        r20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etcash.getText().toString().equals(""))
                {
                    etcash.setText("20");
                }
                else
                {
                    etcash.setText(String.valueOf(Integer.parseInt(etcash.getText().toString().trim())+20));
                }
            }
        });
        r100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etcash.getText().toString().equals(""))
                {
                    etcash.setText("100");
                }
                else
                {
                    etcash.setText(String.valueOf(Integer.parseInt(etcash.getText().toString().trim())+100));
                }

            }
        });
        r200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etcash.getText().toString().equals(""))
                {
                    etcash.setText("10");
                }
                else
                {
                    etcash.setText(String.valueOf(Integer.parseInt(etcash.getText().toString().trim())+200));
                }

            }
        });

        add_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             if(etcash.getText().toString().trim().equals(""))
             {
                 etcash.setError("Enter Money");
             }
             else
             {
                 money=Integer.parseInt(etcash.getText().toString().trim());
                 Toast.makeText(AddCashActivity.this, String.valueOf(money) +"add ho gai", Toast.LENGTH_SHORT).show();

                 // ----------------------add paytm api here-----------------------
                 sendUserDetailTOServerdd dl = new sendUserDetailTOServerdd();
                 dl.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
             }

            }
        });
    }

    public class sendUserDetailTOServerdd extends AsyncTask<ArrayList<String>, Void, String> {
        private ProgressDialog dialog = new ProgressDialog(AddCashActivity.this);
        //private String orderId , mid, custid, amt;
        String url =getString(R.string.generate_checksum_url);
        String varifyurl = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";
        // "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID"+orderId;
        String CHECKSUMHASH ="";
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
        protected String doInBackground(ArrayList<String>... alldata) {
            JSONParser jsonParser = new JSONParser(AddCashActivity.this);
            String param=
                    "MID="+getString(R.string.paytm_mid)+
                            "&ORDER_ID=" + "1234"+
                            "&CUST_ID="+"visnu"+
                            "&CHANNEL_ID=WAP&TXN_AMOUNT=100&WEBSITE=WEBSTAGING"+
                            "&CALLBACK_URL="+ varifyurl+"&INDUSTRY_TYPE_ID=Retail";
            JSONObject jsonObject = jsonParser.makeHttpRequest(url,"POST",param);
            // yaha per checksum ke saht order id or status receive hoga..
            Log.e("CheckSum result >>",jsonObject.toString());
            if(jsonObject != null){
                Log.e("CheckSum result >>",jsonObject.toString());
                try {
                    CHECKSUMHASH=jsonObject.has("CHECKSUMHASH")?jsonObject.getString("CHECKSUMHASH"):"";
                    Log.e("CheckSum result >>",CHECKSUMHASH);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return CHECKSUMHASH;
        }
        @Override
        protected void onPostExecute(String result) {
            Log.e(" setup acc ","  signup result  " + result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            PaytmPGService Service = PaytmPGService.getStagingService();
            // when app is ready to publish use production service
            // PaytmPGService  Service = PaytmPGService.getProductionService();
            // now call paytm service here
            //below parameter map is required to construct PaytmOrder object, Merchant should replace below map values with his own values
            HashMap<String, String> paramMap = new HashMap<String, String>();
            //these are mandatory parameters
            paramMap.put("MID", getString(R.string.paytm_mid)); //MID provided by paytm
            paramMap.put("ORDER_ID", "1234");
            paramMap.put("CUST_ID", "visnu");
            paramMap.put("CHANNEL_ID", "WAP");
            paramMap.put("TXN_AMOUNT", "100");
            paramMap.put("WEBSITE", "WEBSTAGING");
            paramMap.put("CALLBACK_URL" ,varifyurl);
            //paramMap.put( "EMAIL" , "abc@gmail.com");   // no need
            // paramMap.put( "MOBILE_NO" , "9144040888");  // no need
            paramMap.put("CHECKSUMHASH" ,CHECKSUMHASH);
            //paramMap.put("PAYMENT_TYPE_ID" ,"CC");    // no need
            paramMap.put("INDUSTRY_TYPE_ID", "Retail");
            PaytmOrder Order = new PaytmOrder(paramMap);
            Log.e("checksum ", "param "+ paramMap.toString());
            Service.initialize(Order,null);
            // start payment service call here
            Service.startPaymentTransaction(AddCashActivity.this, true, true,
                    AddCashActivity.this  );
        }
    }

    @Override
    public void onTransactionResponse(Bundle bundle) {
        Log.e("checksum ", " respon true " + bundle.toString());
    }
    @Override
    public void networkNotAvailable() {
    }
    @Override
    public void clientAuthenticationFailed(String s) {
    }
    @Override
    public void someUIErrorOccurred(String s) {
        Log.e("checksum ", " ui fail respon  "+ s );
    }
    @Override
    public void onErrorLoadingWebPage(int i, String s, String s1) {
        Log.e("checksum ", " error loading pagerespon true "+ s + "  s1 " + s1);
    }
    @Override
    public void onBackPressedCancelTransaction() {
        Log.e("checksum ", " cancel call back respon  " );
    }
    @Override
    public void onTransactionCancel(String s, Bundle bundle) {
        Log.e("checksum ", "  transaction cancel " );
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
