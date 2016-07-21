package com.example.universal_imageloader;

import android.app.Activity;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by jianfang on 2016/4/24.
 */
public class BaseActivity extends Activity {
    protected ImageLoader imageLoader = ImageLoader.getInstance();
}
