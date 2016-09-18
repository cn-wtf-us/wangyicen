package com.feicui.edu.housekeeper.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.feicui.edu.housekeeper.entity.TelclassInfo;
import com.feicui.edu.housekeeper.entity.TelnumberInfo;
import com.feicui.edu.housekeeper.base.utils.LogUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class DBReader {

    public static File file = new File("data/data/com.feicui.edu.housekeeper/commonnum.db/");
    //打开DB文件
    private static SQLiteDatabase db = null;
    //执行查询的SQL语句select * from classlist
    private static Cursor cursor = null;

    /*public static File telFile;
    static {
        String dbFileDir = "data/data/commonnum.db";

        File fileDir = new File(dbFileDir);
        fileDir.mkdirs();
        telFile = new File(dbFileDir, "commonnum.db");
        LogUtil.d("DBReader", "telFile Dir path:" + dbFileDir);
    }*/
    public static boolean isExistsTeldbFile() {
        if (file.exists() && file.length() > 0) {
            return true;
        }
        return false;
    }
    /*public static boolean isExistsTeldbFile() {
        File toFile = DBReader.file;
        if (!toFile.exists() || toFile.length() <= 0) {
            return false;
        }
        return true;
    }*/
    public static ArrayList<TelclassInfo> readTeldbClasslist() throws Exception {
        ArrayList<TelclassInfo> classlistInfos = new ArrayList<TelclassInfo>();
        if (isExistsTeldbFile()) {
            db = SQLiteDatabase.openOrCreateDatabase(file, null);
            cursor = db.rawQuery("select * from classlist", null);
            LogUtil.d("DBRead", "read teldb classlist size:" + cursor.getCount());
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int idx = cursor.getInt(cursor.getColumnIndex("idx"));
                TelclassInfo classlistInfo = new TelclassInfo(name, idx);
                classlistInfos.add(classlistInfo);
            }
            cursor.close();
            db.close();
            return classlistInfos;
        } else {
            LogUtil.d("DBRead", "数据库文件没有找到...");
            return null;
        }
    }
    public static ArrayList<TelnumberInfo> readTeldbTable(int idx) {
        ArrayList<TelnumberInfo> numberInfos = new ArrayList<TelnumberInfo>();
        String sql = "select * from table" + idx;
        if (isExistsTeldbFile()) {
            //打开db文件
            db = SQLiteDatabase.openOrCreateDatabase(file, null);
            cursor = db.rawQuery(sql, null);
            LogUtil.d("DBRead", "read teldb number table size:" + cursor.getCount());
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                TelnumberInfo numberInfo = new TelnumberInfo(name, number);
                numberInfos.add(numberInfo);
            }
            cursor.close();
            db.close();
            return numberInfos;
        } else {
            LogUtil.d("DBRead", "read teldb number table end [list size]" + numberInfos.size());
            return null;
        }
    }
}