package com.line.rising11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.line.rising11.adapters.CustomTeamSelectionAdapter;

import org.json.JSONArray;

public class TeamSelectionActivity extends AppCompatActivity {

    private RecyclerView rvTeamSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_selection);

        rvTeamSelection = findViewById(R.id.rv_team_selection);
        rvTeamSelection.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvTeamSelection.setAdapter(new CustomTeamSelectionAdapter(new JSONArray()));

    }
}
