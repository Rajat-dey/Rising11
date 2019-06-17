package com.line.rising11.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.line.rising11.ContestsActivity;
import com.line.rising11.JoinedContest;
import com.line.rising11.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class CustomMyMatchAdapter extends RecyclerView.Adapter<CustomMyMatchAdapter.CustomViewHolder> {
    private JSONArray customerList;
    private Context context;
   ArrayList<String> uid,teamname1,teamname2,time,joincontestlist,tabtype;
   int tab;

    public CustomMyMatchAdapter(Context context, ArrayList<String> uid,ArrayList<String> teamname1,ArrayList<String> teamname2,ArrayList<String> time,ArrayList<String> joincontestlist,ArrayList<String> tabtype,int tab) {
        this.uid=uid;
        this.teamname1=teamname1;
        this.teamname2=teamname2;
        this.time=time;
        this.joincontestlist=joincontestlist;
        this.tab=tab;
        this.tabtype=tabtype;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_match_custom_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position)
    {
        if(!joincontestlist.get(position).equals("0"))
        {
            if(tab==0 && tabtype.get(position).equals("upcoming"))
            {
                holder.team1.setText(teamname1.get(position));
                holder.team2.setText(teamname2.get(position));
                holder.joincontest.setText(joincontestlist.get(position));
            }
            else if(tab==1 && tabtype.get(position).equals("live"))
            {
                holder.team1.setText(teamname1.get(position));
                holder.team2.setText(teamname2.get(position));
                holder.joincontest.setText(joincontestlist.get(position));
            }
        }
        holder.matchll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, JoinedContest.class);
                i.putExtra("team1",teamname1.get(position));
                i.putExtra("team2",teamname2.get(position));
                i.putExtra("uid",uid.get(position));
                context.startActivity(i);
            }
        });


    }


    @Override
    public int getItemCount() {
        return uid.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView team1,team2,joincontest;
        LinearLayout matchll;
                public CustomViewHolder (View view) {
            super(view);

                team1=view.findViewById(R.id.tm1);
                team2=view.findViewById(R.id.tm2);
                joincontest=view.findViewById(R.id.textView3);
                    matchll=view.findViewById(R.id.matchll);

        }

    }
}
