package com.line.rising11.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.line.rising11.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONArray;

public class CustomCapViceCapSelectionAdapter extends RecyclerView.Adapter<CustomCapViceCapSelectionAdapter.CustomViewHolder> {
    private JSONArray customerList;
    private Context context;

    public CustomCapViceCapSelectionAdapter(Context context, JSONArray customerList) {
        this.customerList = customerList;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_cap_vice_cap_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        private CircularImageView civDP;
        private TextView tvUsername;
        private TextView tvMsg;
        private TextView tvDateTime;

        public CustomViewHolder (View view) {
            super(view);

        }

    }
}
