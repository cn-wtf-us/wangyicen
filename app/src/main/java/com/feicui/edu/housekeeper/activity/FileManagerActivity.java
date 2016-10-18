package com.feicui.edu.housekeeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.base.utils.FileTypeUtil;
import com.feicui.edu.housekeeper.biz.FileManager;
import com.feicui.edu.housekeeper.view.ActionBarView;

public class FileManagerActivity extends BaseActivity implements FileManager.OnDataSendListener {

    private ActionBarView bar;
    private FileManager fileManager;
    private TextView file_mgr_allsize;

    private TextView file_mgr_doc_size;
    private TextView file_mgr_vd_size;
    private TextView file_mgr_ad_size;
    private TextView file_mgr_apk_size;
    private TextView file_mgr_rar_size;
    private TextView file_mgr_all_size;
    private TextView file_mgr_pic_size;

    private ImageView file_mgr_all_icon;
    private ImageView file_mgr_pic_icon;
    private ImageView file_mgr_doc_icon;
    private ImageView file_mgr_vd_icon;
    private ImageView file_mgr_ad_icon;
    private ImageView file_mgr_apk_icon;
    private ImageView file_mgr_rar_icon;

    private ProgressBar file_mgr_all_pb;
    private ProgressBar file_mgr_pic_pb;
    private ProgressBar file_mgr_doc_pb;
    private ProgressBar file_mgr_vd_pb;
    private ProgressBar file_mgr_ad_pb;
    private ProgressBar file_mgr_apk_pb;
    private ProgressBar file_mgr_rar_pb;

