package com.offlinecache.http;

import android.os.Environment;
import android.os.Handler;

import com.offlinecache.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by fang on 2016/7/20.
 */
public class GetContentThread extends Thread{
    private Handler handler;
    private String url;
    private int num;

    public GetContentThread(Handler handler, String url, int num) {
        this.url = url;
        this.handler = handler;
        this.num = num;
    }

    @Override
    public void run() {
        super.run();
        analyze();
    }

    public void analyze() {
        try {
            //将直接连接URL获取网页内容。
            Document doc = Jsoup.connect(url).get();
            //使用选择器查找特定的数据
            String path = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/CacheApp/";
            File newPath = new File(path);
            if (!newPath.exists()) {
                newPath.mkdirs();// 若不存在，创建目录
            }
            String htmlName = "file"+ num+".html";
            File file = new File(path + htmlName);
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.print(doc.html());
            pw.flush();
            pw.close();

            handler.sendEmptyMessage(200);
        } catch (IOException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(201);
        }

    }
}
