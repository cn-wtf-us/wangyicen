package com.feicui.edu.housekeeper.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.adapter.MyBaseAdapter;
import com.feicui.edu.housekeeper.base.utils.BitmapCache;
import com.feicui.edu.housekeeper.entity.AppInfo;
import com.feicui.edu.housekeeper.entity.DeviceInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
public class PhoneCheckAdapter extends MyBaseAdapter<DeviceInfo>  {

    public PhoneCheckAdapter(Context context) {
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
        DeviceInfo deviceInfo = infos.get(position);
        vh.imageView.setImageResource(deviceInfo.getPic());
        vh.textView1.setText(deviceInfo.getInfo1());
        vh.textView2.setText(deviceInfo.getInfo2());

        return convertView;
    }
}
