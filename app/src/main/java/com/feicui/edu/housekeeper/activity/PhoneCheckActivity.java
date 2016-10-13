package com.feicui.edu.housekeeper.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.adapter.PhoneCheckAdapter;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.view.ActionBarView;


public class PhoneCheckActivity extends BaseActivity {

    private ActionBarView bar;
    private ProgressBar progressBar;
    private TextView textView;//电池的百分比数
    private ListView listView;
    private PhoneCheckAdapter adapter;
    private BatteryReceiver receiver;
    private int scale, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_phone_check);
        super.onCreate(savedInstanceState);
        //导航条
        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneCheckActivity.this.finish();
            }
        };
        bar.initActionBar("手机检测", R.id.iv_left, ActionBarView.ID_BAR, on);
        listView.setAdapter(adapter);
        int point = Math.round(level / (float)scale * 100);
        textView.setText(point + "");
        progressBar.setProgress(point);

    }

    @Override
    protected void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        progressBar = (ProgressBar) findViewById(R.id.phone_check_progressbar);
        textView = (TextView) findViewById(R.id.phone_check_point);
        listView = (ListView) findViewById(R.id.phone_check_lv);
        receiver = new BatteryReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //注册广播，动态注册
        registerReceiver(receiver, filter);
        adapter = new PhoneCheckAdapter(this);

    }

    @Override
    protected void setListener() {

    }

    class BatteryReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //获取电池中的信息
            //获取手机总电量
            scale = intent.getIntExtra("scale", 0);
            //获取当前电量
            level = intent.getIntExtra("level", 0);
            //获取手机电池温度
            int tempreture = intent.getIntExtra("tempreture", 0);
            //获取手机电池健康程度
            int health = intent.getIntExtra("health", 0);
            //获取手机电池电压状态
            int voltage = intent.getIntExtra("voltage", 0);
            //获取手机电池当前状态
            int status = intent.getIntExtra("status", 0);

        }
    }
}
