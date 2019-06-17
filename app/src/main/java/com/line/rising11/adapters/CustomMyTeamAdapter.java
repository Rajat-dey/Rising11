package com.line.rising11.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.line.rising11.R;
import com.squareup.picasso.Picasso;

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

        Picasso.get().load("https://www.cricapi.com/playerpic/34102.jpg").into(holder.pimage1);
        Picasso.get().load("https://www.cricapi.com/playerpic/253802.jpg").into(holder.pimage2);
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder
    {
        ImageView pimage1,pimage2;

        public CustomViewHolder (View view) {
            super(view);
            pimage1=view.findViewById(R.id.pimage1);
            pimage2=view.findViewById(R.id.pimage2);


        }

    }
}
