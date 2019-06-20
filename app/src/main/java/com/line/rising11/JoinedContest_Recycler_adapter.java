package com.line.rising11;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class JoinedContest_Recycler_adapter extends RecyclerView.Adapter<JoinedContest_Recycler_adapter.ViewHolder>{
    private joinedcontest_recyclerdataclass[] listdata11;
    private OnAddListner2 monAddListner;


    public JoinedContest_Recycler_adapter(joinedcontest_recyclerdataclass[] listdata11,OnAddListner2 onAddListner) {
        this.listdata11 = listdata11;
        this.monAddListner=onAddListner;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.joined_contest_custom_layout, parent, false);

        return new ViewHolder(listItem,monAddListner);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final joinedcontest_recyclerdataclass mylistdata11 = listdata11[position];

        holder.prizepool.setText(listdata11[position].getPrizepool());
        holder.spots.setText(listdata11[position].getSpots());
        holder.entry.setText(listdata11[position].getEntry());
        holder.joined_with.setText(listdata11[position].getJoined_with());
        holder.points.setText(listdata11[position].getPoints());
        holder.rank.setText(listdata11[position].getRank());



       /* holder.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+mylistdata11.getTv1(),Toast.LENGTH_LONG).show();
            }
        });*/
    }




    @Override
    public int getItemCount() {
        return listdata11.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView prizepool,spots,entry,joined_with,points,rank;
        public ProgressBar contestpb1;
        public LinearLayout joinedcontest;
        OnAddListner2 onAddListner;
        public ViewHolder(View itemView,OnAddListner2 onAddListner) {
            super(itemView);
            this.onAddListner=onAddListner;
            this.prizepool = (TextView) itemView.findViewById(R.id.prize_pool);
            this.spots = (TextView) itemView.findViewById(R.id.spots);
            this.entry = (TextView) itemView.findViewById(R.id.entry);
            this.joined_with = (TextView) itemView.findViewById(R.id.joined_with);
            this.points = (TextView) itemView.findViewById(R.id.points);
            this.rank = (TextView) itemView.findViewById(R.id.rank);

            joinedcontest=itemView.findViewById(R.id.joined_contest);

            this.contestpb1 = (ProgressBar) itemView.findViewById(R.id.contestpb1);
            joinedcontest.setOnClickListener(this);


        }

        @Override
        public void onClick(View v)
        {
            onAddListner.onAddClick(getAdapterPosition());
        }
    }

    public interface OnAddListner2
    {
        void onAddClick(int position);
    }
}