package com.feicui.edu.housekeeper.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class ToastUtil {
    private static Toast toast;

    public static void show(Context context, String text, int duration){
        if (toast == null){
            toast = Toast.makeText(context, text, duration);
        }
        toast.setText(text);
        toast.setText(duration);
        toast.show();
    }
}
