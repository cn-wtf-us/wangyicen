package com.feicui.edu.housekeeper.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.feicui.edu.housekeeper.entity.TelclassInfo;
import com.feicui.edu.housekeeper.entity.TelnumberInfo;
import com.feicui.edu.housekeeper.utils.LogUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class DBReader {
    public static File telFile;

    static {
        String dbFileDir = "data/data/commonnum.db";

        File fileDir = new File(dbFileDir);
        fileDir.mkdirs();
        telFile = new File(dbFileDir, "commonnum.db");
        LogUtil.d("DBReader", "telFile Dir path:" + dbFileDir);
    }

    public static boolean isExistsTeldbFile() {
        File toFile = DBReader.telFile;
        if (!toFile.exists() || toFile.length() <= 0) {
            return false;
        }
        return true;
    }
    public static ArrayList<TelclassInfo> readTeldbClasslist() throws Exception{
        ArrayList<TelclassInfo> classlistInfos =new ArrayList<TelclassInfo>();
        //打开DB文件
        SQLiteDatabase db = null;
        //执行查询的SQL语句select * from classlist
        Cursor cursor = null;
        try {
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            cursor = db.rawQuery("select * from classlist", null);
            LogUtil.d("DBRead", "read teldb classlist size:" + cursor.getCount());
            if (cursor.moveToFirst()){
                do {
                    String name =cursor.getString(cursor.getColumnIndex("name"));
                    int idx = cursor.getInt(cursor.getColumnIndex("idx"));
                    TelclassInfo classlistInfo =new TelclassInfo(name, idx);
                    classlistInfos.add(classlistInfo);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            throw e;
        }finally {
            try {
                cursor.close();
                db.close();
            }catch (Exception e2){
                throw e2;
            }
            LogUtil.d("DBRead", "read teldb classlist end [list size]" + classlistInfos.size());
        }
        return classlistInfos;
    }

    public static ArrayList<TelnumberInfo> readTeldbTable(int idx){
        ArrayList<TelnumberInfo> numberInfos = new ArrayList<TelnumberInfo>();
        String sql = "select * from table" + idx;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            //打开db文件
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            cursor = db.rawQuery(sql, null);
            LogUtil.d("DBRead", "read teldb number table size:" + cursor.getCount());
            if (cursor.moveToFirst()){
                do {
                    String name =cursor.getString(cursor.getColumnIndex("name"));
                    String number = cursor.getString(cursor.getColumnIndex("number"));
                    TelnumberInfo numberInfo =new TelnumberInfo(name, number);
                    numberInfos.add(numberInfo);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            throw e;
        }finally {
            try {
                cursor.close();
                db.close();
            }catch (Exception e2){
                throw e2;
            }
            LogUtil.d("DBRead", "read teldb number table end [list size]" + numberInfos.size());
        }
        return numberInfos;
    }

}
