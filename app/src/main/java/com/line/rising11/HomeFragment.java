package com.line.rising11;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import com.line.rising11.adapters.CustomMatchAdapter;

import org.json.JSONArray;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView rvHomeMatches;
    private TabLayout tb_home;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        view.findViewById(R.id.rv_home_matches).setFocusable(false);
        view.findViewById(R.id.rl).requestFocus();
        tb_home=view.findViewById(R.id.tb_home);
        rvHomeMatches = view.findViewById(R.id.rv_home_matches);
        rvHomeMatches.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvHomeMatches.setAdapter(new CustomMatchAdapter(getContext(), new JSONArray()));
        tb_home.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1 )
                {
                    Intent intent=new Intent(getContext(),Football_Home_Activity.class);
                    startActivity(intent);
                }
                else if(tab.getPosition()==2)
                {
                    Intent intent=new Intent(getContext(),Kabaddi_Home_Activity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    public void openJoinContest(View view) {
        Intent intent = new Intent(getContext(), JoinedContest.class);
        startActivity(intent);
    }



}
