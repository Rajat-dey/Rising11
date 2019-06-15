package com.line.rising11.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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
                .inflate(R.layout.custom_match_card_new, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position)
    {

        Date date = null;
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat1 = new SimpleDateFormat("HH:mm:ss");
            date = inputFormat.parse(customerList.getJSONObject(position).get("dateTimeGMT").toString());
            String formattedDate = outputFormat.format(date);
            String formattedDate1 = outputFormat1.format(date);
            Calendar cal = Calendar.getInstance();
            String currentdate=outputFormat.format(cal.getTime());
            String currenttime=outputFormat1.format(cal.getTime());
            Date time1=outputFormat1.parse(formattedDate1);
            Date time2=outputFormat1.parse(currenttime);
            long diffInMillies = outputFormat.parse(formattedDate).getTime() - outputFormat.parse(currentdate).getTime();
            holder.tday = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            holder.ttime = (time1.getTime() - time2.getTime())+330*60*1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }
         catch (JSONException e) {
            e.printStackTrace();
        }

        try
        {
            if(holder.countDownTimer !=null)
            {
                holder.countDownTimer.cancel();
            }
            holder.countDownTimer=new CountDownTimer((holder.tday*24*60*60*1000)+holder.ttime,1000)
            {
                @Override
                public void onTick(long millisUntilFinished)
                {


                    long totalSecs = millisUntilFinished/1000;
                    long hours = (totalSecs / 3600);
                    long mins = (totalSecs / 60) % 60;
                    long secs = totalSecs % 60;

                    /*int second = (int) (millisUntilFinished /1000) %60;

                    Log.d("tsec",String.valueOf(millisUntilFinished));
                    String timeformate = String.format(Locale.getDefault(),  "%02d",second);*/
                    holder.tvDateTime.setTextColor(Color.RED);
                    if(holder.tday>0)
                    {
                        holder.tvDateTime.setText(String.valueOf(holder.tday)+" days left");
                    }
                    else
                    {
                        //holder.tvDateTime.setText(timeformate+"s left");
                        if(holder.ttime>0)
                        {
                            String minsString = (mins == 0) ? "00" : ((mins < 10) ? "0" + mins : "" + mins);
                            String secsString = (secs == 0) ? "00" : ((secs < 10) ? "0" + secs : "" + secs);
                            if (hours > 0) {
                                holder.tvDateTime.setText(hours + "h " + minsString + "m left");
                            }
                            else if (mins > 0) {
                                holder.tvDateTime.setText(mins + "m " + secsString+"s left");

                            }
                            else
                            {
                                holder.tvDateTime.setText(secsString+"s left");
                            }

                        }
                        else
                        {
                            holder.tvDateTime.setText("0s left");
                        }
                    }




                }

                @Override
                public void onFinish()
                {
                    holder.tvDateTime.setText("0"+"s left");

                    holder.tvDateTime.setTextColor(Color.RED);
                    /*holder.cvTeam.setClickable(false);
                    holder.cvTeam.setEnabled(false);
                    holder.cvTeam.setAlpha(0.6f);*/

                }
            }.start();
            holder.name1.setText(customerList.getJSONObject(position).get("team-1").toString());
            holder.name2.setText(customerList.getJSONObject(position).get("team-2").toString());

            holder.cvTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                    try {
                        Intent intent = new Intent(context, ContestsActivity.class);
                        intent.putExtra("uid",customerList.getJSONObject(position).get("unique_id").toString());
                        context.startActivity(intent);
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
            });
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
        private TextView tvipl;
        private CardView cvTeam;
        private TextView name1,name2;
        CountDownTimer countDownTimer;
        Long tday,ttime;

        public CustomViewHolder (View view) {
            super(view);

            cvTeam = view.findViewById(R.id.cv_team);
            name1=view.findViewById(R.id.tv_team1_name);
            name2=view.findViewById(R.id.tv_team2_name);
            tvDateTime=view.findViewById(R.id.tv_vs);


        }

    }
}
