package com.feicui.edu.housekeeper.biz;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class FileManager {

    private static FileManager fileManager;

    private FileManager(){}

    public FileManager getInstance(){
        if (fileManager == null){
            fileManager = new FileManager();
        }

        return fileManager;
    }

    public String getData(){
        return null;
    }

}
