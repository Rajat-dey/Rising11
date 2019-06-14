package com.line.rising11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.line.rising11.adapters.CustomCapViceCapSelectionAdapter;

import org.json.JSONArray;

import java.util.ArrayList;

import static com.paytm.pgsdk.easypay.manager.PaytmAssist.getContext;

public class CaptainViceCaptainSelectionActivity extends AppCompatActivity {

    private RecyclerView rvHomeMatches;
    ArrayList<String> pname,pimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain_vice_captain_selection);

        pname=getIntent().getStringArrayListExtra("pname");
        pimage=getIntent().getStringArrayListExtra("pimage");
        rvHomeMatches = findViewById(R.id.rv_cap_vice_cap);
        rvHomeMatches.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvHomeMatches.setAdapter(new CustomCapViceCapSelectionAdapter(getContext(),pname,pimage));
    }
}
