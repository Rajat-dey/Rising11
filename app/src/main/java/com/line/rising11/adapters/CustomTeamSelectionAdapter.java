package com.line.rising11.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.line.rising11.JSONParser;
import com.line.rising11.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomTeamSelectionAdapter extends RecyclerView.Adapter<CustomTeamSelectionAdapter.CustomViewHolder> {
    private JSONArray customerList;

    public CustomTeamSelectionAdapter(JSONArray customerList) {
        this.customerList = customerList;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_team_selection_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position)
    {
        try
        {


            Picasso.get().load(customerList.getJSONObject(position).get("image").toString()).into(holder.civDP);
            holder.tvUsername.setText(customerList.getJSONObject(position).get("player_name").toString());
            holder.tvCredits.setText(customerList.getJSONObject(position).get("credits").toString());
            holder.iv_add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(holder.f==0) {
                        holder.iv_add_btn.setImageResource(R.drawable.ic_remove_circle_outline_red_24dp);

                        holder.f=1;
                    }
                    else
                    {
                        holder.iv_add_btn.setImageResource(R.drawable.ic_add_circle_outline_black_24dp);
                        holder.f=0;
                    }
                }
            });




        }
        catch (JSONException e) {
            e.printStackTrace();
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
        private TextView tvCredits;
        private ImageView iv_add_btn;
        int f=0;

        public CustomViewHolder (View view) {
            super(view);
            tvUsername=view.findViewById(R.id.tv_player_name);
            civDP=view.findViewById(R.id.iv_player_photo);
            tvCredits=view.findViewById(R.id.tv_credits);
            iv_add_btn=view.findViewById(R.id.iv_add_button);

        }

    }
}
