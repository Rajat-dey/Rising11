package com.line.rising11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.line.rising11.adapters.CustomTeamSelectionAdapter;

import org.json.JSONArray;

public class TeamSelectionActivity extends AppCompatActivity {

    private RecyclerView rvTeamSelection;
    private Button btnContinue;
    private Button btnTeamPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_selection);

        rvTeamSelection = findViewById(R.id.rv_team_selection);
        rvTeamSelection.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(new JSONArray()));

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

    }
}
