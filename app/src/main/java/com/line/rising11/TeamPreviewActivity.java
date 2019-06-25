package com.line.rising11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TeamPreviewActivity extends AppCompatActivity
{


    ImageView preview_cancel;
    LinearLayout wkl1,wkl2,wkl3,wkl4,batl1,batl2,batl3,batl4,batl5,batl6,arl1,arl2,arl3,arl4,bowll1,bowll2,bowll3,bowll4,bowll5,bowll6;
    ImageView wkimg1,wkimg2,wkimg3,wkimg4,batimg1,batimg2,batimg3,batimg4,batimg5,batimg6,arimg1,arimg2,arimg3,arimg4,bowlimg1,bowlimg2,bowlimg3,bowlimg4,bowlimg5,bowlimg6;
    TextView wkpname1,wkpname2,wkpname3,wkpname4,batpname1,batpname2,batpname3,batpname4,batpname5,batpname6,arpname1,arpname2,arpname3,arpname4,bowlpname1,bowlpname2,bowlpname3,bowlpname4,bowlpname5,bowlpname6;
    ArrayList<String> pname1,pimage1,pteam1,prole1,pcredit1;
    JSONArray jsonArraywk,jsonArraybat,jsonArrayar,jsonArraybowl;
    TextView wkc1,wkc2,wkc3,wkc4,batc1,batc2,batc3,batc4,batc5,batc6,arc1,arc2,arc3,arc4,bowlc1,bowlc2,bowlc3,bowlc4,bowlc5,bowlc6;
    TextView wkcr1,wkcr2,wkcr3,wkcr4,batcr1,batcr2,batcr3,batcr4,batcr5,batcr6,arcr1,arcr2,arcr3,arcr4,bowlcr1,bowlcr2,bowlcr3,bowlcr4,bowlcr5,bowlcr6;
    int c,vc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_preview);
        setTitle("Team Preview");

        preview_cancel=findViewById(R.id.preview_cancel);

        wkl1=findViewById(R.id.wkl1);
        wkl2=findViewById(R.id.wkl2);
        wkl3=findViewById(R.id.wkl3);
        wkl4=findViewById(R.id.wkl4);

        batl1=findViewById(R.id.batl1);
        batl2=findViewById(R.id.batl2);
        batl3=findViewById(R.id.batl3);
        batl4=findViewById(R.id.batl4);
        batl5=findViewById(R.id.batl5);
        batl6=findViewById(R.id.batl6);

        arl1=findViewById(R.id.arl1);
        arl2=findViewById(R.id.arl2);
        arl3=findViewById(R.id.arl3);
        arl4=findViewById(R.id.arl4);

        bowll1=findViewById(R.id.bowll1);
        bowll2=findViewById(R.id.bowll2);
        bowll3=findViewById(R.id.bowll3);
        bowll4=findViewById(R.id.bowll4);
        bowll5=findViewById(R.id.bowll5);
        bowll6=findViewById(R.id.bowll6);

        wkimg1=findViewById(R.id.wkimg1);
        wkimg2=findViewById(R.id.wkimg2);
        wkimg3=findViewById(R.id.wkimg3);
        wkimg4=findViewById(R.id.wkimg4);

        batimg1=findViewById(R.id.batimg1);
        batimg2=findViewById(R.id.batimg2);
        batimg3=findViewById(R.id.batimg3);
        batimg4=findViewById(R.id.batimg4);
        batimg5=findViewById(R.id.batimg5);
        batimg6=findViewById(R.id.batimg6);

        arimg1=findViewById(R.id.arimg1);
        arimg2=findViewById(R.id.arimg2);
        arimg3=findViewById(R.id.arimg3);
        arimg4=findViewById(R.id.arimg4);

        bowlimg1=findViewById(R.id.bowlimg1);
        bowlimg2=findViewById(R.id.bowlimg2);
        bowlimg3=findViewById(R.id.bowlimg3);
        bowlimg4=findViewById(R.id.bowlimg4);
        bowlimg5=findViewById(R.id.bowlimg5);
        bowlimg6=findViewById(R.id.bowlimg6);

        wkpname1=findViewById(R.id.wkpname1);
        wkpname2=findViewById(R.id.wkpname2);
        wkpname3=findViewById(R.id.wkpname3);
        wkpname4=findViewById(R.id.wkpname4);

        batpname1=findViewById(R.id.batpname1);
        batpname2=findViewById(R.id.batpname2);
        batpname3=findViewById(R.id.batpname3);
        batpname4=findViewById(R.id.batpname4);
        batpname5=findViewById(R.id.batpname5);
        batpname6=findViewById(R.id.batpname6);

        arpname1=findViewById(R.id.arpname1);
        arpname2=findViewById(R.id.arpname2);
        arpname3=findViewById(R.id.arpname3);
        arpname4=findViewById(R.id.arpname4);

        bowlpname1=findViewById(R.id.bowlpname1);
        bowlpname2=findViewById(R.id.bowlpname2);
        bowlpname3=findViewById(R.id.bowlpname3);
        bowlpname4=findViewById(R.id.bowlpname4);
        bowlpname5=findViewById(R.id.bowlpname5);
        bowlpname6=findViewById(R.id.bowlpname6);

        wkc1=findViewById(R.id.wkc1);
        wkc2=findViewById(R.id.wkc2);
        wkc3=findViewById(R.id.wkc3);
        wkc4=findViewById(R.id.wkc4);

        batc1=findViewById(R.id.batc1);
        batc2=findViewById(R.id.batc2);
        batc3=findViewById(R.id.batc3);
        batc4=findViewById(R.id.batc4);
        batc5=findViewById(R.id.batc5);
        batc6=findViewById(R.id.batc6);

        arc1=findViewById(R.id.arc1);
        arc2=findViewById(R.id.arc2);
        arc3=findViewById(R.id.arc3);
        arc4=findViewById(R.id.arc4);

        bowlc1=findViewById(R.id.bowlc1);
        bowlc2=findViewById(R.id.bowlc2);
        bowlc3=findViewById(R.id.bowlc3);
        bowlc4=findViewById(R.id.bowlc4);
        bowlc5=findViewById(R.id.bowlc5);
        bowlc6=findViewById(R.id.bowlc6);

        wkcr1=findViewById(R.id.wkcr1);
        wkcr2=findViewById(R.id.wkcr2);
        wkcr3=findViewById(R.id.wkcr3);
        wkcr4=findViewById(R.id.wkcr4);

        batcr1=findViewById(R.id.batcr1);
        batcr2=findViewById(R.id.batcr2);
        batcr3=findViewById(R.id.batcr3);
        batcr4=findViewById(R.id.batcr4);
        batcr5=findViewById(R.id.batcr5);
        batcr6=findViewById(R.id.batcr6);

        arcr1=findViewById(R.id.arcr1);
        arcr2=findViewById(R.id.arcr2);
        arcr3=findViewById(R.id.arcr3);
        arcr4=findViewById(R.id.arcr4);

        bowlcr1=findViewById(R.id.bowlcr1);
        bowlcr2=findViewById(R.id.bowlcr2);
        bowlcr3=findViewById(R.id.bowlcr3);
        bowlcr4=findViewById(R.id.bowlcr4);
        bowlcr5=findViewById(R.id.bowlcr5);
        bowlcr6=findViewById(R.id.bowlcr6);

        preview_cancel.setVisibility(View.VISIBLE);

        wkc1.setVisibility(View.GONE);
        wkc2.setVisibility(View.GONE);
        wkc3.setVisibility(View.GONE);
        wkc4.setVisibility(View.GONE);

        batc1.setVisibility(View.GONE);
        batc2.setVisibility(View.GONE);
        batc3.setVisibility(View.GONE);
        batc4.setVisibility(View.GONE);
        batc5.setVisibility(View.GONE);
        batc6.setVisibility(View.GONE);

        arc1.setVisibility(View.GONE);
        arc2.setVisibility(View.GONE);
        arc3.setVisibility(View.GONE);
        arc4.setVisibility(View.GONE);

        bowlc1.setVisibility(View.GONE);
        bowlc2.setVisibility(View.GONE);
        bowlc3.setVisibility(View.GONE);
        bowlc4.setVisibility(View.GONE);
        bowlc5.setVisibility(View.GONE);
        bowlc6.setVisibility(View.GONE);

        wkl1.setVisibility(View.GONE);
        wkl2.setVisibility(View.GONE);
        wkl3.setVisibility(View.GONE);
        wkl4.setVisibility(View.GONE);

        batl1.setVisibility(View.GONE);
        batl2.setVisibility(View.GONE);
        batl3.setVisibility(View.GONE);
        batl4.setVisibility(View.GONE);
        batl5.setVisibility(View.GONE);
        batl6.setVisibility(View.GONE);

        arl1.setVisibility(View.GONE);
        arl2.setVisibility(View.GONE);
        arl3.setVisibility(View.GONE);
        arl4.setVisibility(View.GONE);

        bowll1.setVisibility(View.GONE);
        bowll2.setVisibility(View.GONE);
        bowll3.setVisibility(View.GONE);
        bowll4.setVisibility(View.GONE);
        bowll5.setVisibility(View.GONE);
        bowll6.setVisibility(View.GONE);


        preview_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                finish();
            }
        });


        jsonArraywk=new JSONArray();
        jsonArraybat=new JSONArray();
        jsonArrayar=new JSONArray();
        jsonArraybowl=new JSONArray();

        pname1=getIntent().getStringArrayListExtra("pname1");
        pimage1=getIntent().getStringArrayListExtra("pimage1");
        pteam1=getIntent().getStringArrayListExtra("pteam1");
        prole1=getIntent().getStringArrayListExtra("prole1");
        pcredit1=getIntent().getStringArrayListExtra("pcredit1");


        c= Integer.parseInt(getIntent().getStringExtra("c"));
        vc= Integer.parseInt((getIntent().getStringExtra("vc")));






        for (int i=0;i<prole1.size();i++)
        {
            if(prole1.get(i).equals("wk"))
            {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("pname",pname1.get(i));
                    jsonObject.put("pimage",pimage1.get(i));
                    jsonObject.put("pteam",pteam1.get(i));
                    jsonObject.put("prole",prole1.get(i));
                    jsonObject.put("pcredit",pcredit1.get(i));
                    jsonArraywk.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            else if(prole1.get(i).equals("bat"))
            {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("pname",pname1.get(i));
                    jsonObject.put("pimage",pimage1.get(i));
                    jsonObject.put("pteam",pteam1.get(i));
                    jsonObject.put("prole",prole1.get(i));
                    jsonObject.put("pcredit",pcredit1.get(i));
                    jsonArraybat.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            else if(prole1.get(i).equals("ar"))
            {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("pname",pname1.get(i));
                    jsonObject.put("pimage",pimage1.get(i));
                    jsonObject.put("pteam",pteam1.get(i));
                    jsonObject.put("prole",prole1.get(i));
                    jsonObject.put("pcredit",pcredit1.get(i));
                    jsonArrayar.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            else if(prole1.get(i).equals("bowl"))
            {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("pname",pname1.get(i));
                    jsonObject.put("pimage",pimage1.get(i));
                    jsonObject.put("pteam",pteam1.get(i));
                    jsonObject.put("prole",prole1.get(i));
                    jsonObject.put("pcredit",pcredit1.get(i));
                    jsonArraybowl.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        if(jsonArraywk.length()==1)
        {
            wkl2.setVisibility(View.VISIBLE);
            try {

                if(!jsonArraywk.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraywk.getJSONObject(0).getString("pimage")).into(wkimg2);
                }
                wkpname2.setText(jsonArraywk.getJSONObject(0).getString("pname"));
                wkcr2.setText(jsonArraywk.getJSONObject(0).getString("pcredit"));

                if(c==0)
                {
                    wkc2.setVisibility(View.VISIBLE);
                    wkc2.setText("c");
                }
                if(vc==0)
                {
                    wkc2.setVisibility(View.VISIBLE);
                    wkc2.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraywk.length()==2)
        {
            wkl2.setVisibility(View.VISIBLE);
            wkl3.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraywk.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraywk.getJSONObject(0).getString("pimage")).into(wkimg2);
                }
                wkpname2.setText(jsonArraywk.getJSONObject(0).getString("pname"));
                if(!jsonArraywk.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraywk.getJSONObject(1).getString("pimage")).into(wkimg3);
                }
                wkpname3.setText(jsonArraywk.getJSONObject(1).getString("pname"));
                wkcr2.setText(jsonArraywk.getJSONObject(0).getString("pcredit"));
                wkcr3.setText(jsonArraywk.getJSONObject(1).getString("pcredit"));
                if(c==0)
                {
                    wkc2.setVisibility(View.VISIBLE);
                    wkc2.setText("c");
                }
                if(vc==0)
                {
                    wkc2.setVisibility(View.VISIBLE);
                    wkc2.setText("vc");
                }
                if(c==1)
                {
                    wkc3.setVisibility(View.VISIBLE);
                    wkc3.setText("c");
                }
                if(vc==1)
                {
                    wkc3.setVisibility(View.VISIBLE);
                    wkc3.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraywk.length()==3)
        {
            wkl2.setVisibility(View.VISIBLE);
            wkl3.setVisibility(View.VISIBLE);
            wkl4.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraywk.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraywk.getJSONObject(0).getString("pimage")).into(wkimg2);
                }
                wkpname2.setText(jsonArraywk.getJSONObject(0).getString("pname"));
                if(!jsonArraywk.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraywk.getJSONObject(1).getString("pimage")).into(wkimg3);
                }
                wkpname3.setText(jsonArraywk.getJSONObject(1).getString("pname"));
                if(!jsonArraywk.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraywk.getJSONObject(2).getString("pimage")).into(wkimg4);
                }
                wkpname4.setText(jsonArraywk.getJSONObject(2).getString("pname"));
                wkcr2.setText(jsonArraywk.getJSONObject(0).getString("pcredit"));
                wkcr3.setText(jsonArraywk.getJSONObject(1).getString("pcredit"));
                wkcr4.setText(jsonArraywk.getJSONObject(2).getString("pcredit"));
                if(c==0)
                {
                    wkc2.setVisibility(View.VISIBLE);
                    wkc2.setText("c");
                }
                if(vc==0)
                {
                    wkc2.setVisibility(View.VISIBLE);
                    wkc2.setText("vc");
                }
                if(c==1)
                {
                    wkc3.setVisibility(View.VISIBLE);
                    wkc3.setText("c");
                }
                if(vc==1)
                {
                    wkc3.setVisibility(View.VISIBLE);
                    wkc3.setText("vc");
                }
                if(c==2)
                {
                    wkc4.setVisibility(View.VISIBLE);
                    wkc4.setText("c");
                }
                if(vc==2)
                {
                    wkc4.setVisibility(View.VISIBLE);
                    wkc4.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraywk.length()==4)
        {
            wkl2.setVisibility(View.VISIBLE);
            wkl3.setVisibility(View.VISIBLE);
            wkl4.setVisibility(View.VISIBLE);
            wkl1.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraywk.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraywk.getJSONObject(1).getString("pimage")).into(wkimg2);
                }
                wkpname2.setText(jsonArraywk.getJSONObject(1).getString("pname"));
                if(!jsonArraywk.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraywk.getJSONObject(2).getString("pimage")).into(wkimg3);
                }
                wkpname3.setText(jsonArraywk.getJSONObject(2).getString("pname"));
                if(!jsonArraywk.getJSONObject(3).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraywk.getJSONObject(3).getString("pimage")).into(wkimg4);
                }
                wkpname4.setText(jsonArraywk.getJSONObject(3).getString("pname"));
                if(!jsonArraywk.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraywk.getJSONObject(0).getString("pimage")).into(wkimg1);
                }
                wkpname1.setText(jsonArraywk.getJSONObject(0).getString("pname"));

                wkcr2.setText(jsonArraywk.getJSONObject(1).getString("pcredit"));
                wkcr3.setText(jsonArraywk.getJSONObject(2).getString("pcredit"));
                wkcr4.setText(jsonArraywk.getJSONObject(3).getString("pcredit"));
                wkcr1.setText(jsonArraywk.getJSONObject(0).getString("pcredit"));

                if(c==1)
                {
                    wkc2.setVisibility(View.VISIBLE);
                    wkc2.setText("c");
                }
                if(vc==1)
                {
                    wkc2.setVisibility(View.VISIBLE);
                    wkc2.setText("vc");
                }
                if(c==2)
                {
                    wkc3.setVisibility(View.VISIBLE);
                    wkc3.setText("c");
                }
                if(vc==2)
                {
                    wkc3.setVisibility(View.VISIBLE);
                    wkc3.setText("vc");
                }
                if(c==3)
                {
                    wkc4.setVisibility(View.VISIBLE);
                    wkc4.setText("c");
                }
                if(vc==3)
                {
                    wkc4.setVisibility(View.VISIBLE);
                    wkc4.setText("vc");
                }
                if(c==0)
                {
                    wkc1.setVisibility(View.VISIBLE);
                    wkc1.setText("c");
                }
                if(vc==0)
                {
                    wkc1.setVisibility(View.VISIBLE);
                    wkc1.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(jsonArraybat.length()==1)
        {
            batl2.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybat.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(0).getString("pimage")).into(batimg2);
                }
                batpname2.setText(jsonArraybat.getJSONObject(0).getString("pname"));

                batcr2.setText(jsonArraybat.getJSONObject(0).getString("pcredit"));

                int c1=c-jsonArraywk.length();
                int vc1=vc-jsonArraywk.length();

                if(c1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("c");
                }
                if(vc1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraybat.length()==2)
        {
            batl2.setVisibility(View.VISIBLE);
            batl3.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybat.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(0).getString("pimage")).into(batimg2);
                }
                batpname2.setText(jsonArraybat.getJSONObject(0).getString("pname"));
                if(!jsonArraybat.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(1).getString("pimage")).into(batimg3);
                }
                batpname3.setText(jsonArraybat.getJSONObject(1).getString("pname"));

                batcr2.setText(jsonArraybat.getJSONObject(0).getString("pcredit"));
                batcr3.setText(jsonArraybat.getJSONObject(1).getString("pcredit"));

                int c1=c-jsonArraywk.length();
                int vc1=vc-jsonArraywk.length();

                if(c1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("c");
                }
                if(vc1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("vc");
                }
                if(c1==1)
                {
                    batc3.setVisibility(View.VISIBLE);
                    batc3.setText("c");
                }
                if(vc1==1)
                {
                    batc3.setVisibility(View.VISIBLE);
                    batc3.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraybat.length()==3)
        {
            batl2.setVisibility(View.VISIBLE);
            batl3.setVisibility(View.VISIBLE);
            batl5.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybat.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(0).getString("pimage")).into(batimg2);
                }
                batpname2.setText(jsonArraybat.getJSONObject(0).getString("pname"));
                if(!jsonArraybat.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(1).getString("pimage")).into(batimg3);
                }
                batpname3.setText(jsonArraybat.getJSONObject(1).getString("pname"));
                if(!jsonArraybat.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(2).getString("pimage")).into(batimg5);
                }
                batpname5.setText(jsonArraybat.getJSONObject(2).getString("pname"));

                batcr2.setText(jsonArraybat.getJSONObject(0).getString("pcredit"));
                batcr3.setText(jsonArraybat.getJSONObject(1).getString("pcredit"));
                batcr5.setText(jsonArraybat.getJSONObject(2).getString("pcredit"));

                int c1=c-jsonArraywk.length();
                int vc1=vc-jsonArraywk.length();

                if(c1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("c");
                }
                if(vc1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("vc");
                }
                if(c1==1)
                {
                    batc3.setVisibility(View.VISIBLE);
                    batc3.setText("c");
                }
                if(vc1==1)
                {
                    batc3.setVisibility(View.VISIBLE);
                    batc3.setText("vc");
                }
                if(c1==2)
                {
                    batc5.setVisibility(View.VISIBLE);
                    batc5.setText("c");
                }
                if(vc1==2)
                {
                    batc5.setVisibility(View.VISIBLE);
                    batc5.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraybat.length()==4)
        {
            batl2.setVisibility(View.VISIBLE);
            batl3.setVisibility(View.VISIBLE);
            batl5.setVisibility(View.VISIBLE);
            batl6.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybat.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(0).getString("pimage")).into(batimg2);
                }
                batpname2.setText(jsonArraybat.getJSONObject(0).getString("pname"));
                if(!jsonArraybat.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(1).getString("pimage")).into(batimg3);
                }
                batpname3.setText(jsonArraybat.getJSONObject(1).getString("pname"));
                if(!jsonArraybat.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(2).getString("pimage")).into(batimg5);
                }
                batpname5.setText(jsonArraybat.getJSONObject(2).getString("pname"));
                if(!jsonArraybat.getJSONObject(3).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(3).getString("pimage")).into(batimg6);
                }
                batpname6.setText(jsonArraybat.getJSONObject(3).getString("pname"));

                batcr2.setText(jsonArraybat.getJSONObject(0).getString("pcredit"));
                batcr3.setText(jsonArraybat.getJSONObject(1).getString("pcredit"));
                batcr5.setText(jsonArraybat.getJSONObject(2).getString("pcredit"));
                batcr6.setText(jsonArraybat.getJSONObject(3).getString("pcredit"));

                int c1=c-jsonArraywk.length();
                int vc1=vc-jsonArraywk.length();

                if(c1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("c");
                }
                if(vc1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("vc");
                }
                if(c1==1)
                {
                    batc3.setVisibility(View.VISIBLE);
                    batc3.setText("c");
                }
                if(vc1==1)
                {
                    batc3.setVisibility(View.VISIBLE);
                    batc3.setText("vc");
                }
                if(c1==2)
                {
                    batc5.setVisibility(View.VISIBLE);
                    batc5.setText("c");
                }
                if(vc1==2)
                {
                    batc5.setVisibility(View.VISIBLE);
                    batc5.setText("vc");
                }
                if(c1==3)
                {
                    batc6.setVisibility(View.VISIBLE);
                    batc6.setText("c");
                }
                if(vc1==3)
                {
                    batc6.setVisibility(View.VISIBLE);
                    batc6.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraybat.length()==5)
        {
            batl2.setVisibility(View.VISIBLE);
            batl1.setVisibility(View.VISIBLE);
            batl3.setVisibility(View.VISIBLE);
            batl5.setVisibility(View.VISIBLE);
            batl6.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybat.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(0).getString("pimage")).into(batimg2);
                }
                batpname2.setText(jsonArraybat.getJSONObject(0).getString("pname"));
                if(!jsonArraybat.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(1).getString("pimage")).into(batimg3);
                }
                batpname3.setText(jsonArraybat.getJSONObject(1).getString("pname"));
                if(!jsonArraybat.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(2).getString("pimage")).into(batimg5);
                }
                batpname5.setText(jsonArraybat.getJSONObject(2).getString("pname"));
                if(!jsonArraybat.getJSONObject(3).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(3).getString("pimage")).into(batimg6);
                }
                batpname6.setText(jsonArraybat.getJSONObject(3).getString("pname"));
                if(!jsonArraybat.getJSONObject(4).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(4).getString("pimage")).into(batimg1);
                }
                batpname1.setText(jsonArraybat.getJSONObject(4).getString("pname"));

                batcr2.setText(jsonArraybat.getJSONObject(0).getString("pcredit"));
                batcr3.setText(jsonArraybat.getJSONObject(1).getString("pcredit"));
                batcr5.setText(jsonArraybat.getJSONObject(2).getString("pcredit"));
                batcr6.setText(jsonArraybat.getJSONObject(3).getString("pcredit"));
                batcr1.setText(jsonArraybat.getJSONObject(4).getString("pcredit"));

                int c1=c-jsonArraywk.length();
                int vc1=vc-jsonArraywk.length();

                if(c1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("c");
                }
                if(vc1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("vc");
                }
                if(c1==1)
                {
                    batc3.setVisibility(View.VISIBLE);
                    batc3.setText("c");
                }
                if(vc1==1)
                {
                    batc3.setVisibility(View.VISIBLE);
                    batc3.setText("vc");
                }
                if(c1==2)
                {
                    batc5.setVisibility(View.VISIBLE);
                    batc5.setText("c");
                }
                if(vc1==2)
                {
                    batc5.setVisibility(View.VISIBLE);
                    batc5.setText("vc");
                }
                if(c1==3)
                {
                    batc6.setVisibility(View.VISIBLE);
                    batc6.setText("c");
                }
                if(vc1==3)
                {
                    batc6.setVisibility(View.VISIBLE);
                    batc6.setText("vc");
                }
                if(c1==4)
                {
                    batc1.setVisibility(View.VISIBLE);
                    batc1.setText("c");
                }
                if(vc1==4)
                {
                    batc1.setVisibility(View.VISIBLE);
                    batc1.setText("vc");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraybat.length()==6)
        {
            batl2.setVisibility(View.VISIBLE);
            batl1.setVisibility(View.VISIBLE);
            batl3.setVisibility(View.VISIBLE);
            batl4.setVisibility(View.VISIBLE);
            batl5.setVisibility(View.VISIBLE);
            batl6.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybat.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(0).getString("pimage")).into(batimg2);
                }
                batpname2.setText(jsonArraybat.getJSONObject(0).getString("pname"));
                if(!jsonArraybat.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(1).getString("pimage")).into(batimg3);
                }
                batpname3.setText(jsonArraybat.getJSONObject(1).getString("pname"));
                if(!jsonArraybat.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(2).getString("pimage")).into(batimg5);
                }
                batpname5.setText(jsonArraybat.getJSONObject(2).getString("pname"));
                if(!jsonArraybat.getJSONObject(3).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(3).getString("pimage")).into(batimg6);
                }
                batpname6.setText(jsonArraybat.getJSONObject(3).getString("pname"));
                if(!jsonArraybat.getJSONObject(4).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(4).getString("pimage")).into(batimg1);
                }
                batpname1.setText(jsonArraybat.getJSONObject(4).getString("pname"));
                if(!jsonArraybat.getJSONObject(5).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybat.getJSONObject(5).getString("pimage")).into(batimg4);
                }
                batpname4.setText(jsonArraybat.getJSONObject(5).getString("pname"));

                batcr2.setText(jsonArraybat.getJSONObject(0).getString("pcredit"));
                batcr3.setText(jsonArraybat.getJSONObject(1).getString("pcredit"));
                batcr5.setText(jsonArraybat.getJSONObject(2).getString("pcredit"));
                batcr6.setText(jsonArraybat.getJSONObject(3).getString("pcredit"));
                batcr1.setText(jsonArraybat.getJSONObject(4).getString("pcredit"));
                batcr4.setText(jsonArraybat.getJSONObject(5).getString("pcredit"));
                int c1=c-jsonArraywk.length();
                int vc1=vc-jsonArraywk.length();

                if(c1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("c");
                }
                if(vc1==0)
                {
                    batc2.setVisibility(View.VISIBLE);
                    batc2.setText("vc");
                }
                if(c1==1)
                {
                    batc3.setVisibility(View.VISIBLE);
                    batc3.setText("c");
                }
                if(vc1==1)
                {
                    batc3.setVisibility(View.VISIBLE);
                    batc3.setText("vc");
                }
                if(c1==2)
                {
                    batc5.setVisibility(View.VISIBLE);
                    batc5.setText("c");
                }
                if(vc1==2)
                {
                    batc5.setVisibility(View.VISIBLE);
                    batc5.setText("vc");
                }
                if(c1==3)
                {
                    batc6.setVisibility(View.VISIBLE);
                    batc6.setText("c");
                }
                if(vc1==3)
                {
                    batc6.setVisibility(View.VISIBLE);
                    batc6.setText("vc");
                }
                if(c1==4)
                {
                    batc1.setVisibility(View.VISIBLE);
                    batc1.setText("c");
                }
                if(vc1==4)
                {
                    batc1.setVisibility(View.VISIBLE);
                    batc1.setText("vc");
                }
                if(c1==5)
                {
                    batc4.setVisibility(View.VISIBLE);
                    batc4.setText("c");
                }
                if(vc1==5)
                {
                    batc4.setVisibility(View.VISIBLE);
                    batc4.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(jsonArrayar.length()==1)
        {
            arl2.setVisibility(View.VISIBLE);
            try {
                if(!jsonArrayar.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArrayar.getJSONObject(0).getString("pimage")).into(arimg2);
                }
                arpname2.setText(jsonArrayar.getJSONObject(0).getString("pname"));

                arcr2.setText(jsonArrayar.getJSONObject(0).getString("pcredit"));

                int c1=c-jsonArraywk.length()-jsonArraybat.length();
                int vc1=vc-jsonArraywk.length()-jsonArraybat.length();

                if(c1==0)
                {
                    arc2.setVisibility(View.VISIBLE);
                    arc2.setText("c");
                }
                if(vc1==0)
                {
                    arc2.setVisibility(View.VISIBLE);
                    arc2.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArrayar.length()==2)
        {
            arl2.setVisibility(View.VISIBLE);
            arl3.setVisibility(View.VISIBLE);
            try {
                if(!jsonArrayar.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArrayar.getJSONObject(0).getString("pimage")).into(arimg2);
                }
                arpname2.setText(jsonArrayar.getJSONObject(0).getString("pname"));
                if(!jsonArrayar.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArrayar.getJSONObject(1).getString("pimage")).into(arimg3);
                }
                arpname3.setText(jsonArrayar.getJSONObject(1).getString("pname"));

                arcr2.setText(jsonArrayar.getJSONObject(0).getString("pcredit"));
                arcr3.setText(jsonArrayar.getJSONObject(1).getString("pcredit"));

                int c1=c-jsonArraywk.length()-jsonArraybat.length();
                int vc1=vc-jsonArraywk.length()-jsonArraybat.length();

                if(c1==0)
                {
                    arc2.setVisibility(View.VISIBLE);
                    arc2.setText("c");
                }
                if(vc1==0)
                {
                    arc2.setVisibility(View.VISIBLE);
                    arc2.setText("vc");
                }
                if(c1==1)
                {
                    arc3.setVisibility(View.VISIBLE);
                    arc3.setText("c");
                }
                if(vc1==1)
                {
                    arc3.setVisibility(View.VISIBLE);
                    arc3.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArrayar.length()==3)
        {
            arl2.setVisibility(View.VISIBLE);
            arl3.setVisibility(View.VISIBLE);
            arl4.setVisibility(View.VISIBLE);
            try {
                if(!jsonArrayar.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArrayar.getJSONObject(0).getString("pimage")).into(arimg2);
                }
                arpname2.setText(jsonArrayar.getJSONObject(0).getString("pname"));
                if(!jsonArrayar.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArrayar.getJSONObject(1).getString("pimage")).into(arimg3);
                }
                arpname3.setText(jsonArrayar.getJSONObject(1).getString("pname"));
                if(!jsonArrayar.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArrayar.getJSONObject(2).getString("pimage")).into(arimg4);
                }
                arpname4.setText(jsonArrayar.getJSONObject(2).getString("pname"));

                arcr2.setText(jsonArrayar.getJSONObject(0).getString("pcredit"));
                arcr3.setText(jsonArrayar.getJSONObject(1).getString("pcredit"));
                arcr4.setText(jsonArrayar.getJSONObject(2).getString("pcredit"));

                int c1=c-jsonArraywk.length()-jsonArraybat.length();
                int vc1=vc-jsonArraywk.length()-jsonArraybat.length();

                if(c1==0)
                {
                    arc2.setVisibility(View.VISIBLE);
                    arc2.setText("c");
                }
                if(vc1==0)
                {
                    arc2.setVisibility(View.VISIBLE);
                    arc2.setText("vc");
                }
                if(c1==1)
                {
                    arc3.setVisibility(View.VISIBLE);
                    arc3.setText("c");
                }
                if(vc1==1)
                {
                    arc3.setVisibility(View.VISIBLE);
                    arc3.setText("vc");
                }
                if(c1==2)
                {
                    arc4.setVisibility(View.VISIBLE);
                    arc4.setText("c");
                }
                if(vc1==2)
                {
                    arc4.setVisibility(View.VISIBLE);
                    arc4.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArrayar.length()==4)
        {
            arl2.setVisibility(View.VISIBLE);
            arl3.setVisibility(View.VISIBLE);
            arl4.setVisibility(View.VISIBLE);
            arl1.setVisibility(View.VISIBLE);
            try {
                if(!jsonArrayar.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArrayar.getJSONObject(1).getString("pimage")).into(arimg2);
                }
                arpname2.setText(jsonArrayar.getJSONObject(1).getString("pname"));
                if(!jsonArrayar.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArrayar.getJSONObject(2).getString("pimage")).into(arimg3);
                }
                arpname3.setText(jsonArrayar.getJSONObject(2).getString("pname"));
                if(!jsonArrayar.getJSONObject(3).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArrayar.getJSONObject(3).getString("pimage")).into(arimg4);
                }
                arpname4.setText(jsonArrayar.getJSONObject(3).getString("pname"));
                if(!jsonArrayar.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArrayar.getJSONObject(0).getString("pimage")).into(arimg1);
                }
                arpname1.setText(jsonArrayar.getJSONObject(0).getString("pname"));

                arcr2.setText(jsonArrayar.getJSONObject(1).getString("pcredit"));
                arcr3.setText(jsonArrayar.getJSONObject(2).getString("pcredit"));
                arcr4.setText(jsonArrayar.getJSONObject(3).getString("pcredit"));
                arcr1.setText(jsonArrayar.getJSONObject(0).getString("pcredit"));

                int c1=c-jsonArraywk.length()-jsonArraybat.length();
                int vc1=vc-jsonArraywk.length()-jsonArraybat.length();

                if(c1==1)
                {
                    arc2.setVisibility(View.VISIBLE);
                    arc2.setText("c");
                }
                if(vc1==1)
                {
                    arc2.setVisibility(View.VISIBLE);
                    arc2.setText("vc");
                }
                if(c1==2)
                {
                    arc3.setVisibility(View.VISIBLE);
                    arc3.setText("c");
                }
                if(vc1==2)
                {
                    arc3.setVisibility(View.VISIBLE);
                    arc3.setText("vc");
                }
                if(c1==3)
                {
                    arc4.setVisibility(View.VISIBLE);
                    arc4.setText("c");
                }
                if(vc1==3)
                {
                    arc4.setVisibility(View.VISIBLE);
                    arc4.setText("vc");
                }
                if(c1==0)
                {
                    arc1.setVisibility(View.VISIBLE);
                    arc1.setText("c");
                }
                if(vc1==0)
                {
                    arc1.setVisibility(View.VISIBLE);
                    arc1.setText("vc");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(jsonArraybowl.length()==1)
        {
            bowll2.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybowl.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(0).getString("pimage")).into(bowlimg2);
                }
                bowlpname2.setText(jsonArraybowl.getJSONObject(0).getString("pname"));

                bowlcr2.setText(jsonArraybowl.getJSONObject(0).getString("pcredit"));

                int c1=c-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();
                int vc1=vc-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();

                if(c1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("c");
                }
                if(vc1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraybowl.length()==2)
        {
            bowll2.setVisibility(View.VISIBLE);
            bowll3.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybowl.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(0).getString("pimage")).into(bowlimg2);
                }
                bowlpname2.setText(jsonArraybowl.getJSONObject(0).getString("pname"));
                if(!jsonArraybowl.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(1).getString("pimage")).into(bowlimg3);
                }
                bowlpname3.setText(jsonArraybowl.getJSONObject(1).getString("pname"));

                bowlcr2.setText(jsonArraybowl.getJSONObject(0).getString("pcredit"));
                bowlcr3.setText(jsonArraybowl.getJSONObject(1).getString("pcredit"));

                int c1=c-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();
                int vc1=vc-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();

                if(c1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("c");
                }
                if(vc1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("vc");
                }
                if(c1==1)
                {
                    bowlc3.setVisibility(View.VISIBLE);
                    bowlc3.setText("c");
                }
                if(vc1==1)
                {
                    bowlc3.setVisibility(View.VISIBLE);
                    bowlc3.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraybowl.length()==3)
        {
            bowll2.setVisibility(View.VISIBLE);
            bowll3.setVisibility(View.VISIBLE);
            bowll5.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybowl.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(0).getString("pimage")).into(bowlimg2);
                }
                bowlpname2.setText(jsonArraybowl.getJSONObject(0).getString("pname"));
                if(!jsonArraybowl.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(1).getString("pimage")).into(bowlimg3);
                }
                bowlpname3.setText(jsonArraybowl.getJSONObject(1).getString("pname"));
                if(!jsonArraybowl.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(2).getString("pimage")).into(bowlimg5);
                }
                bowlpname5.setText(jsonArraybowl.getJSONObject(2).getString("pname"));

                bowlcr2.setText(jsonArraybowl.getJSONObject(0).getString("pcredit"));
                bowlcr3.setText(jsonArraybowl.getJSONObject(1).getString("pcredit"));
                bowlcr5.setText(jsonArraybowl.getJSONObject(2).getString("pcredit"));

                int c1=c-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();
                int vc1=vc-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();

                if(c1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("c");
                }
                if(vc1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("vc");
                }
                if(c1==1)
                {
                    bowlc3.setVisibility(View.VISIBLE);
                    bowlc3.setText("c");
                }
                if(vc1==1)
                {
                    bowlc3.setVisibility(View.VISIBLE);
                    bowlc3.setText("vc");
                }
                if(c1==2)
                {
                    bowlc5.setVisibility(View.VISIBLE);
                    bowlc5.setText("c");
                }
                if(vc1==2)
                {
                    bowlc5.setVisibility(View.VISIBLE);
                    bowlc5.setText("vc");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraybowl.length()==4)
        {
            bowll2.setVisibility(View.VISIBLE);
            bowll3.setVisibility(View.VISIBLE);
            bowll5.setVisibility(View.VISIBLE);
            bowll6.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybowl.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(0).getString("pimage")).into(bowlimg2);
                }
                bowlpname2.setText(jsonArraybowl.getJSONObject(0).getString("pname"));
                if(!jsonArraybowl.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(1).getString("pimage")).into(bowlimg3);
                }
                bowlpname3.setText(jsonArraybowl.getJSONObject(1).getString("pname"));
                if(!jsonArraybowl.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(2).getString("pimage")).into(bowlimg5);
                }
                bowlpname5.setText(jsonArraybowl.getJSONObject(2).getString("pname"));
                if(!jsonArraybowl.getJSONObject(3).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(3).getString("pimage")).into(bowlimg6);
                }
                bowlpname6.setText(jsonArraybowl.getJSONObject(3).getString("pname"));

                bowlcr2.setText(jsonArraybowl.getJSONObject(0).getString("pcredit"));
                bowlcr3.setText(jsonArraybowl.getJSONObject(1).getString("pcredit"));
                bowlcr5.setText(jsonArraybowl.getJSONObject(2).getString("pcredit"));
                bowlcr6.setText(jsonArraybowl.getJSONObject(3).getString("pcredit"));
                int c1=c-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();
                int vc1=vc-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();

                if(c1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("c");
                }
                if(vc1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("vc");
                }
                if(c1==1)
                {
                    bowlc3.setVisibility(View.VISIBLE);
                    bowlc3.setText("c");
                }
                if(vc1==1)
                {
                    bowlc3.setVisibility(View.VISIBLE);
                    bowlc3.setText("vc");
                }
                if(c1==2)
                {
                    bowlc5.setVisibility(View.VISIBLE);
                    bowlc5.setText("c");
                }
                if(vc1==2)
                {
                    bowlc5.setVisibility(View.VISIBLE);
                    bowlc5.setText("vc");
                }
                if(c1==3)
                {
                    bowlc6.setVisibility(View.VISIBLE);
                    bowlc6.setText("c");
                }
                if(vc1==3)
                {
                    bowlc6.setVisibility(View.VISIBLE);
                    bowlc6.setText("vc");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraybowl.length()==5)
        {
            bowll2.setVisibility(View.VISIBLE);
            bowll1.setVisibility(View.VISIBLE);
            bowll3.setVisibility(View.VISIBLE);
            bowll5.setVisibility(View.VISIBLE);
            bowll6.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybowl.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(0).getString("pimage")).into(bowlimg2);
                }
                bowlpname2.setText(jsonArraybowl.getJSONObject(0).getString("pname"));
                if(!jsonArraybowl.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(1).getString("pimage")).into(bowlimg3);
                }
                bowlpname3.setText(jsonArraybowl.getJSONObject(1).getString("pname"));
                if(!jsonArraybowl.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(2).getString("pimage")).into(bowlimg5);
                }
                bowlpname5.setText(jsonArraybowl.getJSONObject(2).getString("pname"));
                if(!jsonArraybowl.getJSONObject(3).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(3).getString("pimage")).into(bowlimg6);
                }
                bowlpname6.setText(jsonArraybowl.getJSONObject(3).getString("pname"));
                if(!jsonArraybowl.getJSONObject(4).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(4).getString("pimage")).into(bowlimg1);
                }
                bowlpname1.setText(jsonArraybowl.getJSONObject(4).getString("pname"));

                bowlcr2.setText(jsonArraybowl.getJSONObject(0).getString("pcredit"));
                bowlcr3.setText(jsonArraybowl.getJSONObject(1).getString("pcredit"));
                bowlcr5.setText(jsonArraybowl.getJSONObject(2).getString("pcredit"));
                bowlcr6.setText(jsonArraybowl.getJSONObject(3).getString("pcredit"));
                bowlcr1.setText(jsonArraybowl.getJSONObject(4).getString("pcredit"));

                int c1=c-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();
                int vc1=vc-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();

                if(c1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("c");
                }
                if(vc1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("vc");
                }
                if(c1==1)
                {
                    bowlc3.setVisibility(View.VISIBLE);
                    bowlc3.setText("c");
                }
                if(vc1==1)
                {
                    bowlc3.setVisibility(View.VISIBLE);
                    bowlc3.setText("vc");
                }
                if(c1==2)
                {
                    bowlc5.setVisibility(View.VISIBLE);
                    bowlc5.setText("c");
                }
                if(vc1==2)
                {
                    bowlc5.setVisibility(View.VISIBLE);
                    bowlc5.setText("vc");
                }
                if(c1==3)
                {
                    bowlc6.setVisibility(View.VISIBLE);
                    bowlc6.setText("c");
                }
                if(vc1==3)
                {
                    bowlc6.setVisibility(View.VISIBLE);
                    bowlc6.setText("vc");
                }
                if(c1==4)
                {
                    bowlc1.setVisibility(View.VISIBLE);
                    bowlc1.setText("c");
                }
                if(vc1==4)
                {
                    bowlc1.setVisibility(View.VISIBLE);
                    bowlc1.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(jsonArraybowl.length()==6)
        {
            bowll2.setVisibility(View.VISIBLE);
            bowll1.setVisibility(View.VISIBLE);
            bowll3.setVisibility(View.VISIBLE);
            bowll4.setVisibility(View.VISIBLE);
            bowll5.setVisibility(View.VISIBLE);
            bowll6.setVisibility(View.VISIBLE);
            try {
                if(!jsonArraybowl.getJSONObject(0).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(0).getString("pimage")).into(bowlimg2);
                }
                bowlpname2.setText(jsonArraybowl.getJSONObject(0).getString("pname"));
                if(!jsonArraybowl.getJSONObject(1).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(1).getString("pimage")).into(bowlimg3);
                }
                bowlpname3.setText(jsonArraybowl.getJSONObject(1).getString("pname"));
                if(!jsonArraybowl.getJSONObject(2).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(2).getString("pimage")).into(bowlimg5);
                }
                bowlpname5.setText(jsonArraybowl.getJSONObject(2).getString("pname"));
                if(!jsonArraybowl.getJSONObject(3).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(3).getString("pimage")).into(bowlimg6);
                }
                bowlpname6.setText(jsonArraybowl.getJSONObject(3).getString("pname"));
                if(!jsonArraybowl.getJSONObject(4).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(4).getString("pimage")).into(bowlimg1);
                }
                bowlpname1.setText(jsonArraybowl.getJSONObject(4).getString("pname"));
                if(!jsonArraybowl.getJSONObject(5).getString("pimage").equals("")) {
                    Picasso.get().load(jsonArraybowl.getJSONObject(5).getString("pimage")).into(bowlimg4);
                }
                bowlpname4.setText(jsonArraybowl.getJSONObject(5).getString("pname"));

                bowlcr2.setText(jsonArraybowl.getJSONObject(0).getString("pcredit"));
                bowlcr3.setText(jsonArraybowl.getJSONObject(1).getString("pcredit"));
                bowlcr5.setText(jsonArraybowl.getJSONObject(2).getString("pcredit"));
                bowlcr6.setText(jsonArraybowl.getJSONObject(3).getString("pcredit"));
                bowlcr1.setText(jsonArraybowl.getJSONObject(4).getString("pcredit"));
                bowlcr4.setText(jsonArraybowl.getJSONObject(5).getString("pcredit"));


                int c1=c-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();
                int vc1=vc-jsonArraywk.length()-jsonArraybat.length()-jsonArrayar.length();

                if(c1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("c");
                }
                if(vc1==0)
                {
                    bowlc2.setVisibility(View.VISIBLE);
                    bowlc2.setText("vc");
                }
                if(c1==1)
                {
                    bowlc3.setVisibility(View.VISIBLE);
                    bowlc3.setText("c");
                }
                if(vc1==1)
                {
                    bowlc3.setVisibility(View.VISIBLE);
                    bowlc3.setText("vc");
                }
                if(c1==2)
                {
                    bowlc5.setVisibility(View.VISIBLE);
                    bowlc5.setText("c");
                }
                if(vc1==2)
                {
                    bowlc5.setVisibility(View.VISIBLE);
                    bowlc5.setText("vc");
                }
                if(c1==3)
                {
                    bowlc6.setVisibility(View.VISIBLE);
                    bowlc6.setText("c");
                }
                if(vc1==3)
                {
                    bowlc6.setVisibility(View.VISIBLE);
                    bowlc6.setText("vc");
                }
                if(c1==4)
                {
                    bowlc1.setVisibility(View.VISIBLE);
                    bowlc1.setText("c");
                }
                if(vc1==4)
                {
                    bowlc1.setVisibility(View.VISIBLE);
                    bowlc1.setText("vc");
                }
                if(c1==5)
                {
                    bowlc4.setVisibility(View.VISIBLE);
                    bowlc4.setText("c");
                }
                if(vc1==5)
                {
                    bowlc4.setVisibility(View.VISIBLE);
                    bowlc4.setText("vc");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }




    }

}
