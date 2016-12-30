package com.bishedemo.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by fang on 2016/12/12.
 */

public class SDHelper {
    public Context mContext;
    private String content;
    public String newPath;
    public List<String> listPath;
    public static int number = 1;

    public SDHelper(Context context, String content) {
        this.mContext = context;
        this.content = content;
    }

    // 首先判断SD卡是否插入
    public String getSDPath() {
        File SDdir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            SDdir = Environment.getExternalStorageDirectory();
        }
        if (SDdir != null) {
            return SDdir.toString();
        } else {
            return null;
        }
    }

    // 然后创建文件夹
    public void createSDCardDir() {
        if (getSDPath() == null) {
            Toast.makeText(mContext, "未找到SD卡", Toast.LENGTH_SHORT).show();
        } else {
            if (Environment.MEDIA_MOUNTED.equals(Environment
                    .getExternalStorageState())) {
                // 创建一个文件夹对象，赋值为外部存储器的目录
                File sdcardDir = Environment.getExternalStorageDirectory();
                // 得到一个路径，内容是sdcard的文件夹路径和名字
                newPath = sdcardDir.getPath() + "/biSheNotes/";// newPath在程序中要声明
                File path1 = new File(newPath);
                if (!path1.exists()) {
                    // 若不存在，创建目录，可以在应用启动的时候创建
                    path1.mkdirs();
                    System.out.println("paht ok,path:" + newPath);
                }
            } else {
                System.out.println("false");
            }
        }
    }

    public void writeStringToTxt() {
        try {
            File file = new File(newPath+(number++)+".txt");
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(content);// 往文件里写入字符串
            System.out.println("write OK!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
