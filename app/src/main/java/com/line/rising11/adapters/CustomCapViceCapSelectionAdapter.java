package com.line.rising11.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.line.rising11.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;

public class CustomCapViceCapSelectionAdapter extends RecyclerView.Adapter<CustomCapViceCapSelectionAdapter.CustomViewHolder> {
    private JSONArray customerList;
    private Context context;
    ArrayList<String>pname,pimage;

    public CustomCapViceCapSelectionAdapter(Context context, ArrayList<String> pname,ArrayList<String>pimage) {
        /*this.customerList = customerList;*/

        this.context = context;
        this.pname=pname;
        this.pimage=pimage;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_cap_vice_cap_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position)
    {
        Picasso.get().load(pimage.get(position)).into(holder.civDP);
        holder.tvUsername.setText(pname.get(position));
    }


    @Override
    public int getItemCount() {
        return pname.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        private CircularImageView civDP;
        private TextView tvUsername;
        private TextView tvMsg;
        private TextView tvDateTime;

        public CustomViewHolder (View view)
        {
            super(view);
            civDP=view.findViewById(R.id.iv_player_photo);
            tvUsername=view.findViewById(R.id.tv_player_name);

        }

    }
}
