package com.feicui.edu.housekeeper.base.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

    //获取手机最大的运存Ram
    public static long getPhoneTotalRamMemory(){
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader("proc/meminfo");
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String[] datas = line.split("//s+");
            return Integer.parseInt(datas[1]) * 1024;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    //获取空闲的运存
    public static long getPhoneAvRamMemory(Context context){
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

}
