package com.feicui.edu.housekeeper.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.fragment.ViewPagerFragment;


public class BasePagerAdapter extends FragmentStatePagerAdapter {
    private int[] pics = {R.drawable.adware_style_applist,
            R.drawable.adware_style_banner, R.drawable.adware_style_creditswall};
    private int count;
    public void setCount(int count) {
        this.count = count;
    }

    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }
        @Override
        public Fragment getItem(int position) {
            ViewPagerFragment fragment = new ViewPagerFragment();
            fragment.initData(pics[position]);
            return fragment;
        }
        @Override
        public int getCount() {
            return count;
        }

}
