package com.line.rising11;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;
import com.line.rising11.adapters.CustomTeamSelectionAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TeamSelectionActivity extends AppCompatActivity {

    private RecyclerView rvTeamSelection;
    private Button btnContinue;
    private Button btnTeamPreview;
    private ViewPager pageview;

    int mCurrentPage=0;
    int flag=0;
    private PageviewPlayerAdapter pageviewPlayerAdapter;
    JsonArray jsonArrayWK , jsonArrayBAT, jsonArrayAR, jsonArrayBOWL;
    private TabLayout tab_home;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_selection);

        /*rvTeamSelection = findViewById(R.id.rv_team_selection);
        rvTeamSelection.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(new JSONArray()));*/

        btnContinue = findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamSelectionActivity.this, CaptainViceCaptainSelectionActivity.class);
                startActivity(intent);
            }
        });

        btnTeamPreview = findViewById(R.id.btn_team_preview);
        btnTeamPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamSelectionActivity.this, TeamPreviewActivity.class);
                startActivity(intent);
            }
        });

        tab_home=findViewById(R.id.tb_home);


        pageview=findViewById(R.id.pageview);
        pageviewPlayerAdapter= new PageviewPlayerAdapter(getSupportFragmentManager());
        pageview.setAdapter(pageviewPlayerAdapter);

        pageview.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mCurrentPage=i;

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        tab_home.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                pageview.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });


    }


}
