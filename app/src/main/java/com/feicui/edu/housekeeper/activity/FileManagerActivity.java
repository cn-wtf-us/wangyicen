package com.feicui.edu.housekeeper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.view.ActionBarView;

public class FileManagerActivity extends BaseActivity {

    private ActionBarView bar;
    private TextView file_mgr_allsize;

    private TextView file_mgr_doc_size;
    private TextView file_mgr_vd_size;
    private TextView file_mgr_av_size;
    private TextView file_mgr_apk_size;
    private TextView file_mgr_rar_size;
    private TextView file_mgr_all_size;
    private TextView file_mgr_pic_size;

    private ImageView file_mgr_pic_icon;
    private ImageView file_mgr_doc_icon;
    private ImageView file_mgr_vd_icon;
    private ImageView file_mgr_av_icon;
    private ImageView file_mgr_apk_icon;
    private ImageView file_mgr_rar_icon;

    private ProgressBar file_mgr_pic_pb;
    private ProgressBar file_mgr_doc_pb;
    private ProgressBar file_mgr_vd_pb;
    private ProgressBar file_mgr_av_pb;
    private ProgressBar file_mgr_apk_pb;
    private ProgressBar file_mgr_rar_pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);
        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileManagerActivity.this.finish();
            }
        };
        bar.initActionBar("软件管理", R.id.iv_left, ActionBarView.ID_BAR, on);

        new Thread(){
            @Override
            public void run() {
                super.run();
//                getData();
            }
        };
    }

    @Override
    protected void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        file_mgr_allsize = (TextView) findViewById(R.id.file_mgr_allsize);

        file_mgr_all_size = (TextView) findViewById(R.id.file_mgr_all_size);
        file_mgr_doc_size = (TextView) findViewById(R.id.file_mgr_doc_size);
        file_mgr_av_size = (TextView) findViewById(R.id.file_mgr_av_size);
        file_mgr_vd_size = (TextView) findViewById(R.id.file_mgr_vd_size);
        file_mgr_apk_size = (TextView) findViewById(R.id.file_mgr_apk_size);
        file_mgr_rar_size = (TextView) findViewById(R.id.file_mgr_rar_size);
        file_mgr_pic_size = (TextView) findViewById(R.id.file_mgr_pic_size);

        file_mgr_doc_icon = (ImageView) findViewById(R.id.file_mgr_doc_icon);
        file_mgr_av_icon = (ImageView) findViewById(R.id.file_mgr_av_icon);
        file_mgr_vd_icon = (ImageView) findViewById(R.id.file_mgr_vd_icon);
        file_mgr_apk_icon = (ImageView) findViewById(R.id.file_mgr_apk_icon);
        file_mgr_rar_icon = (ImageView) findViewById(R.id.file_mgr_rar_icon);
        file_mgr_pic_icon = (ImageView) findViewById(R.id.file_mgr_pic_icon);

        file_mgr_pic_pb = (ProgressBar) findViewById(R.id.file_mgr_pic_pb);
        file_mgr_doc_pb = (ProgressBar) findViewById(R.id.file_mgr_doc_pb);
        file_mgr_av_pb = (ProgressBar) findViewById(R.id.file_mgr_av_pb);
        file_mgr_vd_pb = (ProgressBar) findViewById(R.id.file_mgr_vd_pb);
        file_mgr_apk_pb = (ProgressBar) findViewById(R.id.file_mgr_apk_pb);
        file_mgr_rar_pb = (ProgressBar) findViewById(R.id.file_mgr_rar_pb);


    }

    @Override
    protected void setListener() {

    }
}
