package com.feicui.edu.housekeeper.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.feicui.edu.housekeeper.fragment.ViewPagerFragment;

/**
 * Created by asus on 2016/9/18.
 */
public class BasePagerAdapter extends FragmentStatePagerAdapter {

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
        return fragment;
    }

    @Override
    public int getCount() {
        return count;
    }



    /*protected Context context;
    private ArrayList<View> viewList = new ArrayList<View>();
    private ArrayList<String> tabtitleList = new ArrayList<String>();

    public BasePagerAdapter(Context context){
        this.context = context;
    }

    public ArrayList<View> getViewList(){
        return viewList;
    }

    public void addViewToAdapter(View view){
        viewList.add(view);
    }

    public void addViewToAdapter(String title){
        tabtitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitleList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = viewList.get(position);
        container.removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }*/
}
