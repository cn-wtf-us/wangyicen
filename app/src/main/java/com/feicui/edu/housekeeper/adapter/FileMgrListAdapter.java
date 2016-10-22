package com.feicui.edu.housekeeper.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.adapter.MyBaseAdapter;
import com.feicui.edu.housekeeper.base.utils.CommonUtil;
import com.feicui.edu.housekeeper.base.utils.FileTypeUtil;
import com.feicui.edu.housekeeper.entity.FileInfo;

import java.io.File;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
public class FileMgrListAdapter extends MyBaseAdapter<FileInfo> implements CompoundButton.OnCheckedChangeListener {

    public FileMgrListAdapter(Context context) {
        super(context);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //当被点击了就设置属性为true
        int position = (int) buttonView.getTag();
        FileInfo fileInfo = infos.get(position);
        fileInfo.setChecked(isChecked);
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
            convertView = layoutInflater.inflate(R.layout.view_file_mgr_list_item, null);
            //找到要使用的控件
            vh.checkBox = (CheckBox) convertView.findViewById(R.id.file_mgr_list_item_cb);
            vh.imageView = (ImageView) convertView.findViewById(R.id.file_mgr_list_item_iv);
            vh.textView1 = (TextView) convertView.findViewById(R.id.file_mgr_list_item_tv1);
            vh.textView2 = (TextView) convertView.findViewById(R.id.file_mgr_list_item_tv2);
            vh.textView3 = (TextView) convertView.findViewById(R.id.file_mgr_list_item_tv3);
            //setTag可以存放任意对象
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }

        //添加数据
        FileInfo fileInfo = infos.get(position);
        //给所有多选框设置监听
        vh.checkBox.setOnCheckedChangeListener(this);
        vh.checkBox.setTag(position);
        //设置多选框的当前状态
        vh.checkBox.setChecked(fileInfo.isChecked());

        String icon = fileInfo.getIcon();

        File file = fileInfo.getFile();
//        String mimeType = FileTypeUtil.getMimeTypeFromFileName(file);

        int pic = context.getResources().getIdentifier(icon, "drawable", context.getPackageName());
        if (pic != 0){
            vh.imageView.setImageResource(pic);
        }else{
            vh.imageView.setImageResource(R.mipmap.ic_launcher);
        }

        vh.textView1.setText(file.getName());

        long lastModify = file.lastModified();
        String time = CommonUtil.parseDateToString(lastModify);
        vh.textView2.setText(time);

        vh.textView3.setText(Formatter.formatFileSize(context, file.length()));

        return convertView;
    }
}
