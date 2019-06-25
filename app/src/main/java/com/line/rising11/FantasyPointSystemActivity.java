package com.line.rising11;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FantasyPointSystemActivity extends AppCompatActivity {
    private TextView batting4,batting5,bowling1,bowling2,bowling3,bowling4,erate1,erate2,erate3,erate4,erate5,erate6,srate1,srate2,srate3;
    private TabLayout tab2;
    int bowlingflag=1,fieldingflag=1,othersflag=1,erateflag=1,srateflag=1;
    private LinearLayout bowlingll,bowlingclickll,fieldingll,fieldingclickll,otherll,otherclickll,eratell,erateclickll,sratell,srateclickll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy_point_system);
        setTitle("FANTASY POINT SYSTEM");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        batting4=findViewById(R.id.batting4);
        batting5=findViewById(R.id.batting5);
        bowling1=findViewById(R.id.bowling1);
        bowling2=findViewById(R.id.bowling2);
        bowling3=findViewById(R.id.bowling3);
        bowling4=findViewById(R.id.bowling4);
        erate1=findViewById(R.id.erate1);
        erate2=findViewById(R.id.erate2);
        erate3=findViewById(R.id.erate3);
        erate4=findViewById(R.id.erate4);
        erate5=findViewById(R.id.erate5);
        erate6=findViewById(R.id.erate6);
        srate1=findViewById(R.id.srate1);
        srate2=findViewById(R.id.srate2);
        srate3=findViewById(R.id.srate3);

        tab2=findViewById(R.id.tab2);

        bowlingll=findViewById(R.id.bowlingll);
        bowlingclickll=findViewById(R.id.bowlingclickll);
        fieldingll=findViewById(R.id.fieldingll);
        fieldingclickll=findViewById(R.id.fieldingclickll);
        otherll=findViewById(R.id.otherll);
        otherclickll=findViewById(R.id.otherclickll);
        eratell=findViewById(R.id.eratell);
        erateclickll=findViewById(R.id.erateclickll);
        sratell=findViewById(R.id.sratell);
        srateclickll=findViewById(R.id.srateclickll);

        if(bowlingflag==1)
        {
            bowlingll.setVisibility(View.GONE);
        }
        if(fieldingflag==1)
        {
            fieldingll.setVisibility(View.GONE);
        }
        if(othersflag==1)
        {
            otherll.setVisibility(View.GONE);
        }
        if(erateflag==1)
        {
            eratell.setVisibility(View.GONE);
        }
        if(srateflag==1)
        {
            sratell.setVisibility(View.GONE);
        }

        bowlingclickll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bowlingflag==1)
                {
                    bowlingll.setVisibility(View.VISIBLE);
                    bowlingflag=0;
                }
                else
                {
                    bowlingll.setVisibility(View.GONE);
                    bowlingflag=1;
                }

            }
        });
        fieldingclickll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fieldingflag==1)
                {
                    fieldingll.setVisibility(View.VISIBLE);
                    fieldingflag=0;
                }
                else
                {
                    fieldingll.setVisibility(View.GONE);
                    fieldingflag=1;
                }
            }
        });
        otherclickll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(othersflag==1)
                {
                    otherll.setVisibility(View.VISIBLE);
                    othersflag=0;
                }
                else
                {
                    otherll.setVisibility(View.GONE);
                    othersflag=1;
                }
            }
        });
        erateclickll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(erateflag==1)
                {
                    eratell.setVisibility(View.VISIBLE);
                    erateflag=0;
                }
                else
                {
                    eratell.setVisibility(View.GONE);
                    erateflag=1;
                }
            }
        });
        srateclickll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(srateflag==1)
                {
                    sratell.setVisibility(View.VISIBLE);
                    srateflag=0;
                }
                else
                {
                    sratell.setVisibility(View.GONE);
                    srateflag=1;

                }
            }
        });

        tab2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0)
                {
                   batting4.setText("+4");
                   batting5.setText("+8");
                   bowling1.setText("+10");
                   bowling2.setText("+4");
                   bowling3.setText("+8");
                   bowling4.setText("+4");
                   erate1.setText("+3");
                   erate2.setText("+2");
                   erate3.setText("+1");
                   erate4.setText("-1");
                   erate5.setText("-2");
                   erate6.setText("-3");
                   srate1.setText("-1");
                   srate2.setText("-2");
                   srate3.setText("-3");
                    srate1.setTextColor(Color.parseColor("#0546e3"));
                    srate2.setTextColor(Color.parseColor("#0546e3"));
                    srate3.setTextColor(Color.parseColor("#0546e3"));
                    erate4.setTextColor(Color.parseColor("#0546e3"));
                    erate5.setTextColor(Color.parseColor("#0546e3"));
                    erate6.setTextColor(Color.parseColor("#0546e3"));

                }
                else if(tab.getPosition()==1)
                {
                    batting4.setText("+2");
                    batting5.setText("+4");
                    bowling1.setText("+12");
                    bowling2.setText("+2");
                    bowling3.setText("+4");
                    bowling4.setText("+2");
                    erate1.setText("+3");
                    erate2.setText("+2");
                    erate3.setText("+1");
                    erate4.setText("-2");
                    erate5.setText("-3");
                    erate6.setText("-4");
                    srate1.setText("-1");
                    srate2.setText("-2");
                    srate3.setText("-3");
                    srate1.setTextColor(Color.parseColor("#0546e3"));
                    srate2.setTextColor(Color.parseColor("#0546e3"));
                    srate3.setTextColor(Color.parseColor("#0546e3"));
                    erate4.setTextColor(Color.parseColor("#0546e3"));
                    erate5.setTextColor(Color.parseColor("#0546e3"));
                    erate6.setTextColor(Color.parseColor("#0546e3"));
                }
                else if(tab.getPosition()==2)
                {
                    batting4.setText("+2");
                    batting5.setText("+4");
                    bowling1.setText("+8");
                    bowling2.setText("+2");
                    bowling3.setText("+4");
                    bowling4.setText("0");
                    erate1.setText("0");
                    erate2.setText("0");
                    erate3.setText("0");
                    erate4.setText("0");
                    erate5.setText("0");
                    erate6.setText("0");
                    srate1.setText("0");
                    srate2.setText("0");
                    srate3.setText("0");
                    srate1.setTextColor(Color.parseColor("#ba254f"));
                    srate2.setTextColor(Color.parseColor("#ba254f"));
                    srate3.setTextColor(Color.parseColor("#ba254f"));
                    erate4.setTextColor(Color.parseColor("#ba254f"));
                    erate5.setTextColor(Color.parseColor("#ba254f"));
                    erate6.setTextColor(Color.parseColor("#ba254f"));
                }
                else if(tab.getPosition()==3)
                {
                    batting4.setText("+5");
                    batting5.setText("+10");
                    bowling1.setText("+12");
                    bowling2.setText("+5");
                    bowling3.setText("+10");
                    bowling4.setText("+6");
                    erate1.setText("+4");
                    erate2.setText("+3");
                    erate3.setText("+2");
                    erate4.setText("-2");
                    erate5.setText("-3");
                    erate6.setText("-4");
                    srate1.setText("-1");
                    srate2.setText("-2");
                    srate3.setText("-3");
                    srate1.setTextColor(Color.parseColor("#0546e3"));
                    srate2.setTextColor(Color.parseColor("#0546e3"));
                    srate3.setTextColor(Color.parseColor("#0546e3"));
                    erate4.setTextColor(Color.parseColor("#0546e3"));
                    erate5.setTextColor(Color.parseColor("#0546e3"));
                    erate6.setTextColor(Color.parseColor("#0546e3"));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
