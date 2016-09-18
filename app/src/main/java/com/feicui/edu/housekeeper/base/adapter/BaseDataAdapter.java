package com.feicui.edu.housekeeper.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public abstract class BaseDataAdapter<E> extends BaseAdapter {
    private ArrayList<E> adapterDatas = new ArrayList<E>();
    protected Context context;
    protected LayoutInflater layoutInflater;

    public BaseDataAdapter(Context context){
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return adapterDatas.size();
    }

    @Override
    public E getItem(int position) {
        return adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public ArrayList<E> getDataFromAdapter() {
        return adapterDatas;
    }

    public void setDataFromAdapter(ArrayList<E> adapterDatas) {
        adapterDatas.clear();
        if (adapterDatas != null){
            adapterDatas.addAll(adapterDatas);
        }
    }

    //添加数据到当前适配器集合
    public void addDataToAdapter(E e){
        if (e != null){
            adapterDatas.add(e);
        }
    }

    //添加数据到当前适配器集合
    public void addDataToAdapter(List<E> e){
        if (e != null){
            adapterDatas.addAll(e);
        }
    }

    public void clearAdapter(){
        adapterDatas.clear();
    }
    //删除当前适配器集合中的数据
    public void removeDataFromAdapter(E e){
        adapterDatas.remove(e);
    }
    //删除当前适配器集合中的数据
    public void removeDataFromAdapter(int index){
        adapterDatas.remove(index);
    }
}
