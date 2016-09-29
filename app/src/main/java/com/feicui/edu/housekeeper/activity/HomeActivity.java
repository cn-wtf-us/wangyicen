package com.feicui.edu.housekeeper.activity;
import android.os.Bundle;
import android.view.View;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.activity.BaseActivity;
import com.feicui.edu.housekeeper.view.ActionBarView;


public class HomeActivity extends BaseActivity {
    private ActionBarView bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        bar.initActionBar("HouseKeeper",R.drawable.home_left, R.drawable.home_right , on);
    }
    View.OnClickListener on = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.drawable.home_left:
                    startActivity(AboutActivity.class);
                    break;
                case R.drawable.home_right:
                    startActivity(SettingActivity.class);
                    break;
            }
        }
    };
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
