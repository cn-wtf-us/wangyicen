package com.feicui.edu.housekeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.entity.TelnumberInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class TellistAdapter extends BaseAdapter {

    //当前适配器内的数据集合
    private ArrayList<TelnumberInfo> adapterDatas = new ArrayList<TelnumberInfo>();

    private LayoutInflater layoutInflater;

    //添加数据到当前适配器集合
    public void addDataToAdapter(TelnumberInfo telnumberInfo){
        if (telnumberInfo != null){
            adapterDatas.add(telnumberInfo);
        }
    }
    //添加数据到当前适配器集合
    public void addDataToAdapter(List<TelnumberInfo> list){
        if (list != null){
            adapterDatas.addAll(list);
        }
    }
    //添加数据到当前适配器集合中
    public void replaceDataToAdapter(List<TelnumberInfo> list){
        if (list != null) {
            adapterDatas.clear();
            adapterDatas.addAll(list);
        }
    }

    public ArrayList<TelnumberInfo> getDataFromAdapter(){
        return adapterDatas;
    }

    public TellistAdapter(Context context){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return adapterDatas.size();
    }

    @Override
    public TelnumberInfo getItem(int position) {
        return adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.inflate_tellist_listitem, null);
        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_number = (TextView) convertView.findViewById(R.id.tv_number);

        tv_name.setText(getItem(position).name);
        tv_number.setText(getItem(position).number + "");

        return convertView;
    }
}
