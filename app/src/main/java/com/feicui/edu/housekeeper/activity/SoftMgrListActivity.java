package com.feicui.edu.housekeeper.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private AppInfoManager appManager;
    private int key;
    private ProgressBar progressBar;
    private CheckBox allSel;
    private MyAppDeletedReceiver myAppDeletedReceiver;

    //创建一个Handler的子类对象
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressBar.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            //接收和处理消息
            adapter.addData(appInfos);
            adapter.notifyDataSetChanged();
        }
    };//接收和处理消息

    //删除所有应用程序
    public void delApp(View view){
        //先确定要删除的应用软件
        ArrayList<AppInfo> appInfos = adapter.getAppInfos();
        for (AppInfo appInfo : appInfos) {
            //判断软件是否为选中状态
            if (appInfo.isChecked()){
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:" + appInfo.getInfo().packageName));
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_mgr_list);
        initView();
        getData();


        setListener();
    }

    public void setListener() {
        allSel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ArrayList<AppInfo> appInfos = adapter.getAppInfos();
                for (AppInfo appInfo : appInfos) {
                    appInfo.setChecked(isChecked);
                }
                //更新适配器
                adapter.notifyDataSetChanged();
            }
        });

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

    public void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        appManager = AppInfoManager.getInstance(this);
        key = getIntent().getIntExtra("soft", 1);
        adapter = new SoftMgrListAdapter(SoftMgrListActivity.this);
        bar.initActionBar("软件列表",ActionBarView.ID_BAR, ActionBarView.ID_BAR, null);
        lv = (ListView) findViewById(R.id.soft_mgr_list_lv);
        progressBar = (ProgressBar) findViewById(R.id.soft_mgr_list_pb);
        allSel = (CheckBox) findViewById(R.id.soft_mgr_list_all);
        //创建广播
        myAppDeletedReceiver = new MyAppDeletedReceiver();
        //指定要接收的广播类型
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_REMOVED);
        //指定删除的内容是什么
        filter.addDataScheme("package");
        //打开广播
        registerReceiver(myAppDeletedReceiver, filter);
        lv.setAdapter(adapter);
    }

    public void getData() {
        //显示进度条，隐藏列表
        progressBar.setVisibility(View.VISIBLE);
        lv.setVisibility(View.GONE);

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
    }

    //创建广播接收者
    class MyAppDeletedReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //应用程序被删除，更新列表
            Log.i("onReceive", "application deleted");
            getData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        unregisterReceiver(myAppDeletedReceiver);
    }
}