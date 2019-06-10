package com.line.rising11.adapters;

import android.content.Context;
import android.content.Intent;
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

public class CustomMatchAdapter extends RecyclerView.Adapter<CustomMatchAdapter.CustomViewHolder> {
    private JSONArray customerList;
    private Context context;

    public CustomMatchAdapter(Context context, JSONArray customerList) {
        this.customerList = customerList;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_match_card, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position)
    {
        try
        {
            holder.name1.setText(customerList.getJSONObject(position).get("team-1").toString());
            holder.name2.setText(customerList.getJSONObject(position).get("team-2").toString());
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
        private CardView cvTeam;
        private TextView name1,name2;

        public CustomViewHolder (View view) {
            super(view);

            cvTeam = view.findViewById(R.id.cv_team);
            name1=view.findViewById(R.id.tv_team1_name);
            name2=view.findViewById(R.id.tv_team2_name);
            cvTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ContestsActivity.class);
                    context.startActivity(intent);
                }
            });

        }

    }
}
