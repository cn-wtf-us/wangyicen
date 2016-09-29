package com.feicui.edu.housekeeper.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.ToggleButton;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.base.utils.NotificationUtil;
import com.feicui.edu.housekeeper.base.utils.SharedPreferenceUtil;
import com.feicui.edu.housekeeper.view.ActionBarView;
public class SettingActivity extends BaseActivity {
    private ActionBarView bar;
    private ToggleButton notification;
    private RelativeLayout help,about;
    private static final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        boolean toggleState = SharedPreferenceUtil.getToggleState(this);
        notification.setChecked(toggleState);

        notification = (ToggleButton) findViewById(R.id.setting_notification);
        help = (RelativeLayout) findViewById(R.id.setting_help);
        about = (RelativeLayout) findViewById(R.id.setting_about);
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        bar.initActionBar("设 置", R.drawable.home_left, ActionBarView.ID_BAR, on);
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //保存状态
                SharedPreferenceUtil.saveToggleState(SettingActivity.this, isChecked);

                if (isChecked){
                    NotificationUtil.showAppIconNotification(SettingActivity.this);
                }else{
                    NotificationUtil.closeAppIconNotification(SettingActivity.this);
                }
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AboutActivity.class);
            }
        });
    }
    View.OnClickListener on = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SettingActivity.this.finish();
        }
    };
    //退出设置界面时，同样关闭通知
    @Override
    protected void onDestroy() {
        super.onDestroy();
        NotificationUtil.closeAppIconNotification(SettingActivity.this);
    }


}
