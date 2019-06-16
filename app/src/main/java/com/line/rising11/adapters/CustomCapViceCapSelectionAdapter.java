package com.line.rising11.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.line.rising11.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;

public class CustomCapViceCapSelectionAdapter extends RecyclerView.Adapter<CustomCapViceCapSelectionAdapter.CustomViewHolder> {
    private JSONArray customerList;
    private Context context;
    private OnAddListner1 monAddListner;
    ArrayList<String>pname,pimage,pteam,ctype,vctype;

    public CustomCapViceCapSelectionAdapter(Context context, ArrayList<String> pname,ArrayList<String>pimage,OnAddListner1 onAddListner,ArrayList<String> pteam,ArrayList<String> ctype,ArrayList<String> vctype) {
        /*this.customerList = customerList;*/

        this.context = context;
        this.pname=pname;
        this.pimage=pimage;
        this.pteam=pteam;
        this.monAddListner=onAddListner;
        this.ctype=ctype;
        this.vctype=vctype;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_cap_vice_cap_list, parent, false);
        return new CustomViewHolder(view,monAddListner);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position)
    {
        if(!pimage.get(position).equals("")) {
            Picasso.get().load(pimage.get(position)).into(holder.civDP);
        }
        holder.tvUsername.setText(pname.get(position));
        holder.tv_teams.setText(pteam.get(position));

        if(ctype.get(position).equals("0") && vctype.get(position).equals("0"))
        {
            holder.iv_add_C.setText("C");
            holder.iv_add_VC.setText("VC");
        }
        else if(ctype.get(position).equals("1") && vctype.get(position).equals("1"))
        {
            holder.iv_add_C.setText("2x");
            holder.iv_add_VC.setText("1.5x");
        }
        else if(ctype.get(position).equals("1") && vctype.get(position).equals("0"))
        {
            holder.iv_add_C.setText("2x");
            holder.iv_add_VC.setText("VC");
        }
        else if(ctype.get(position).equals("0") && vctype.get(position).equals("1"))
        {
            holder.iv_add_C.setText("C");
            holder.iv_add_VC.setText("1.5x");
        }
    }


    @Override
    public int getItemCount() {
        return pname.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private CircularImageView civDP;
        private TextView tvUsername;
        private TextView tvMsg;
        private TextView tvDateTime,tv_teams;
        OnAddListner1 onAddListner;
        private TextView iv_add_C,iv_add_VC;


        public CustomViewHolder (View view,OnAddListner1 onAddListner)
        {
            super(view);
            this.onAddListner=onAddListner;
            civDP=view.findViewById(R.id.iv_player_photo);
            tvUsername=view.findViewById(R.id.tv_player_name);
            iv_add_C=view.findViewById(R.id.iv_add_C);

            iv_add_VC=view.findViewById(R.id.iv_add_VC);

            tv_teams=view.findViewById(R.id.tv_teams);
            iv_add_C.setOnClickListener(this);
            iv_add_VC.setOnClickListener(this);



        }


        @Override
        public void onClick(View v)
        {
           if(v.getId()==iv_add_C.getId())
           {
               onAddListner.onAddClick(getAdapterPosition(),"c");
           }
           else if(v.getId()==iv_add_VC.getId())
           {
               onAddListner.onAddClick(getAdapterPosition(),"vc");
           }
        }
    }
    public interface OnAddListner1
    {
        void onAddClick(int position,String type);
    }
}
