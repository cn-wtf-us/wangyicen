package com.feicui.edu.housekeeper.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class SharedPreferenceUtil {
    private static SharedPreferences sharedPreferences;
    private static final String KEY_TOGGLE_STATE = "key_toggle_state";

    public static void saveToggleState(Context context, boolean state){
        //获取简单编辑器对象，同时会创建一个xml文件
        sharedPreferences = context.getSharedPreferences("state", context.MODE_PRIVATE);

        Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_TOGGLE_STATE, state);
        //提交
        editor.commit();
    }
    public static boolean getToggleState(Context context){
        sharedPreferences = context.getSharedPreferences("state", context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(KEY_TOGGLE_STATE, true);
    }
}
