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
    ArrayList<String> teamnumber,teamname1,teamname2,team1count,team2count,cimage,cname,vcimage,vcname,wk,bat,ar,bowl;


    public CustomMyTeamAdapter(Context context,ArrayList<String> teamnumber,ArrayList<String> teamname1,ArrayList<String> teamname2,ArrayList<String> team1count,ArrayList<String> team2count,ArrayList<String> cimage,ArrayList<String> cname,ArrayList<String> vcimage,ArrayList<String> vcname,ArrayList<String> wk,ArrayList<String> bat,ArrayList<String> ar,ArrayList<String> bowl) {

        this.context = context;
        this.teamnumber=teamnumber;
        this.teamname1=teamname1;
        this.teamname2=teamname2;
        this.team1count=team1count;
        this.team2count=team2count;
        this.cimage=cimage;
        this.cname=cname;
        this.vcimage=vcimage;
        this.vcname=vcname;
        this.wk=wk;
        this.bat=bat;
        this.ar=ar;
        this.bowl=bowl;
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
        if(!cimage.get(position).equals(""))
        {
            Picasso.get().load(cimage.get(position)).into(holder.pimage1);
        }
        if(!vcimage.get(position).equals("")) {

            Picasso.get().load(vcimage.get(position)).into(holder.pimage2);
        }
        holder.teamnumber.setText("(T"+teamnumber.get(position)+")");
        holder.t1name.setText(teamname1.get(position));
        holder.t2name.setText(teamname2.get(position));
        holder.t1c.setText(team1count.get(position));
        holder.t2c.setText(team2count.get(position));
        holder.cname.setText(cname.get(position));
        holder.vcname.setText(vcname.get(position));
        holder.wk.setText(wk.get(position));
        holder.bat.setText(bat.get(position));
        holder.ar.setText(ar.get(position));
        holder.bowl.setText(bowl.get(position));
    }


    @Override
    public int getItemCount() {
        return teamnumber.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder
    {
        ImageView pimage1,pimage2;
        TextView teamnumber,t1name,t1c,t2name,t2c,cname,vcname,wk,bat,ar,bowl;

        public CustomViewHolder (View view) {
            super(view);
            pimage1=view.findViewById(R.id.pimage1);
            pimage2=view.findViewById(R.id.pimage2);
            teamnumber=view.findViewById(R.id.teamnumber);
            t1name=view.findViewById(R.id.t1name);
            t2name=view.findViewById(R.id.t2name);
            t1c=view.findViewById(R.id.t1c);
            t2c=view.findViewById(R.id.t2c);
            wk=view.findViewById(R.id.wk);
            bat=view.findViewById(R.id.bat);
            ar=view.findViewById(R.id.ar);
            bowl=view.findViewById(R.id.bowl);
            cname=view.findViewById(R.id.cname);
            vcname=view.findViewById(R.id.vcname);


        }

    }
}
