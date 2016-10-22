package com.feicui.edu.housekeeper.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.adapter.PhoneCheckAdapter;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.base.utils.DeviceUtil;
import com.feicui.edu.housekeeper.base.utils.MemoryUtil;
import com.feicui.edu.housekeeper.entity.DeviceInfo;
import com.feicui.edu.housekeeper.view.ActionBarView;

import java.util.ArrayList;


public class PhoneCheckActivity extends BaseActivity {

    private ActionBarView bar;
    private ProgressBar progressBar;
    private TextView textView;//电池的百分比数
    private ListView listView;
    private LinearLayout layout;
    private PhoneCheckAdapter adapter;
    private BatteryReceiver receiver;
    private ArrayList<DeviceInfo> deviceInfos;
    private int scale, level, tempreture, voltage;
    private int point;//设置百分比

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
        bar.initActionBar("手机检测", R.drawable.home_left, ActionBarView.ID_BAR, on);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        progressBar = (ProgressBar) findViewById(R.id.phone_check_progressbar);
        textView = (TextView) findViewById(R.id.phone_check_point);
        listView = (ListView) findViewById(R.id.phone_check_lv);
        layout = (LinearLayout) findViewById(R.id.phone_check_layout);
        deviceInfos = new ArrayList<DeviceInfo>();
        receiver = new BatteryReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //注册广播，动态注册
        registerReceiver(receiver, filter);
        adapter = new PhoneCheckAdapter(this);


        //获取列表中的数据
        getData();

    }
    private void getData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                setData(R.drawable.setting_info_icon_version,
                        "设备名称："+DeviceUtil.getPhoneBrand(),
                        "系统版本："+DeviceUtil.getPhoneVersion());
                setData(R.drawable.setting_info_icon_space,
                        "全部运行内存：" + (MemoryUtil.getPhoneTotalRamMemory()/1024/1024) + "M",
                        "剩余运行内存：" + (MemoryUtil.getPhoneAvRamMemory(PhoneCheckActivity.this)
                                /1024/1024) + "M");
                setData(R.drawable.setting_info_icon_cpu,
                        "CPU名称："+ DeviceUtil.getCpuName(),
                        "CPU数量：" + DeviceUtil.getCpuNumber());
                setData(R.drawable.setting_info_icon_camera,
                        "手机分辨率：" + DeviceUtil.getDisplayMetrics(PhoneCheckActivity.this),
                        "相机分辨率：" + DeviceUtil.getCameraMetrics(PhoneCheckActivity.this));
                setData(R.drawable.setting_info_icon_root,
                        "基带版本：" + DeviceUtil.getRadio(),
                        "是否ROOT：" + (DeviceUtil.isRoot()? "是" : "否"));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addDatas(deviceInfos);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }

    //设置手机设备数据信息
    private void setData(int pic, String text1, String text2){
        DeviceInfo info= new DeviceInfo();
        info.setPic(pic);
        info.setInfo1(text1);
        info.setInfo2(text2);
        deviceInfos.add(info);
    }

    @Override
    protected void setListener() {
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PhoneCheckActivity.this);
                builder.setTitle("电池电量信息");
                builder.setMessage("电池温度:" + tempreture * 0.1 + "℃\n\n电池电压:" + voltage);
                builder.show();
            }
        });
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PhoneCheckActivity.this);
                builder.setTitle("电池电量信息");
                builder.setMessage("电池温度:" + tempreture * 0.1 + "℃\n\n电池电压:" + voltage);
                builder.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public class BatteryReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取电池中的信息
            //获取手机总电量
            scale = intent.getIntExtra("scale", 0);
            //获取当前电量
            level = intent.getIntExtra("level", 0);
            //获取手机电池温度
            tempreture = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            //获取手机电池电压状态
            voltage = intent.getIntExtra("voltage", 0);
            //获取手机电池健康程度
//            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            //获取手机电池当前状态
//            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            point = (int) Math.round(level / (float)scale * 100);

            textView.setText(level + "");
            progressBar.setMax(scale);
            progressBar.setProgress(point);


        }
    }
}
