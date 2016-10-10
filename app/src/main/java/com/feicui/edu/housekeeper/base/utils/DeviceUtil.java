package com.feicui.edu.housekeeper.base.utils;

import android.os.Build;

/**
 * Created by Administrator on 2016/10/10 0010.
 * 获取设备信息
 */
public class DeviceUtil {

    //获取手机品牌
    public static String getPhoneBrand(){
        return Build.BRAND;
    }

    //获取手机型号
    public static String getPhoneModel(){
        return Build.MODEL;
    }

    //获取手机版本号
    public static String getPhoneVersion(){
        return Build.VERSION.RELEASE;
    }

}
