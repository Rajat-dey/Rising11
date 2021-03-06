package com.line.rising11.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.line.rising11.JSONParser;
import com.line.rising11.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomTeamSelectionAdapter extends RecyclerView.Adapter<CustomTeamSelectionAdapter.CustomViewHolder> {
    private JSONArray customerList;
    private OnAddListner monAddListner;
    ArrayList<String> listforcheckadd;
    float credit_left;
    int tposition;
    ArrayList<String> credit=new ArrayList<>();
    int totalselectedplayer,tabplayerselected,maxlimittabplayer;

    public CustomTeamSelectionAdapter(JSONArray customerList,OnAddListner onAddListner,ArrayList<String> listforchekadd,float credit_left,int totalselectedplayer,int tabplayerselected,int maxlimittabplayer,int tposition) {
        this.customerList = customerList;
        this.monAddListner=onAddListner;
        this.listforcheckadd=listforchekadd;
        this.credit_left=credit_left;
        this.totalselectedplayer=totalselectedplayer;
        this.tabplayerselected=tabplayerselected;
        this.maxlimittabplayer=maxlimittabplayer;
        this.tposition=tposition;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_team_selection_list, parent, false);
        return new CustomViewHolder(view,monAddListner);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position)
    {
        try
        {
            String teamname="India";
            if(tposition==0)
            {
                teamname=customerList.getJSONObject(position).getString("team_name")+" - WK";
            }
            else if(tposition==1)
            {
                teamname=customerList.getJSONObject(position).getString("team_name")+" - BAT";
            }
            else if(tposition==2)
            {
                teamname=customerList.getJSONObject(position).getString("team_name")+" - ALL";
            }
            else if(tposition==3)
            {
                teamname=customerList.getJSONObject(position).getString("team_name")+" - BOWL";
            }
            if(!customerList.getJSONObject(position).get("image").toString().equals("")) {
                Picasso.get().load(customerList.getJSONObject(position).get("image").toString()).into(holder.civDP);
            }
            holder.tvUsername.setText(customerList.getJSONObject(position).get("player_name").toString());
            holder.tvCredits.setText(customerList.getJSONObject(position).get("credits").toString());
           credit.add(customerList.getJSONObject(position).get("credits").toString());
           holder.tv_teams.setText(teamname);
            if(listforcheckadd.get(position).equals("0"))
            {
                holder.iv_add_btn.setImageResource(R.drawable.ic_add_circle_outline_green_24dp);
            }
            else
            {
                holder.iv_add_btn.setImageResource(R.drawable.ic_remove_circle_outline_red_24dp);
            }





        }
        catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return customerList.length();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CircularImageView civDP;
        private TextView tvUsername;
        private TextView tvMsg;
        private TextView tvDateTime;
        private TextView tvCredits;
        private ImageView iv_add_btn;
        private TextView tv_teams;
        OnAddListner onAddListner;


        public CustomViewHolder (View view, OnAddListner onAddListner) {
            super(view);
            this.onAddListner=onAddListner;
            tvUsername=view.findViewById(R.id.tv_player_name);
            civDP=view.findViewById(R.id.iv_player_photo);
            tvCredits=view.findViewById(R.id.tv_credits);

            iv_add_btn=view.findViewById(R.id.iv_add_button);
            tv_teams=view.findViewById(R.id.tv_teams);
            iv_add_btn.setOnClickListener(this);


        }

        @Override
        public void onClick(View v)
        {
            int p = getAdapterPosition();



                if (listforcheckadd.get(p).equals("0")  ) {
                    if(tabplayerselected<maxlimittabplayer && totalselectedplayer<11) {

                        iv_add_btn.setImageResource(R.drawable.ic_remove_circle_outline_red_24dp);
                        tabplayerselected=tabplayerselected+1;
                        totalselectedplayer=totalselectedplayer+1;
                        credit_left=credit_left-Float.parseFloat(credit.get(p));
                        onAddListner.onAddClick(p);
                    }
                    else if(Float.parseFloat(credit.get(p))<credit_left)
                    {
                        onAddListner.onAddClick(-1);
                    }
                    else
                    {
                        onAddListner.onAddClick(-2);
                    }

                }
                else  {
                    iv_add_btn.setImageResource(R.drawable.ic_add_circle_outline_green_24dp);
                    tabplayerselected=tabplayerselected-1;
                    totalselectedplayer=totalselectedplayer-1;
                    credit_left=credit_left+Float.parseFloat(credit.get(p));
                    onAddListner.onAddClick(p);

                }



        }

    }
    public interface OnAddListner
    {
        void onAddClick(int position);
    }
}
