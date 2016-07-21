package com.example.funnymath;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

/**
 * Created by jianfang on 2016/4/5.
 */
public class SettingActivity extends Activity {

    private SeekBar voice_seekBar;
    private RadioGroup voice_radioGroup;
    private RadioButton boy_radioButton;
    private RadioButton girl_radioButton1;
    private RadioButton girl_radioButton2;
    private EditText editText_phone;
    private EditText editText_email;

    private int currentVoice;
    private int maxVoice;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findID();
        getSystemVoice();
    }

    private void getSystemVoice() {
        audioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        maxVoice = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        currentVoice = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        System.out.println("RING" + " max :" + maxVoice + " current : " + currentVoice);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dialog();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void dialog() {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingActivity.this);

        mBuilder.setMessage("完成设置了吗？");
        mBuilder.setTitle("提示ʾ");

        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                saveChange();
//                Intent mIntent = new Intent();
//                mIntent.setClass(SettingActivity.this, MiLaucherActivity.class);
//                startActivity(mIntent);
                finish();
            }
        });
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        mBuilder.create().show();
    }

    private void findID() {
        voice_seekBar = (SeekBar) findViewById(R.id.voice_seekBar);
        voice_radioGroup = (RadioGroup) findViewById(R.id.voice_radioGroup);
        boy_radioButton = (RadioButton) findViewById(R.id.boy_radioButton);
        girl_radioButton1 = (RadioButton) findViewById(R.id.girl_radioButton1);
        girl_radioButton2 = (RadioButton) findViewById(R.id.girl_radioButton2);
        voice_seekBar.setProgress(10);
    }
}
