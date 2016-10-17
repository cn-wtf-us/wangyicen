package com.feicui.edu.housekeeper.entity;

import java.io.File;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class FileInfo {

    private boolean isChecked;
    private String type;
    private File file;
    private String icon;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public FileInfo(boolean isChecked, String type, File file, String icon) {
        this.isChecked = isChecked;
        this.type = type;
        this.file = file;
        this.icon = icon;
    }

    public FileInfo() {
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "isChecked=" + isChecked +
                ", type='" + type + '\'' +
                ", file=" + file +
                ", icon=" + icon +
                '}';
    }

}
