package com.picassodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by fang on 2016/7/29.
 */
public class GridImageActivity extends Activity {

    private ImageAdapter adapter;
    private GridView gridView;

    //用来存储我们需要用到的18个Url地址
    private ArrayList<String> urls=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        gridView= (GridView) findViewById(R.id.grid_view);
        AddUrl();

        adapter=new ImageAdapter(GridImageActivity.this,urls);
        gridView.setAdapter(adapter);

        //为每个网格添加单击事件监听，单击后跳转到图片展示页面MainActivity
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(GridImageActivity.this,MainActivity.class);

                //我们需要传递这张图片的Url地址给MainActivity
                intent.putExtra("imageUrl",urls.get(i));
                startActivity(intent);
            }
        });

    }

    private void AddUrl() {
        //图片Url地址
        urls.add("http://img4.imgtn.bdimg.com/it/u=3736756256,2327515518&fm=21&gp=0.jpg");
        urls.add("http://pic1.nipic.com/20090324/1987406_182134086_2.jpg");
        urls.add("http://p14.go007.com/2014_07_24_18/767dc54e97f62f3f_2.jpg");
        urls.add("http://pic.house365.com/newcms/2013/01/11/135788341050efa812a8118.jpg");
        urls.add("http://pic21.nipic.com/20120524/10166175_212830563178_2.jpg");
        urls.add("http://pic64.nipic.com/file/20150331/12993780_190456066000_2.jpg");
        urls.add("http://pic2.58.com/zp_images/allimg/130510/12_130510105326_1.png");
        urls.add("http://www.51pinwei.com/uploads/allimg/140417/1322-14041G13121638.jpg");
        urls.add("http://www.46ps.com/uploadfile/2012/0214/20120214050531365.jpg");
        urls.add("http://pic2.58.com/zp_images/allimg/130423/12_130423111522_1.png");

        urls.add("http://a.hiphotos.baidu.com/zhidao/pic/item/f9dcd100baa1cd11aa2ca018bf12c8fcc3ce2d74.jpg");
        urls.add("http://img.taopic.com/uploads/allimg/110914/8879-11091422541844.jpg");
        urls.add("http://image6.huangye88.com/2013/03/28/2a569ac6dbab1216.jpg");
        urls.add("http://img.taopic.com/uploads/allimg/121005/219049-1210051TK021.jpg");
        urls.add("http://img.article.pchome.net/00/39/46/24/pic_lib/wm/001.jpg");
        urls.add("http://img02.tooopen.com/Downs/images/2010/7/30/sy_20100730095420104065.jpg");
        urls.add("http://xs3.op.xywy.com/api.iu1.xywy.com/cms/20160615/d5556b6663168f6b4a3afce1cb524a5526196.jpg");
        urls.add("http://easyread.ph.126.net/3QcuFrUjSJKcY3PrOnMKtQ==/7917013684595940630.jpg");
    }
}