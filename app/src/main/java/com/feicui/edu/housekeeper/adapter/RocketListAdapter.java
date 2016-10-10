package com.feicui.edu.housekeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.adapter.MyBaseAdapter;
import com.feicui.edu.housekeeper.entity.RunningApp;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class RocketListAdapter extends MyBaseAdapter<RunningApp> implements CompoundButton.OnCheckedChangeListener {

    public RocketListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null){
            vh = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.view_rocket_list_item, null);
            vh.cb = (CheckBox) convertView.findViewById(R.id.rocket_list_item_cb);
            vh.iv = (ImageView) convertView.findViewById(R.id.rocket_list_item_iv);
            vh.tv1 = (TextView) convertView.findViewById(R.id.rocket_list_item_tv1);
            vh.tv2 = (TextView) convertView.findViewById(R.id.rocket_list_item_tv2);
            vh.tv3 = (TextView) convertView.findViewById(R.id.rocket_list_item_tv3);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        RunningApp runningApp = infos.get(position);
        vh.cb.setTag(position);
        vh.cb.setChecked(runningApp.isChecked());

        return convertView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int position = (int) buttonView.getTag();
        RunningApp runningApp = infos.get(position);
        runningApp.setChecked(isChecked);
    }

    class ViewHolder{
        CheckBox cb;
        ImageView iv;
        TextView tv1, tv2, tv3;
    }
}
