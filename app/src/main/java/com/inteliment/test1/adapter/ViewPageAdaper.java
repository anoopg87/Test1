package com.inteliment.test1.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.inteliment.test1.fragments.ViewPagerFragment;

// View page adapter which list the fragments in viewpager
public class ViewPageAdaper extends FragmentPagerAdapter {
    public ViewPageAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return ViewPagerFragment.newInstance("Fragment1",1);
            case 1:return ViewPagerFragment.newInstance("Fragment2",2);
            case 2:return ViewPagerFragment.newInstance("Fragment3",3);
            case 3:return ViewPagerFragment.newInstance("Fragment4",4);
            default:return  ViewPagerFragment.newInstance("Fragment Default",0);
        }

    }


    // returning the count of fragments
    @Override
    public int getCount() {
        return 4;
    }
}
