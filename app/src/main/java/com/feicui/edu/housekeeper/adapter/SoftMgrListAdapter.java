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


/**
 * Created by Administrator on 2016/9/30 0030.
 */
public class SoftMgrListAdapter extends MyBaseAdapter<AppInfo> implements CompoundButton.OnCheckedChangeListener {
    private BitmapCache cache;
    private boolean isFlying;

    public SoftMgrListAdapter(Context context) {
        super(context);
        cache = BitmapCache.getInstance();
    }

    public void setFlying(boolean isFlying){
        this.isFlying = isFlying;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //当被点击了就设置属性为true
        int position = (int) buttonView.getTag();
        AppInfo appInfo = infos.get(position);
        appInfo.setChecked(isChecked);
    }

    class ViewHolder{
        CheckBox checkBox;
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null){
            vh = new ViewHolder();
            //加载布局文件
            convertView = layoutInflater.inflate(R.layout.view_soft_mgr_list_item, null);
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
        AppInfo appInfo = infos.get(position);
        PackageInfo packageInfo = appInfo.getInfo();
        //给所有多选框设置监听
        vh.checkBox.setOnCheckedChangeListener(this);
        vh.checkBox.setTag(position);
        //设置多选框的当前状态
        vh.checkBox.setChecked(appInfo.isChecked());
        if (!isFlying){
            //获取图标
            Drawable drawable = packageInfo.applicationInfo.loadIcon(context.getPackageManager());
            //将Drawable类型转换成Bitmap
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            //将Bitmap添加到软引用中防止OOM异常
            cache.addBitmap(position, bitmap);
            //从软引用集合中尝试获取图标，但是不一定获取到
            vh.imageView.setImageBitmap(cache.getBitmap(position, context));
        }else {
            vh.imageView.setImageResource(R.mipmap.ic_launcher);
        }

        String label = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
        vh.textView1.setText(label);
        vh.textView2.setText(packageInfo.packageName);
        vh.textView3.setText(packageInfo.versionName);

        return convertView;
    }
}
