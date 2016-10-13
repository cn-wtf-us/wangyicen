package com.feicui.edu.housekeeper.entity;

/**
 * Created by asus on 2016/10/13.
 */
public class DeviceInfo {
    private int pic;
    private String info1;
    private String info2;

    public DeviceInfo(String info1, String info2, int pic) {
        this.info1 = info1;
        this.info2 = info2;
        this.pic = pic;
    }

    public DeviceInfo() {
    }

    public int getPic() {

        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getInfo2() {

        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo1() {

        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }
}
