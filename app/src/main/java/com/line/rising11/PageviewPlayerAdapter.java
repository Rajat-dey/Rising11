package com.line.rising11;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageviewPlayerAdapter extends FragmentPagerAdapter
{

    public PageviewPlayerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {
        Fragment fragment= null;
        switch (i)
        {
            case 0:
                fragment= new WicketFragment();
                break;
            case 1:
                fragment=new BatingFragment();
                break;
            case 2:
                fragment=new AllRounderFragment();
                break;
            case 3:
                fragment=new BowlerFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