    private static final int MESSAGE_UPDATE_TEXT = 0;
    private static final int MESSAGE_UPDATE_PROGRESSBAR = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int number = msg.what;
            switch (number){
                case MESSAGE_UPDATE_TEXT:
                    String type = (String) msg.obj;
                    updateText(type);
                    break;
                case MESSAGE_UPDATE_PROGRESSBAR:
                    updateProgressBar();
                    break;
            }
        }
    };

    public void navigationToList(View view){
        Intent intent = new Intent(this, FileMgrListActivity.class);
        switch (view.getId()){
            case R.id.file_mgr_all:
                intent.putExtra("title", "全部");
                break;
            case R.id.file_mgr_doc:
                intent.putExtra("title", "文件");
                break;
            case R.id.file_mgr_pic:
                intent.putExtra("title", "图片");
                break;
            case R.id.file_mgr_apk:
                intent.putExtra("title", "安装包");
                break;
            case R.id.file_mgr_av:
                intent.putExtra("title", "音频");
                break;
            case R.id.file_mgr_vd:
                intent.putExtra("title", "视频");
                break;
            case R.id.file_mgr_rar:
                intent.putExtra("title", "压缩包");
                break;
        }
        //获取从子界面中携带回来的数据
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //子界面销毁了，更新主界面上的数据
        file_mgr_apk_size.setText(Formatter.formatFileSize(this, FileManager.getInstance().getApkSize()));
        file_mgr_ad_size.setText(Formatter.formatFileSize(this, FileManager.getInstance().getAdSize()));
        file_mgr_pic_size.setText(Formatter.formatFileSize(this, FileManager.getInstance().getPicSize()));
        file_mgr_doc_size.setText(Formatter.formatFileSize(this, FileManager.getInstance().getDocSize()));
        file_mgr_vd_size.setText(Formatter.formatFileSize(this, FileManager.getInstance().getVdSize()));
        file_mgr_rar_size.setText(Formatter.formatFileSize(this, FileManager.getInstance().getRarSize()));
        file_mgr_allsize.setText(Formatter.formatFileSize(this, FileManager.getInstance().getAllSize()));
        file_mgr_all_size.setText(Formatter.formatFileSize(this, FileManager.getInstance().getAllSize()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_file_manager);
        super.onCreate(savedInstanceState);

        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileManagerActivity.this.finish();
            }
        };
        bar.initActionBar("软件管理", R.id.iv_left, ActionBarView.ID_BAR, on);

        //每次进入主界面都先清理缓存,将所有属性初始化
        FileManager.getInstance().clearCache();
        searchFile();

    }

    private void searchFile() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                //第二步，查询文件个数
                fileManager.searchFile(FileManager.sdPath, true);
            }
        }.start();
    }

    @Override
    protected void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        file_mgr_allsize = (TextView) findViewById(R.id.file_mgr_allsize);

        file_mgr_all_size = (TextView) findViewById(R.id.file_mgr_all_size);
        file_mgr_doc_size = (TextView) findViewById(R.id.file_mgr_doc_size);
        file_mgr_ad_size = (TextView) findViewById(R.id.file_mgr_av_size);
        file_mgr_vd_size = (TextView) findViewById(R.id.file_mgr_vd_size);
        file_mgr_apk_size = (TextView) findViewById(R.id.file_mgr_apk_size);
        file_mgr_rar_size = (TextView) findViewById(R.id.file_mgr_rar_size);
        file_mgr_pic_size = (TextView) findViewById(R.id.file_mgr_pic_size);

        file_mgr_all_icon = (ImageView) findViewById(R.id.file_mgr_all_icon);
        file_mgr_doc_icon = (ImageView) findViewById(R.id.file_mgr_doc_icon);
        file_mgr_ad_icon = (ImageView) findViewById(R.id.file_mgr_av_icon);
        file_mgr_vd_icon = (ImageView) findViewById(R.id.file_mgr_vd_icon);
        file_mgr_apk_icon = (ImageView) findViewById(R.id.file_mgr_apk_icon);
        file_mgr_rar_icon = (ImageView) findViewById(R.id.file_mgr_rar_icon);
        file_mgr_pic_icon = (ImageView) findViewById(R.id.file_mgr_pic_icon);

        file_mgr_all_pb = (ProgressBar) findViewById(R.id.file_mgr_all_pb);
        file_mgr_pic_pb = (ProgressBar) findViewById(R.id.file_mgr_pic_pb);
        file_mgr_doc_pb = (ProgressBar) findViewById(R.id.file_mgr_doc_pb);
        file_mgr_ad_pb = (ProgressBar) findViewById(R.id.file_mgr_av_pb);
        file_mgr_vd_pb = (ProgressBar) findViewById(R.id.file_mgr_vd_pb);
        file_mgr_apk_pb = (ProgressBar) findViewById(R.id.file_mgr_apk_pb);
        file_mgr_rar_pb = (ProgressBar) findViewById(R.id.file_mgr_rar_pb);

    }

    @Override
    protected void setListener() {
        fileManager = FileManager.getInstance();
        //第一步，设置监听
        fileManager.setOnDataSendListener(this);

    }

    //回调函数
    @Override
    public void getData(String type) {
        Message msg = handler.obtainMessage();
        msg.what = MESSAGE_UPDATE_TEXT;
        msg.obj = type;
        handler.sendMessage(msg);
    }

    @Override
    public void searchEnd() {
        handler.sendEmptyMessage(MESSAGE_UPDATE_PROGRESSBAR);
        Log.i("FileManagerActivity", "搜索完成！");

    }

    public void updateText(String type) {

        long apkTextSize = FileManager.getInstance().getApkSize();
        long adTextSize = FileManager.getInstance().getAdSize();
        long picTextSize = FileManager.getInstance().getPicSize();
        long docTextSize = FileManager.getInstance().getDocSize();
        long vdTextSize = FileManager.getInstance().getVdSize();
        long rarTextSize = FileManager.getInstance().getRarSize();
        long allTextSize = FileManager.getInstance().getAllSize();

        if (type.equals(FileTypeUtil.TYPE_APK)){
            file_mgr_apk_size.setText(Formatter.formatFileSize(this, apkTextSize));

        }else if (type.equals(FileTypeUtil.TYPE_AUDIO)){
            file_mgr_ad_size.setText(Formatter.formatFileSize(this, adTextSize));

        }else if (type.equals(FileTypeUtil.TYPE_IMAGE)){
            file_mgr_pic_size.setText(Formatter.formatFileSize(this, picTextSize));

        }else if (type.equals(FileTypeUtil.TYPE_TXT)){
            file_mgr_doc_size.setText(Formatter.formatFileSize(this, docTextSize));

        }else if (type.equals(FileTypeUtil.TYPE_VIDEO)){
            file_mgr_vd_size.setText(Formatter.formatFileSize(this, vdTextSize));

        }else if (type.equals(FileTypeUtil.TYPE_ZIP)){
            file_mgr_rar_size.setText(Formatter.formatFileSize(this, rarTextSize));

        }else {
            file_mgr_allsize.setText(Formatter.formatFileSize(this, allTextSize));
            file_mgr_all_size.setText(Formatter.formatFileSize(this, allTextSize));
        }

    }

    public void updateProgressBar() {
        //进度条消失
        file_mgr_all_pb.setVisibility(View.GONE);
        file_mgr_apk_pb.setVisibility(View.GONE);
        file_mgr_ad_pb.setVisibility(View.GONE);
        file_mgr_vd_pb.setVisibility(View.GONE);
        file_mgr_doc_pb.setVisibility(View.GONE);
        file_mgr_rar_pb.setVisibility(View.GONE);
        file_mgr_pic_pb.setVisibility(View.GONE);

        //图片显示
        file_mgr_all_icon.setVisibility(View.VISIBLE);
        file_mgr_apk_icon.setVisibility(View.VISIBLE);
        file_mgr_ad_icon.setVisibility(View.VISIBLE);
        file_mgr_vd_icon.setVisibility(View.VISIBLE);
        file_mgr_doc_icon.setVisibility(View.VISIBLE);
        file_mgr_rar_icon.setVisibility(View.VISIBLE);
        file_mgr_pic_icon.setVisibility(View.VISIBLE);
    }
}
