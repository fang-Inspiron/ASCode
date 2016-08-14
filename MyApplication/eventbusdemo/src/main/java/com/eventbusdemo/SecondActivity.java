package com.eventbusdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.greenrobot.event.EventBus;


/**
 * Created by fang on 2016/8/14.
 */
public class SecondActivity extends Activity {

    private Button btn_FirstEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn_FirstEvent = (Button) findViewById(R.id.btn_first_event);

        btn_FirstEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FirstEvent("FirstEvent btn Clicked"));
            }
        });
    }

}
