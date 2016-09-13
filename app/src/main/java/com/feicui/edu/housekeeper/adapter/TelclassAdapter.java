package com.feicui.edu.housekeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.entity.TelclassInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class TelclassAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;

    //当前适配器内的数据集合（当前适配器适配工作只认此集合）
    private ArrayList<TelclassInfo> adapterDatas = new ArrayList<TelclassInfo>();
    //添加数据到当前适配器集合
    public void addDataToAdapter(TelclassInfo telclassInfo){
        if (telclassInfo != null){
            adapterDatas.add(telclassInfo);
        }
    }

    //添加数据到当前适配器集合
    public void addDataToAdapter(List<TelclassInfo> list){
        if (list != null){
            adapterDatas.addAll(list);
        }
    }

    public ArrayList<TelclassInfo> getDataFromAdapter(){
        return adapterDatas;
    }

    //删除当前适配器集合内数据
    public void clearDataTOAdapter(){
        adapterDatas.clear();
    }

    public TelclassAdapter(Context context){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return adapterDatas.size();
    }

    @Override
    public TelclassInfo getItem(int position) {
        return adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.inflate_telmsg_listitem, null);
        }
        TextView tv_text = (TextView) convertView.findViewById(R.id.tv_list_item);
//        TelclassInfo item = (TelclassInfo) getItem(position);
//        tv_text.setText(item.name);
        tv_text.setText(getItem(position).name);
        return convertView;
    }
}
