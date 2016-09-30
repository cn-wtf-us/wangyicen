package com.feicui.edu.housekeeper.activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.feicui.edu.housekeeper.R;
import com.feicui.edu.housekeeper.base.utils.AppInfoManager;
import com.feicui.edu.housekeeper.entity.AppInfo;
import com.feicui.edu.housekeeper.view.ActionBarView;

import java.util.ArrayList;

public class SoftMgrListActivity extends AppCompatActivity {
    private ActionBarView bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_mgr_list);
        bar = (ActionBarView) findViewById(R.id.view_action_bar);
        bar.initActionBar("所有软件",R.drawable.home_left, ActionBarView.ID_BAR, null);

        AppInfoManager appManager = AppInfoManager.getInstance(this);
        ArrayList<AppInfo> appInfos = appManager.getAllInstalledApp();

        for (AppInfo appInfo : appInfos){
            PackageInfo info = appInfo.getInfo();
            ApplicationInfo app = info.applicationInfo;

            Log.i("data", info.packageName + "," + info.versionName + "," +
                    app.loadIcon(getPackageManager()) + "," + app.loadLabel(getPackageManager()));
        }

    }
}
