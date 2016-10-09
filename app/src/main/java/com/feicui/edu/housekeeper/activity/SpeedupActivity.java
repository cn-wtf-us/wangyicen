package com.feicui.edu.housekeeper.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.adapter.RocketListAdapter;
import com.feicui.edu.housekeeper.entity.RunningApp;
import com.feicui.edu.housekeeper.view.ActionBarView;

import java.util.ArrayList;


public class SpeedupActivity extends AppCompatActivity {

    private ListView lv;
    private TextView tv1,tv2,tv3;
    private CheckBox all;
    private ProgressBar pb;
    private ActionBarView bar;
    private RocketListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speedup);

        initView();
        
        setListener();

    }

    private void setListener() {
        all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ArrayList<RunningApp> runningApps = adapter.getDatas();
                for (RunningApp runningApp: runningApps) {
                    runningApp.setChecked(isChecked);
                }
                //更新适配器
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        lv = (ListView) findViewById(R.id.rocket_lv);
        tv1 = (TextView) findViewById(R.id.rocket_phone_name);
        tv2 = (TextView) findViewById(R.id.rocket_phone_label);
        tv3 = (TextView) findViewById(R.id.rocket_phone_total);
        all = (CheckBox) findViewById(R.id.rocket_all);
        pb = (ProgressBar) findViewById(R.id.rocket_progressbar);
        adapter = new RocketListAdapter(this);
    }

    //一键清理
    public void clean(View view){

    }

    //显示系统或用户进程
    public void showProgress(View view){

    }
}
