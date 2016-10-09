package com.feicui.edu.housekeeper.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class RunningApp {
    private boolean isChecked;
    private Drawable icon;
    private String label;
    private String ram;
    private boolean isSys;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isSys() {
        return isSys;
    }

    public void setSys(boolean sys) {
        isSys = sys;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }
}
