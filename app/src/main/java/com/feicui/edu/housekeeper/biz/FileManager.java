package com.feicui.edu.housekeeper.biz;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class FileManager {

    private static FileManager fileManager;
    public static File sdPath;

    private FileManager(){}

    static {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            sdPath = new File(Environment.getExternalStorageDirectory().getPath());
        }
    }

    public static FileManager getInstance(){
        if (fileManager == null){
            fileManager = new FileManager();
        }

        return fileManager;
    }

    public void searchFile(File path, boolean flag) {
        File[] files = path.listFiles();
        //判断
        if (files != null){
            for (File file : files) {
                if (file.isFile()){
                    Log.i("FileManager", file.getName());
                    on.getData(file.getName());
                }else {
                    //递归
                    searchFile(file,false);
                }
            }
            if (flag){
                on.searchEnd();
            }

        }

    }

    public static interface OnDataSendListener{
        //通知activity，搜索到一个文件
        void getData(String text);
        //搜索完成
        void searchEnd();
    }

    private OnDataSendListener on;

    public void setOnDataSendListener(OnDataSendListener on){
        this.on = on;
    }

    public String getData(){
        return null;
    }

}
