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

    private String url;
    private Handler handler;

    public GetContentThread(String url, Handler handler) {
        this.url = url;
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        analyze();
    }

    public void analyze() {
        try {
            //给服务器保存一份数据




            //将直接连接URL获取网页内容。
            Document doc = Jsoup.connect(url).get();
            //使用选择器查找特定的数据
            String path = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/CacheApp/";
            File newPath = new File(path);
            if (!newPath.exists()) {
                // 若不存在，创建目录
                newPath.mkdirs();
            }
            String htmlName = "file"+ MainActivity.num+".html";
            MainActivity.num++;
            File file = new File(path + htmlName);
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.print(doc.html());
            pw.flush();
            pw.close();

            handler.sendEmptyMessage(100);
        } catch (IOException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(101);
        }

    }
}
