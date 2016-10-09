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
import com.feicui.edu.housekeeper.entity.RunningApp;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class RocketListAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
    private ArrayList<RunningApp> runningApps;
    private LayoutInflater inflater;

    public RocketListAdapter(Context context){
        runningApps = new ArrayList<RunningApp>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<RunningApp> getDatas(){
        return runningApps;
    }

    public void addDatas(ArrayList<RunningApp> runningApps){
        this.runningApps.clear();
        this.runningApps.addAll(runningApps);
    }

    @Override
    public int getCount() {
        return runningApps.size();
    }

    @Override
    public Object getItem(int position) {
        return runningApps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null){
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.view_rocket_list_item, null);
            vh.cb = (CheckBox) convertView.findViewById(R.id.rocket_list_item_cb);
            vh.iv = (ImageView) convertView.findViewById(R.id.rocket_list_item_iv);
            vh.tv1 = (TextView) convertView.findViewById(R.id.rocket_list_item_tv1);
            vh.tv2 = (TextView) convertView.findViewById(R.id.rocket_list_item_tv2);
            vh.tv3 = (TextView) convertView.findViewById(R.id.rocket_list_item_tv3);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        RunningApp runningApp = runningApps.get(position);
        vh.cb.setTag(position);
        vh.cb.setChecked(runningApp.isChecked());

        return convertView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int position = (int) buttonView.getTag();
        RunningApp runningApp = runningApps.get(position);
        runningApp.setChecked(isChecked);
    }

    class ViewHolder{
        CheckBox cb;
        ImageView iv;
        TextView tv1, tv2, tv3;
    }
}
