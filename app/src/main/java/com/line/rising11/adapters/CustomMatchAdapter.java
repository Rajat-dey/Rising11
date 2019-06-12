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
import android.widget.TextView;

import com.line.rising11.ContestsActivity;
import com.line.rising11.R;
import com.line.rising11.TeamSelectionActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class CustomMatchAdapter extends RecyclerView.Adapter<CustomMatchAdapter.CustomViewHolder> {
    private JSONArray customerList;
    private Context context;
    int a[]={52000,50000,40000,45000,25000,55000,28000,36000,59000};

    public CustomMatchAdapter(Context context, JSONArray customerList) {
        this.customerList = customerList;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_match_card_new, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position)
    {
        try
        {
            if(holder.countDownTimer !=null)
            {
                holder.countDownTimer.cancel();
            }
            holder.countDownTimer=new CountDownTimer(a[position],1000)
            {
                @Override
                public void onTick(long millisUntilFinished)
                {
                    int second = (int) (millisUntilFinished /1000) %60;
                    String timeformate = String.format(Locale.getDefault(),  "%02d",second);

                    holder.tvipl.setText(timeformate+"s left");

                    holder.tvipl.setTextColor(Color.RED);


                }

                @Override
                public void onFinish()
                {
                    holder.tvipl.setText("00"+"s left");

                    holder.tvipl.setTextColor(Color.RED);
                    holder.cvTeam.setClickable(false);
                    holder.cvTeam.setAlpha(0.6f);
                }
            }.start();
            holder.name1.setText(customerList.getJSONObject(position).get("team-1").toString());
            holder.name2.setText(customerList.getJSONObject(position).get("team-2").toString());

            holder.cvTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                    try {
                        Intent intent = new Intent(context, ContestsActivity.class);
                        intent.putExtra("uid",customerList.getJSONObject(position).get("unique_id").toString());
                        context.startActivity(intent);
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
            });
        }
        catch (JSONException e)
        {

        }

    }


    @Override
    public int getItemCount() {
        return customerList.length();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        private CircularImageView civDP;
        private TextView tvUsername;
        private TextView tvMsg;
        private TextView tvDateTime;
        private TextView tvipl;
        private CardView cvTeam;
        private TextView name1,name2;
        CountDownTimer countDownTimer;

        public CustomViewHolder (View view) {
            super(view);

            cvTeam = view.findViewById(R.id.cv_team);
            name1=view.findViewById(R.id.tv_team1_name);
            name2=view.findViewById(R.id.tv_team2_name);
            tvipl=view.findViewById(R.id.tv_title_ipl);


        }

    }
}
