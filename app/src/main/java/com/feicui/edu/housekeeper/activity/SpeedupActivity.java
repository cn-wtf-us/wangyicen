package com.feicui.edu.housekeeper.activity;

import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.adapter.RocketListAdapter;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.base.utils.DeviceUtil;
import com.feicui.edu.housekeeper.base.utils.LogUtil;
import com.feicui.edu.housekeeper.base.utils.MemoryUtil;
import com.feicui.edu.housekeeper.biz.AppInfoManager;
import com.feicui.edu.housekeeper.entity.RunningApp;
import com.feicui.edu.housekeeper.view.ActionBarView;

import java.util.ArrayList;
import java.util.HashMap;


public class SpeedupActivity extends BaseActivity {

    private ListView lv;
    private TextView tv1,tv2,tv3;
    private Button show, clean;
    private CheckBox all;
    private ProgressBar pb1, pb2;
    private ActionBarView bar;
    private RocketListAdapter adapter;
    private AppInfoManager appInfoManager;
    private HashMap<Integer, ArrayList<RunningApp>> runningApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_speedup);
        super.onCreate(savedInstanceState);

        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeedupActivity.this.finish();
            }
        };
        bar.initActionBar("手机加速", R.drawable.home_left, ActionBarView.ID_BAR, on);

        //设置设备型号
        tv1.setText(DeviceUtil.getPhoneBrand());
        tv2.setText(DeviceUtil.getPhoneModel() + " Android " + DeviceUtil.getPhoneVersion());

        updateProgress();

        getData();
    }

    private void getData() {
        pb2.setVisibility(View.VISIBLE);
        lv.setVisibility(View.GONE);
        new Thread(){
            @Override
            public void run() {
                appInfoManager = AppInfoManager.getInstance(SpeedupActivity.this);
                runningApps = appInfoManager.getRunningAppInfos(SpeedupActivity.this);

                //Handler   runOnUiThread   让参数中的代码在主线程中执行
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //隐藏进度条，显示列表信息
                        pb2.setVisibility(View.GONE);
                        lv.setVisibility(View.VISIBLE);
                        //更新上方进度条信息
                        updateProgress();
                        adapter.setState(RocketListAdapter.SHOW_SYS);
                        //将数据添加到适配器中
                        adapter.addDatas(runningApps.get(AppInfoManager.SYS_APPLICATION));
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }

    private void updateProgress() {
        long usedMem = MemoryUtil.getPhoneTotalRamMemory() - MemoryUtil.getPhoneAvRamMemory(this);
        long totalMem = MemoryUtil.getPhoneTotalRamMemory();
        tv3.setText("已用内存：" + Formatter.formatFileSize(this, usedMem) + "/" + Formatter.formatFileSize(this, totalMem));
        pb1.setProgress((int)Math.round(usedMem / (double)totalMem * 100));
    }

    protected void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        lv = (ListView) findViewById(R.id.rocket_lv);
        tv1 = (TextView) findViewById(R.id.rocket_phone_name);
        tv2 = (TextView) findViewById(R.id.rocket_phone_label);
        tv3 = (TextView) findViewById(R.id.rocket_phone_total);
        all = (CheckBox) findViewById(R.id.rocket_all);
        pb1 = (ProgressBar) findViewById(R.id.rocket_progressbar);
        pb2 = (ProgressBar) findViewById(R.id.rocket_list_pb);
        show = (Button) findViewById(R.id.rocket_show);
        clean = (Button) findViewById(R.id.rocket_clean);
        adapter = new RocketListAdapter(this);
        lv.setAdapter(adapter);
    }

    protected void setListener() {
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

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState){
                    case SCROLL_STATE_FLING:
                        adapter.setFlying(true);
                        break;
                    case SCROLL_STATE_IDLE:
                        //加载图片
                        adapter.setFlying(false);
                        adapter.notifyDataSetChanged();
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        adapter.setFlying(true);
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItemCount,
                                 int visibleItemCount, int totalItemCount) {

            }
        });
    }

    //一键清理
    public void clean(View view){
        //找到所有被选中的应用
        ArrayList<RunningApp> runningApps = adapter.getDatas();
        for (RunningApp runningApp : runningApps){
            if (runningApp.isChecked()){
                //杀死进程
                appInfoManager.killProgress(runningApp.getPackageName());
                //删除集合中被杀死进程的数据
                getData();
                all.setChecked(false);
            }
        }
        adapter.notifyDataSetChanged();
    }

    //显示系统或用户进程
    public void showProgress(View view){

        switch (adapter.getState()){
            case RocketListAdapter.SHOW_SYS:
                //切换到用户状态
                show.setText("显示系统进程");
                adapter.setState(RocketListAdapter.SHOW_USER);
                adapter.addDatas(runningApps.get(AppInfoManager.USER_APPLICATION));
                break;
            case RocketListAdapter.SHOW_USER:
                show.setText("显示用户进程");
                adapter.setState(RocketListAdapter.SHOW_SYS);
                adapter.addDatas(runningApps.get(AppInfoManager.SYS_APPLICATION));
                break;
        }
        adapter.notifyDataSetChanged();
    }
}
