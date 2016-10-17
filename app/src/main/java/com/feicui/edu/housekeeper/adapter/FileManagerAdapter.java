package com.feicui.edu.housekeeper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.adapter.MyBaseAdapter;
import com.feicui.edu.housekeeper.entity.DeviceInfo;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
public class FileManagerAdapter extends MyBaseAdapter<DeviceInfo>  {

    public FileManagerAdapter(Context context) {
        super(context);
    }

    class ViewHolder{
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
            convertView = layoutInflater.inflate(R.layout.phone_check_list_item, null);
            //找到要使用的控件
            vh.imageView = (ImageView) convertView.findViewById(R.id.phone_check_list_item_iv);
            vh.textView1 = (TextView) convertView.findViewById(R.id.phone_check_list_item_tv1);
            vh.textView2 = (TextView) convertView.findViewById(R.id.phone_check_list_item_tv2);
            //setTag可以存放任意对象
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }
}
