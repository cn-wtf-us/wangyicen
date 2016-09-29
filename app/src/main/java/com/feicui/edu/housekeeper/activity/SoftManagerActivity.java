package com.feicui.edu.housekeeper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.base.utils.MemoryUtil;
import com.feicui.edu.housekeeper.view.ActionBarView;

import java.text.Format;
import java.util.Formatter;


public class SoftManagerActivity extends BaseActivity {

    private ActionBarView bar;
    private TextView phoneTv, SDCardTv;
    private ProgressBar pb1, pb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_manager);
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        bar.initActionBar("软件管理", ActionBarView.ID_BAR, ActionBarView.ID_BAR, null);
        phoneTv = (TextView) findViewById(R.id.soft_mgr_phone_memory);
        SDCardTv = (TextView) findViewById(R.id.soft_mgr_sdcard_memory);
        pb1 = (ProgressBar) findViewById(R.id.progressBar1);
        pb2 = (ProgressBar) findViewById(R.id.progressBar2);


        String phoneTotal = android.text.format.Formatter.formatFileSize(this, MemoryUtil.getPhoneSelfTotalRom());
        String phone = android.text.format.Formatter.formatFileSize(this, MemoryUtil.getPhoneSelfTotalRom() - MemoryUtil.getPhoneSelfAvRom());
        String sdTotal = android.text.format.Formatter.formatFileSize(this, MemoryUtil.getPhoneSDRom());
        String sdav = android.text.format.Formatter.formatFileSize(this, MemoryUtil.getPhoneSDRom() - MemoryUtil.getPhoneSDAvRom());
        //计算手机内存信息
        phoneTv.setText(phone + "/" + phoneTotal);
        //计算SD卡内存信息
        SDCardTv.setText(sdav + "/" + sdTotal);

        //计算百分比
        pb1.setProgress((int)Math.round((MemoryUtil.getPhoneSelfTotalRom() - MemoryUtil.getPhoneSelfAvRom()) /
                (double)MemoryUtil.getPhoneSelfTotalRom()) * 100);
        pb2.setProgress((int)Math.round((MemoryUtil.getPhoneSDRom() - MemoryUtil.getPhoneSDAvRom()) /
                (double)MemoryUtil.getPhoneSDRom()) * 100);

    }

    //跳转到软件管理的列表界面
    public void navigationToList(View view) {

        startActivity(SoftMgrListActivity.class);
    }
}
