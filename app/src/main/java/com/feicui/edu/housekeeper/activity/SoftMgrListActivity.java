package com.feicui.edu.housekeeper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
            }
        }.start();

        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        bar.initActionBar("所有软件",R.drawable.home_left, ActionBarView.ID_BAR, null);

        lv = (ListView) findViewById(R.id.soft_mgr_list_lv);
        adapter = new SoftMgrListAdapter(this, appInfos);
        lv.setAdapter(adapter);

    }
}
