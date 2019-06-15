package com.line.rising11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.line.rising11.adapters.CustomCapViceCapSelectionAdapter;
import com.paytm.pgsdk.Log;

import org.json.JSONArray;

import java.util.ArrayList;

import static com.paytm.pgsdk.easypay.manager.PaytmAssist.getContext;

public class CaptainViceCaptainSelectionActivity extends AppCompatActivity implements CustomCapViceCapSelectionAdapter.OnAddListner1 {

    private RecyclerView rvHomeMatches;
    ArrayList<String> pname,pimage,pteam,ctype,vctype;
    CustomCapViceCapSelectionAdapter customCapViceCapSelectionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain_vice_captain_selection);

        pname=getIntent().getStringArrayListExtra("pname");
        pimage=getIntent().getStringArrayListExtra("pimage");
        pteam=getIntent().getStringArrayListExtra("pteam");
        ctype=new ArrayList<>();
        vctype=new ArrayList<>();
        for(int i=0;i<11;i++)
        {
            ctype.add("0");
            vctype.add("0");
        }
        rvHomeMatches = findViewById(R.id.rv_cap_vice_cap);
        rvHomeMatches.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        customCapViceCapSelectionAdapter=new CustomCapViceCapSelectionAdapter(getContext(),pname,pimage,CaptainViceCaptainSelectionActivity.this,pteam,ctype,vctype);
        rvHomeMatches.setAdapter(customCapViceCapSelectionAdapter);
    }

    @Override
    public void onAddClick(int position,String type)
    {

        if(type.equals("c"))
        {
            ctype=new ArrayList<>();
            for(int i=0;i<11;i++)
            {
                if(i==position)
                {
                    ctype.add("1");
                    vctype.set(position,"0");

                }
                else {
                    ctype.add("0");
                }
            }

            rvHomeMatches = findViewById(R.id.rv_cap_vice_cap);
            rvHomeMatches.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            customCapViceCapSelectionAdapter=new CustomCapViceCapSelectionAdapter(getContext(),pname,pimage,CaptainViceCaptainSelectionActivity.this,pteam,ctype,vctype);
            rvHomeMatches.setAdapter(customCapViceCapSelectionAdapter);
        }
        else if(type.equals("vc"))
        {
            vctype=new ArrayList<>();
            for(int i=0;i<11;i++)
            {
                if(i==position)
                {
                    vctype.add("1");
                    ctype.set(position,"0");

                }
                else {
                    vctype.add("0");
                }
            }

            rvHomeMatches = findViewById(R.id.rv_cap_vice_cap);
            rvHomeMatches.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            customCapViceCapSelectionAdapter=new CustomCapViceCapSelectionAdapter(getContext(),pname,pimage,CaptainViceCaptainSelectionActivity.this,pteam,ctype,vctype);
            rvHomeMatches.setAdapter(customCapViceCapSelectionAdapter);
        }
    }
}
