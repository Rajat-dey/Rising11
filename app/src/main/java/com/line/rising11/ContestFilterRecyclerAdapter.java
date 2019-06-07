package com.line.rising11;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ContestFilterRecyclerAdapter extends RecyclerView.Adapter<ContestFilterRecyclerAdapter.ViewHolder>{
    private ContestFilterRecyclerDataClass[] listdata;


    public ContestFilterRecyclerAdapter(ContestFilterRecyclerDataClass[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.contestfilterrecycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ContestFilterRecyclerDataClass myListData = listdata[position];

        holder.contesttv1.setText(listdata[position].getPrize());
        holder.contestbt1.setText(listdata[position].getEntry());
        holder.contesttv2.setText(listdata[position].getMin_pb_val());
        holder.contesttv3.setText(listdata[position].getMax_pb_val());
        holder.contesttv4.setText(listdata[position].getWinner());
        holder.contesttv5.setText(listdata[position].getBox1());
        holder.contesttv6.setText(listdata[position].getBox2());

       /* holder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getTv1(),Toast.LENGTH_LONG).show();
            }
        });*/
    }




    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView contesttv1,contesttv2,contesttv3,contesttv4,contesttv5,contesttv6;
        public Button contestbt1;
        public ProgressBar contestpb1;
        public ViewHolder(View itemView) {
            super(itemView);

            this.contesttv1 = (TextView) itemView.findViewById(R.id.contesttv1);
            this.contesttv2 = (TextView) itemView.findViewById(R.id.contesttv2);
            this.contesttv3 = (TextView) itemView.findViewById(R.id.contesttv3);
            this.contesttv4 = (TextView) itemView.findViewById(R.id.contesttv4);
            this.contesttv5 = (TextView) itemView.findViewById(R.id.contesttv5);
            this.contesttv6 = (TextView) itemView.findViewById(R.id.contesttv6);
            this.contestbt1 = (Button) itemView.findViewById(R.id.contestbt1);
            this.contestpb1 = (ProgressBar) itemView.findViewById(R.id.contestpb1);

        }
    }
}