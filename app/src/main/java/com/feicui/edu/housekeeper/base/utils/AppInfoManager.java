package com.feicui.edu.housekeeper.base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.feicui.edu.housekeeper.entity.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
//用于获取手机应用程序
public class AppInfoManager {

    private static AppInfoManager appInfoManager;
    private static PackageManager packageManager;
    //准备给ListView展示数据用的
    private static ArrayList<AppInfo> appInfos = new ArrayList<AppInfo>();

    public static synchronized AppInfoManager getInstance(Context context){
        if(appInfoManager == null){
            appInfoManager = new AppInfoManager();
        }
        if(packageManager == null){
            packageManager = context.getPackageManager();
        }
        return appInfoManager;
    }

    /* 用于获取所有已经安装的应用程序集合*/
    public ArrayList<AppInfo> getAllInstalledApp(){
        //获取所有已经安装在手机上的应用
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);

        //每次调用此函数前需要清空集合
        appInfos.clear();

        for(PackageInfo info :packageInfos){
            appInfos.add(new AppInfo(info));
        }
        return appInfos;
    }

    /* 获取系统应用信息*/
    public ArrayList<AppInfo> getSysInstalledApp(){
        ArrayList<AppInfo> sysInfos = new ArrayList<AppInfo>();
        ArrayList<AppInfo> appInfos = getAllInstalledApp();
        //判断到底哪些是系统的
        for(AppInfo appInfo:appInfos){
            PackageInfo packageInfo = appInfo.getInfo();
            if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0){//系统应用
                sysInfos.add(new AppInfo(packageInfo));
            }
        }
        return sysInfos;
    }

    /* 获取用户应用信息*/
    public ArrayList<AppInfo> getUserInstalledApp(){
        ArrayList<AppInfo> userInfos = new ArrayList<AppInfo>();
        ArrayList<AppInfo> appInfos = getAllInstalledApp();
        //判断到底哪些是系统的
        for(AppInfo appInfo:appInfos){
            PackageInfo packageInfo = appInfo.getInfo();
            if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){//系统应用
                userInfos.add(new AppInfo(packageInfo));
            }
        }
        return userInfos;
    }
}
