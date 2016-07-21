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
            //将直接连接URL获取网页内容。
            Document doc = Jsoup.connect(url).get();
            //使用选择器查找特定的数据
            System.out.println("77777"+doc.title());
            System.out.println("99999"+doc.html());

            String path = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/CacheApp/";
            File newPath = new File(path);
            if (!newPath.exists()) {
                // 若不存在，创建目录，可以在应用启动的时候创建
                newPath.mkdirs();
                System.out.println("paht ok,path:" + path);
            }
            System.out.println("前MainActivity.num:"+MainActivity.num);
            String htmlName = "file"+ MainActivity.num+".html";
            MainActivity.num++;
            System.out.println("后MainActivity.num:"+MainActivity.num);
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
