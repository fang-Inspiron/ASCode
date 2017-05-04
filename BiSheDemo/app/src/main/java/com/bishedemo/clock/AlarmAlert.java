package com.bishedemo.clock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.bishedemo.R;

import java.io.IOException;

/**
 * Created by fang on 2017/5/3.
 */

public class AlarmAlert extends Activity {
    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri ringUri = Uri.parse(getIntent().getStringExtra("ringUri"));
        try {
            mp.setDataSource(this, ringUri);
            mp.setLooping(true);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new AlertDialog.Builder(AlarmAlert.this)
                .setIcon(R.drawable.ic_clock_strip_desk_clock)
                .setTitle("闹钟响了!")
                .setMessage("快完成你制定的计划吧!!!")
                .setPositiveButton("关掉它",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                AlarmAlert.this.finish();
                                mp.stop();
                            }
                        })
                .show();


    }
}
