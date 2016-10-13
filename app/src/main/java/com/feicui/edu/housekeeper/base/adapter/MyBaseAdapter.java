package com.feicui.edu.housekeeper.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
/**
 * Created by Administrator on 2016/9/18 0018.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    protected ArrayList<T> infos;
    protected Context context;
    protected LayoutInflater layoutInflater;

    public MyBaseAdapter(Context context){
        infos = new ArrayList<T>();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getMyView(position, convertView, parent);
    }

    public abstract View getMyView(int position, View convertView, ViewGroup parent);


    public ArrayList<T> getDatas() {
        return infos;
    }



    //添加数据到当前适配器集合
    public void addDatas(ArrayList<T> infos){
        this.infos.clear();
        if (infos != null){
            this.infos.addAll(infos);
        }
    }


}
