package com.feicui.edu.housekeeper.base.utils;

import android.os.Environment;
import android.os.StatFs;

/**
 * Created by asus on 2016/9/29.
 * 内存管理工具类
 */
public class MemoryUtil {
//    获取手机自带的内存空间
    public static long getPhoneSelfTotalRom(){
        String path = Environment.getDataDirectory().getPath();
        StatFs statFs = new StatFs(path);
//        计算手机自带空间的内存块
        int count = statFs.getBlockCount();
//        获取每个内存块的大小
        long size = statFs.getBlockSize();

        return size * count;
    }

    //    获取手机自带内存空闲空间ROM
    public static long getPhoneSelfAvRom(){
        String path = Environment.getDataDirectory().getPath();
        StatFs statFs = new StatFs(path);
//        计算手机自带空间的内存块
        int count = statFs.getAvailableBlocks();
//        获取每个内存块的大小
        long size = statFs.getBlockSize();
        return size * count;
    }

    //    获取SD卡内存空间ROM
    public static long getPhoneSDRom(){
        String path = Environment.getExternalStorageDirectory().getPath();
        StatFs statFs = new StatFs(path);
//        计算手机自带空间的内存块
        int count = statFs.getBlockCount();
//        获取每个内存块的大小
        long size = statFs.getBlockSize();
        return size * count;
    }


    //    获取SD卡空闲空间ROM
    public static long getPhoneSDAvRom(){
        String path = Environment.getExternalStorageDirectory().getPath();
        StatFs statFs = new StatFs(path);
//        计算手机自带空间的内存块
        int count = statFs.getAvailableBlocks();
//        获取每个内存块的大小
        long size = statFs.getBlockSize();

        return size * count;
    }
}
