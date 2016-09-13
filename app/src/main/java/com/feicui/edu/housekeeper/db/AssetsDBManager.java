package com.feicui.edu.housekeeper.db;

import android.content.Context;

import com.feicui.edu.housekeeper.utils.LogUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class AssetsDBManager {
    public static void copyAssetsFileToFile(Context context, String path, File toFile) throws IOException {

        LogUtil.d("AssetDBManager", "copyAssetsFileToFile start");
        LogUtil.d("AssetDBManager", "File path:" + path);
        LogUtil.d("AssetDBManager", "toFile path:" + toFile.getAbsolutePath());

        InputStream inputStream = null;
        //输入流
        BufferedInputStream bufferedInputStream = null;
        //输出流
        BufferedOutputStream bufferedOutputStream = null;

        try {
            inputStream = context.getAssets().open(path);
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(toFile, false));

            int length = 0;
            byte[] buff = new byte[2 * 1024];
            while ((length = bufferedInputStream.read(buff)) != -1){
                bufferedOutputStream.write(buff, 0, length);
            }
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            bufferedOutputStream.close();
            bufferedInputStream.close();
            inputStream.close();
            LogUtil.d("AssetDBManager", "copyAssetsFileToFile end");
        }

    }
}
