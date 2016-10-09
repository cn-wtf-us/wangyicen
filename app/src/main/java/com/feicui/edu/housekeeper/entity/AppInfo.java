package com.feicui.edu.housekeeper.entity;

import android.content.pm.PackageInfo;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
public class AppInfo {

    private PackageInfo info;
    //控制checkbox状态
    private boolean isChecked;

    public AppInfo(PackageInfo info){
        super();
        this.info = info;
    }
    public PackageInfo getInfo(){
        return info;
    }
    public void setInfo(PackageInfo info){
        this.info = info;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
