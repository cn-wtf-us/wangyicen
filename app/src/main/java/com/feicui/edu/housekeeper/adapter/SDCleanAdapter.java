package com.feicui.edu.housekeeper.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.adapter.MyBaseAdapter;
import com.feicui.edu.housekeeper.entity.SDCleanInfo;

import java.io.File;

/**
 * Created by asus on 2016/10/23.
 */
public class SDCleanAdapter extends MyBaseAdapter<SDCleanInfo> {
    public SDCleanAdapter(Context context) {
        super(context);
    }

    class ViewHolder{
        CheckBox checkBox;
        ImageView imageView;
        TextView textView1;
        TextView textView2;
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null){
            vh = new ViewHolder();
            //加载布局文件
            convertView = layoutInflater.inflate(R.layout.sd_clean_list_item, null);
            //找到要使用的控件
            vh.checkBox = (CheckBox) convertView.findViewById(R.id.sd_clean_list_item_cb);
            vh.imageView = (ImageView) convertView.findViewById(R.id.sd_clean_list_item_iv);
            vh.textView1 = (TextView) convertView.findViewById(R.id.sd_clean_list_item_tv1);
            vh.textView2 = (TextView) convertView.findViewById(R.id.sd_clean_list_item_tv2);
            //setTag可以存放任意对象
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        SDCleanInfo cleanInfo = infos.get(position);
        vh.imageView.setImageResource(cleanInfo.getPic());
        vh.textView1.setText(cleanInfo.getType());
        vh.textView2.setText(cleanInfo.getIcon());
        return convertView;
    }
}
