package com.feicui.edu.housekeeper.base.adapter;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


import java.util.List;


/**
 * Created by asus on 2016/9/18.
 */
public class BasePagerAdapter extends PagerAdapter {
    private List<View> views;

    public BasePagerAdapter(List<View> views){
        this.views = views;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public void finishUpdate(View container) {
        super.finishUpdate(container);
    }

    @Override
    public int getCount() {
        if (views != null){
            return views.size();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(views.get(position), 0);
        return views.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View container) {

    }
    /*private int count;
    int[] colors;
    Random random = new Random();
    public void setCount(int count) {
        this.count = count;
        colors = new int[count];
        for (int i = 0; i < count; i++) {
            colors[i] = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }
    }

    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.initData(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return count;
    }
*/


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
