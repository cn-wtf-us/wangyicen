package com.feicui.edu.housekeeper.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
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
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
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
                        appInfos = appManager.getSysInstalledApp();
                        break;
                    case SoftManagerActivity.USER:
                        appInfos = appManager.getUserInstalledApp();
                        break;
                }
                //发送消息到Handler
                handler.sendEmptyMessage(0);
            };
        }.start();
        bar.initActionBar("软件列表",ActionBarView.ID_BAR, ActionBarView.ID_BAR, null);
        lv = (ListView) findViewById(R.id.soft_mgr_list_lv);
        progressBar = (ProgressBar) findViewById(R.id.soft_mgr_list_pb);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            //当滚动状态更改时调用
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState){
                    case SCROLL_STATE_IDLE://空闲 SCROLL_STATE_IDLE = 0
                        //给适配器设置状态
                        adapter.setFlying(false);
                        //需要加载图片更新界面
                        adapter.notifyDataSetChanged();
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL://缓慢滑动  SCROLL_STATE_TOUCH_SCROLL = 1
                        adapter.setFlying(true);
                        break;
                    case SCROLL_STATE_FLING://快速滑动  SCROLL_STATE_FLING = 2
                        adapter.setFlying(true);
                        break;
                }
            }
            //当手指滚动时调用
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            }
        });
    }
}
