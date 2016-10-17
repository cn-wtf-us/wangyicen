package com.feicui.edu.housekeeper.biz;

import android.os.Environment;
import android.util.Log;

import com.feicui.edu.housekeeper.base.utils.FileTypeUtil;
import com.feicui.edu.housekeeper.entity.FileInfo;

import java.io.File;
import java.util.ArrayList;

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

    //用于给列表加载的数据
    private ArrayList<FileInfo> allDatas = new ArrayList<FileInfo>();
    private ArrayList<FileInfo> docDatas = new ArrayList<FileInfo>();
    private ArrayList<FileInfo> picDatas = new ArrayList<FileInfo>();
    private ArrayList<FileInfo> vdDatas = new ArrayList<FileInfo>();
    private ArrayList<FileInfo> adDatas = new ArrayList<FileInfo>();
    private ArrayList<FileInfo> apkDatas = new ArrayList<FileInfo>();
    private ArrayList<FileInfo> rarDatas = new ArrayList<FileInfo>();

    public ArrayList<FileInfo> getAdDatas() {
        return adDatas;
    }

    public ArrayList<FileInfo> getAllDatas() {
        return allDatas;
    }

    public ArrayList<FileInfo> getApkDatas() {
        return apkDatas;
    }

    public ArrayList<FileInfo> getDocDatas() {
        return docDatas;
    }

    public ArrayList<FileInfo> getPicDatas() {
        return picDatas;
    }

    public ArrayList<FileInfo> getRarDatas() {
        return rarDatas;
    }

    public ArrayList<FileInfo> getVdDatas() {
        return vdDatas;
    }

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

    private FileManager(){

    }

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
                    String[] typeAndIcon = FileTypeUtil.getFileTypeAndIconName(file);
                    String type = typeAndIcon[0];
                    String icon = typeAndIcon[1];
                    //获取文件大小
                    long fileSize = file.length();
                    //将文件封装
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFile(file);
                    fileInfo.setIcon(icon);
                    fileInfo.setType(type);

                    //计算文件大小
                    switch (type){
                        case FileTypeUtil.TYPE_APK:
                            apkSize += fileSize;
                            apkDatas.add(fileInfo);
                            break;
                        case FileTypeUtil.TYPE_AUDIO:
                            adSize += fileSize;
                            adDatas.add(fileInfo);
                            break;
                        case FileTypeUtil.TYPE_IMAGE:
                            picSize += fileSize;
                            picDatas.add(fileInfo);
                            break;
                        case FileTypeUtil.TYPE_TXT:
                            docSize += fileSize;
                            docDatas.add(fileInfo);
                            break;
                        case FileTypeUtil.TYPE_VIDEO:
                            vdSize += fileSize;
                            vdDatas.add(fileInfo);
                            break;
                        case FileTypeUtil.TYPE_ZIP:
                            rarSize += fileSize;
                            rarDatas.add(fileInfo);
                            break;
                    }
                    //所有文件的总大小
                    allSize += fileSize;
                    allDatas.add(fileInfo);


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


    //清理所有数据
    public void clearCache(){
        allSize = 0;
        docSize = 0;
        adSize = 0;
        vdSize = 0;
        apkSize = 0;
        rarSize = 0;
        picSize = 0;

        allDatas.clear();
        docDatas.clear();
        apkDatas.clear();
        adDatas.clear();
        vdDatas.clear();
        picDatas.clear();
        rarDatas.clear();
    }

}
