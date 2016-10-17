package com.feicui.edu.housekeeper.biz;

import android.os.Environment;
import android.util.Log;

import com.feicui.edu.housekeeper.base.utils.FileTypeUtil;

import java.io.File;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class FileManager {

    private static FileManager fileManager;
    //用于记录总文件的大小
    private long allSize;
    private long docSize;
    private long picSize;
    private long vdSize;
    private long adSize;
    private long apkSize;
    private long rarSize;
    public static File sdPath;

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static File getSdPath() {
        return sdPath;
    }

    public long getRarSize() {
        return rarSize;
    }

    public long getApkSize() {
        return apkSize;
    }

    public long getAdSize() {
        return adSize;
    }

    public long getVdSize() {
        return vdSize;
    }

    public long getPicSize() {
        return picSize;
    }

    public long getDocSize() {
        return docSize;
    }

    public long getAllSize() {
        return allSize;
    }

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
                    //判断文件属于哪种类型
                    String type = FileTypeUtil.getFileType(file);
                    //获取文件大小
                    long fileSize = file.length();
                    //计算文件大小
                    switch (type){
                        case FileTypeUtil.TYPE_APK:
                            apkSize += fileSize;
                            break;
                        case FileTypeUtil.TYPE_AUDIO:
                            adSize += fileSize;
                            break;
                        case FileTypeUtil.TYPE_IMAGE:
                            picSize += fileSize;
                            break;
                        case FileTypeUtil.TYPE_TXT:
                            docSize += fileSize;
                            break;
                        case FileTypeUtil.TYPE_VIDEO:
                            vdSize += fileSize;
                            break;
                        case FileTypeUtil.TYPE_ZIP:
                            rarSize += fileSize;
                            break;
                    }
                    //所有文件的总大小
                    allSize += fileSize;

                    Log.i("FileManager", file.getName());
                    //数据文件已经获取到
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

    public interface OnDataSendListener{
        //通知activity，搜索到一个文件
        void getData(String type);
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
