package com.feicui.edu.housekeeper.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.base.utils.MemoryUtil;
import com.feicui.edu.housekeeper.view.ActionBarView;
import com.feicui.edu.housekeeper.view.MainPieChart;


public class HomeActivity extends BaseActivity {
    private ActionBarView bar;
    private MainPieChart mainPieChart;
    private TextView point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
        View.OnClickListener on = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.iv_left://跳转关于界面
                        HomeActivity.this.startActivity(AboutActivity.class);
                        break;
                    case R.id.iv_right://跳转设置界面
                        HomeActivity.this.startActivity(SettingActivity.class);
                }
            }
        };
        bar.initActionBar("HouseKeeper",R.drawable.home_left, R.drawable.home_right, on);

        //计算角度值
        long usedMem = MemoryUtil.getPhoneTotalRamMemory() - MemoryUtil.getPhoneAvRamMemory(this);
        long totalMem = MemoryUtil.getPhoneTotalRamMemory();
        //计算显示百分比数
        point.setText((int)Math.round(usedMem / (float)totalMem * 100) + "");
        //计算扇形角度
        float angel = (usedMem / (float)totalMem) * 360;
        //设置最大角度
        mainPieChart.setMaxAngel(angel);
        mainPieChart.startMove();
    }

    @Override
    protected void initView() {
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        mainPieChart = (MainPieChart) findViewById(R.id.view_main_pie_chart);
        point = (TextView) findViewById(R.id.view_main_pie_chart_tv);
    }

    @Override
    protected void setListener() {

    }

    public void clean(View view){
        switch (view.getId()){
            case R.id.view_main_pie_chart_iv:

                break;
            case R.id.view_main_pie_chart_tv:

                break;
        }
    }

    public void hitHomeItem(View view){
        int viewID = view.getId();
        switch (viewID){
            case R.id.home_speedup:
                //跳转到手机加速界面
                startActivity(SpeedupActivity.class);
                break;
            case R.id.home_softmgr:
                //跳转到软件管理界面
                startActivity(SoftManagerActivity.class);
                break;
            case R.id.home_phonemgr:
                //跳转到手机检测界面
                startActivity(PhoneCheckActivity.class);
                break;
            case R.id.home_telmgr:
                //跳转到通讯大全界面
                startActivity(TelmsgActivity.class);
                break;
            case R.id.home_filemgr:
                //跳转到文件管理界面
                startActivity(FileManagerActivity.class);
                break;
            case R.id.home_sdclean:
                //跳转到垃圾清理界面
                startActivity(SDCleanActivity.class);
                break;
        }
    }
}
