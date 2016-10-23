package com.feicui.edu.housekeeper.entity;

import java.io.File;

/**
 * Created by asus on 2016/10/23.
 */
public class SDCleanInfo {
    private boolean isChecked;
    private int pic;
    private String type;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SDCleanInfo{" +
                "icon='" + icon + '\'' +
                ", isChecked=" + isChecked +
                ", pic=" + pic +
                ", type='" + type + '\'' +
                '}';
    }
}
