package com.feicui.edu.housekeeper.biz;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug;

import com.feicui.edu.housekeeper.entity.AppInfo;
import com.feicui.edu.housekeeper.entity.RunningApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
//用于获取手机应用程序
public class AppInfoManager {

    private static AppInfoManager appInfoManager;
    private static PackageManager packageManager;
    //用于获取正在运行的应用程序信息管理类
    private static ActivityManager activityManager;
    public static final Integer USER_APPLICATION = 1;
    public static final Integer SYS_APPLICATION = 2;

    //准备给ListView展示数据用的
    private static ArrayList<AppInfo> appInfos = new ArrayList<AppInfo>();

    public static synchronized AppInfoManager getInstance(Context context){
        if(appInfoManager == null){
            appInfoManager = new AppInfoManager();
        }
        if(packageManager == null){
            packageManager = context.getPackageManager();
        }
        if (activityManager == null){
            activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
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
            if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){//用户应用
                userInfos.add(new AppInfo(packageInfo));
            }
        }
        return userInfos;
    }

    //获取正在运行的应用程序信息
    public HashMap<Integer, ArrayList<RunningApp>> getRunningAppInfos(){
        HashMap<Integer, ArrayList<RunningApp>> maps = new HashMap<Integer, ArrayList<RunningApp>>();
        ArrayList<RunningApp> sysRunningApps = new ArrayList<RunningApp>();
        ArrayList<RunningApp> userRunningApps = new ArrayList<RunningApp>();

        //获取所有正在运行的应用程序
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfos = activityManager.getRunningAppProcesses();
        //获取每个应用程序
        for (int x = 0; x < runningAppProcessInfos.size(); x++) {
            RunningApp runningApp = new RunningApp();
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcessInfos.get(x);
            int pid = runningAppProcessInfo.pid;
            Debug.MemoryInfo[] memoryInfos = activityManager.getProcessMemoryInfo(new int[pid]);
            //当前应用程序所占用的运存
            int memoryInfo = memoryInfos[0].dalvikPrivateDirty;
            String packageName = runningAppProcessInfo.processName;
            int importance = runningAppProcessInfo.importance;
            //首先判断哪些进程是可以杀死的
            if (importance >= ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE){
                try {
                    Drawable icon = packageManager.getApplicationIcon(packageName);
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
                    String label = packageManager.getApplicationLabel(applicationInfo).toString();

                    //添加数据
                    runningApp.setLabel(label);
                    runningApp.setIcon(icon);
                    runningApp.setRam(memoryInfo);

                    //判断是系统软件还是用户软件
                    if((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){//用户应用
                        userRunningApps.add(runningApp);
                    }else if((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0){//系统应用
                        sysRunningApps.add(runningApp);
                    }

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }


        }

        return null;
    }

}
