package com.bishedemo.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fang on 2016/12/12.
 */

public class SDHelper {
    public static String newPath;

    //得到某一个文件的详细数据
    public static String getAFileData(String fileName) {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            // 得到一个路径，内容是sdcard的文件夹路径和名字
            newPath = sdcardDir.getPath() + "/biSheNotes/";// newPath在程序中要声明
           String s = "";
            byte[] bytes = new byte[1024];
            try {
                FileInputStream fis = new FileInputStream(newPath+fileName);
                while (fis.read(bytes) != -1) {
                    s = s + String.valueOf(bytes);
                }
                System.out.println("___________"+s);
                return s;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }




    //得到文件夹下所有文件的名字
    public static List<String> getFilesData() {
        List<String> listData = new ArrayList<>();
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            // 创建一个文件夹对象，赋值为外部存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            // 得到一个路径，内容是sdcard的文件夹路径和名字
            newPath = sdcardDir.getPath() + "/biSheNotes/";// newPath在程序中要声明
            File path1 = new File(newPath);
            if (path1.isDirectory()) {  //判断此目录是否存在
                int length = path1.listFiles().length;
                if (length > 0) {   //若存在，判断是否有文件
                    for (int i=0; i<path1.list().length; i++) {
                        listData.add(path1.list()[i]);
                    }
                }
            }
        }
        return listData;
    }


    // 然后创建文件夹
    public static void createSDCardDir() {
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

    public static void writeStringToTxt(String filename, String content) {

        try {
            File file = new File(newPath + filename);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(content);// 往文件里写入字符串
            System.out.println("write OK!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //判断文件是否存在
    public static boolean isFileExit(String filename) {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            newPath = sdcardDir.getPath() + "/biSheNotes/";// newPath在程序中要声明
            File path1 = new File(newPath);
            if (path1.isDirectory()) {  //判断此目录是否存在
                File file = new File(filename);
                if (file.exists()) {
                    return true;
                }
            }
        }
        return false;
    }
}
