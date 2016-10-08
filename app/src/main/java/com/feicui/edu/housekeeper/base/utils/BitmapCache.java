package com.feicui.edu.housekeeper.base.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.feicui.edu.housekeeper.R;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

/**
 * Created by Administrator on 2016/10/8 0008.
 * 图片软引用缓存
 */
public class BitmapCache {
    private static BitmapCache bitmapCache;
    private Hashtable<Integer, MySoft> datas;
    private ReferenceQueue<Bitmap> queue;

    private class MySoft extends SoftReference<Bitmap>{
        private int key;
        public MySoft(Bitmap referent, ReferenceQueue<? super Bitmap> q, int key) {
            super(referent, q);
            this.key = key;
        }
    }

    //添加图片到集合
    public void addBitmap(int key, Bitmap bitmap){
        //判断要添加的图片在集合中是否存在，如果存在则不添加
        /*if (datas.containsKey(key)){
            return;
        }*/
        datas.put(key, new MySoft(bitmap, queue, key));

    }
    //获取数据
    public Bitmap getBitmap(int key, Context context){
        //判断软引用中是否存在该图片
        if (datas.containsKey(key)){
            //获取图片
            MySoft mySoft = datas.get(key);
            Bitmap bitmap = mySoft.get();
            if (bitmap == null){
                //使用一张默认的图片代替
                //换一张图片转换成Bitmap类型
                bitmap = BitmapFactory.decodeResource
                        (context.getResources(), R.mipmap.ic_launcher);
            }
            return bitmap;
        }
        return null;
    }

    private BitmapCache(){
        datas = new Hashtable<Integer, MySoft>();
        queue = new ReferenceQueue<Bitmap>();
    }
    public static BitmapCache getInstance(){
        if (bitmapCache == null){
            bitmapCache = new BitmapCache();
        }
        return bitmapCache;
    }
}
