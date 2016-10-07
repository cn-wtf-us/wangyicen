package com.feicui.edu.housekeeper.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.adapter.SoftMgrListAdapter;
import com.feicui.edu.housekeeper.base.utils.AppInfoManager;
import com.feicui.edu.housekeeper.entity.AppInfo;
import com.feicui.edu.housekeeper.view.ActionBarView;

import java.util.ArrayList;


public class SoftMgrListActivity extends AppCompatActivity {
    private ActionBarView bar;
    private ArrayList<AppInfo> appInfos;
    private ListView lv;
    private SoftMgrListAdapter adapter;
    private ProgressBar progressBar;
    //创建一个Handler的子类对象
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressBar.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            //接收和处理消息
            adapter = new SoftMgrListAdapter(SoftMgrListActivity.this, appInfos);
            lv.setAdapter(adapter);
        }
    };//接收和处理消息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_mgr_list);

        final AppInfoManager appManager = AppInfoManager.getInstance(this);
        final int key = getIntent().getIntExtra("soft", 1);
        //接受传递过来的参数
        new Thread(){
            @Override
            public void run() {
                super.run();
                switch (key){
                    case SoftManagerActivity.ALL:
                        appInfos = appManager.getAllInstalledApp();
                        break;
                    case SoftManagerActivity.SYS:
                        appInfos = appManager.getAllInstalledApp();
                        break;
                    case SoftManagerActivity.USER:
                        appInfos = appManager.getAllInstalledApp();
                        break;
                }
                //发送消息到Handler
                handler.sendEmptyMessage(0);
            };
        }.start();

        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        bar.initActionBar("所有软件",R.drawable.home_left, ActionBarView.ID_BAR, null);

        lv = (ListView) findViewById(R.id.soft_mgr_list_lv);
        progressBar = (ProgressBar) findViewById(R.id.soft_mgr_list_pb);


    }
}
