package com.bishedemo.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bishedemo.R;

/**
 * Created by fang on 2016/11/28.
 */

public class NewsDetailActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail_main);
        Intent intent = getIntent();
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(intent.getStringExtra("url"));
        setTitle(intent.getStringExtra("appTitle"));
    }
}
