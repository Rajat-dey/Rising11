package com.line.rising11;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/*
public class MyListAdapterActiveOffer extends RecyclerView.Adapter<MyListAdapterActiveOffer.V> {
}
*/
public class MyListAdapterActiveOffer extends RecyclerView.Adapter<MyListAdapterActiveOffer.ViewHolder>{
    private MyActiveOfferDataClass[] listdata;

    // RecyclerView recyclerView;
    public MyListAdapterActiveOffer(MyActiveOfferDataClass[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rewardandofferrecyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyActiveOfferDataClass myListData = listdata[position];
        holder.textView1.setText(listdata[position].getTv1());
        holder.textView2.setText(listdata[position].getTv2());
        holder.textView3.setText(listdata[position].getTv3());
        holder.textView4.setText(listdata[position].getTv4());
        holder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getTv1(),Toast.LENGTH_LONG).show();
            }
        });
    }




    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1,textView2,textView3,textView4;
        public ViewHolder(View itemView) {
            super(itemView);

            this.textView1 = (TextView) itemView.findViewById(R.id.tv1);
            this.textView2 = (TextView) itemView.findViewById(R.id.tv2);
            this.textView3 = (TextView) itemView.findViewById(R.id.tv3);
            this.textView4 = (TextView) itemView.findViewById(R.id.tv4);
        }
    }
}