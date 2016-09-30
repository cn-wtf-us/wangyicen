package com.feicui.edu.housekeeper.entity;

import android.content.pm.PackageInfo;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
public class AppInfo {

    private PackageInfo info;

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
}
