package com.feicui.edu.housekeeper.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.utils.BitmapCache;
import com.feicui.edu.housekeeper.entity.AppInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
public class SoftMgrListAdapter extends BaseAdapter {
    private ArrayList<AppInfo> appInfos;
    private Context context;
    private LayoutInflater inflater;
    private BitmapCache cache;
    private boolean isFlying;

    public void setFlying(boolean isFlying){
        this.isFlying = isFlying;
    }

    public SoftMgrListAdapter(Context context, ArrayList<AppInfo> appInfos){
        this.appInfos = appInfos;
        this.context = context;
        //创建布局加载器
        inflater = LayoutInflater.from(context);
        cache = BitmapCache.getInstance();
    }

    @Override
    public int getCount() {
        return appInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return appInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        CheckBox checkBox;
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null){
            vh = new ViewHolder();
            //加载布局文件
            convertView = inflater.inflate(R.layout.view_soft_mgr_list_item, null);
            //找到要使用的控件
            vh.checkBox = (CheckBox) convertView.findViewById(R.id.soft_mgr_list_item_cb);
            vh.imageView = (ImageView) convertView.findViewById(R.id.soft_mgr_list_item_iv);
            vh.textView1 = (TextView) convertView.findViewById(R.id.soft_mgr_list_item_tv1);
            vh.textView2 = (TextView) convertView.findViewById(R.id.soft_mgr_list_item_tv2);
            vh.textView3 = (TextView) convertView.findViewById(R.id.soft_mgr_list_item_tv3);
            //setTag可以存放任意对象
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }

        //添加数据
        AppInfo appInfo = appInfos.get(position);
        PackageInfo packageInfo = appInfo.getInfo();
        vh.checkBox.setChecked(false);
        //获取图标
        Drawable drawable = packageInfo.applicationInfo.loadIcon(context.getPackageManager());
        //将Drawable类型转换成Bitmap
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        //将Bitmap添加到软引用中防止OOM异常
        cache.addBitmap(position, bitmap);

        String label = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
        //从软引用集合中尝试获取图标，但是不一定获取到
        vh.imageView.setImageBitmap(cache.getBitmap(position, context));
        vh.textView1.setText(label);
        vh.textView2.setText(packageInfo.packageName);
        vh.textView3.setText(packageInfo.versionName);

        return convertView;
    }
}
