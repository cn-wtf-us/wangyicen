package com.feicui.edu.housekeeper.base.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.feicui.edu.housekeeper.base.utils.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private static ArrayList<BaseActivity> onLineActivityList = new ArrayList<BaseActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    protected abstract void initView();
    protected abstract void setListener();

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG, getClass().getSimpleName() + "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, getClass().getSimpleName() + "onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TAG, getClass().getSimpleName() + "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, getClass().getSimpleName() + "onDestroy()");
        //清空Activity
        if (onLineActivityList.contains(this)){
            onLineActivityList.remove(this);
        }
    }

    //依次退出当前存在的所有Activity
    public static void finishAll(){
        Iterator<BaseActivity> iterator = onLineActivityList.iterator();
        while (iterator.hasNext()){
            iterator.next().finish();
        }
    }

    //普通跳转
    protected void startActivity(Class<?> targetClass){
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
    }
    //传递参数的跳转
    protected void startActivity(Class<?> targetClass, Bundle bundle){
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //带动画的跳转
    protected void startActivity(Class<?> targetClass, int inAnimID, int outAnimID){
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
        overridePendingTransition(inAnimID, outAnimID);
    }
    //带动画跳转，并传递参数
    protected void startActivity(Class<?> targetClass, int inAnimID, int outAnimID, Bundle bundle){
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(inAnimID, outAnimID);
    }

}
