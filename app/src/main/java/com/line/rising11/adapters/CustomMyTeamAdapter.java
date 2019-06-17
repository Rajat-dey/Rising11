package com.line.rising11.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.line.rising11.R;

import org.json.JSONArray;

import java.util.ArrayList;

public class CustomMyTeamAdapter extends RecyclerView.Adapter<CustomMyTeamAdapter.CustomViewHolder> {

    private Context context;


    public CustomMyTeamAdapter(Context context) {

        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_myteam_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position)
    {


    }


    @Override
    public int getItemCount() {
        return 2;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{


        public CustomViewHolder (View view) {
            super(view);


        }

    }
}
