package com.feicui.edu.housekeeper.db;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class AssetsDBManager {
    private static final String TAG = "AssetsDBManager";

    public static void copyAssetsFileToFile(Context context,String from,String to) throws IOException {

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        AssetManager am = context.getAssets();

        try {
            InputStream is = am.open(from);
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(new FileOutputStream(to));

            int x;
            byte[] b = new byte[1024 * 2];
            while((x = bis.read(b)) != -1){
                bos.write(b, 0, x);
            }
            bos.flush();

        } catch (IOException e) {
            throw e;
        }finally{
            if (bis != null){
                bis.close();
            }
            if (bos != null){
                bos.close();
            }
        }

    }
}
