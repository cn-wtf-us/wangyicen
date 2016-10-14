package com.feicui.edu.housekeeper.base.utils;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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

    //获取CPU的名称
    public static String getCpuName(){
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("proc/cpuinfo"));
            String[] lines = new String[2];
            String line = null;
            for (int x = 0; x < lines.length; x++) {
                line = bufferedReader.readLine();
                lines[x] = line;
            }
            String[] datas = lines[1].split(":\\s+", 2);
            return datas[1];
        } catch (IOException e) {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    //获取CPU数量
    public static int getCpuNumber(){
        File file = new File("sys/devices/system/cpu");
        //获取当前路径下的文件或文件夹
        File[] allFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().matches("cpu[0-9]");
            }
        });
        return allFiles.length;
    }

    //获取手机屏幕分辨率
    public static String getDisplayMetrics(Context context){
        //获取屏幕管理器
        WindowManager windowManager = (WindowManager) context.getSystemService
                (Context.WINDOW_SERVICE);
        //获取屏幕对象
        Display display = windowManager.getDefaultDisplay();
        //创建一个空的屏幕参数对象
        DisplayMetrics metrics = new DisplayMetrics();
        //获取屏幕分辨率
        display.getMetrics(metrics);
        return metrics.widthPixels + "*" + metrics.heightPixels;
    }

    //获取手机相机的最大分辨率
    public static String getCameraMetrics(Context context){
        //打开摄像头
        Camera camera = Camera.open();
        if (camera != null){
            //从摄像头中获取参数
            Camera.Parameters parameters = camera.getParameters();
            //从参数对象中，获取相机所支持的所有分辨率
            List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
            int max = 0;
            Camera.Size s = null;
            for (int x = 0; x < sizes.size(); x++) {
                Camera.Size size = sizes.get(x);
                if (size.width * size.height > max){
                    max = size.width * size.height;
                    s = size;
                }
            }
            camera.stopPreview();
            camera.release();
            camera = null;
            return s.width + "*" + s.height;
        }
       return " ";
    }

    //获取基带版本
    public static String getRadio(){
        return Build.RADIO;
    }

    //手机是否ROOT
    public static boolean isRoot(){

        if (!new File("system/bin/su").exists() || !new File("system/xbin/su").exists()){
            return false;
        }
        return true;
    }


}
