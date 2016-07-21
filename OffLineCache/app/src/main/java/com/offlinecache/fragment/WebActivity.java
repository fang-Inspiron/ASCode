package com.offlinecache.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.offlinecache.MainActivity;
import com.offlinecache.R;

/**
 * Created by fang on 2016/7/20.
 */
public class WebActivity extends AppCompatActivity {
    private WebView webView;
    private int num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        num = intent.getIntExtra("num", 0);
        String htmlName = "file"+ MainActivity.num+".html";
        String path = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/CacheApp/";
        webView.loadUrl("file://" + path + htmlName);
    }
}
