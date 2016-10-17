package com.feicui.edu.housekeeper.base.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by asus on 2016/10/17.
 */
public class CommonUtil {

    public static String parseDateToString(long lastModify){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(lastModify);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        String time = sdf.format(date);
        return time;
    }
}
