package com.line.rising11.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.line.rising11.R;


public class CustomMyContestAdapter extends RecyclerView.Adapter<CustomMyContestAdapter.CustomViewHolder> {

    private Context context;


    public CustomMyContestAdapter(Context context) {

        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_my_contest, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position)
    {


    }


    @Override
    public int getItemCount() {
        return 2;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{


        public CustomViewHolder (View view) {
            super(view);


        }

    }
}
