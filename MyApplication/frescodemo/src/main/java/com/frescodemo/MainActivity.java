package com.frescodemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.simpleDraweeView);
        simpleDraweeView.setImageURI(Uri.parse("http://pic14.nipic.com/20110511/2457331_231004995000_2.jpg"));
    }
}
