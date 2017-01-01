package com.bishedemo.function;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.bishedemo.R;
import com.bishedemo.UniversalActivity;
import com.bishedemo.utils.AlarmBroadcastReceiver;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by fang on 2016/11/15.
 */



public class ClockActivity extends UniversalActivity implements View.OnClickListener{

    private static final String TAG = "AlarmActivity";
    AlarmManager alarmManager;
    Calendar calendar = Calendar.getInstance(Locale.CHINESE);
    Button setTime;
    Button setRing;
    Button setOver;
    Uri ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_test);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        setTime = (Button) findViewById(R.id.setTime);
        setRing = (Button) findViewById(R.id.setRing);
        setOver = (Button) findViewById(R.id.setOver);

        setTime.setOnClickListener(this);
        setRing.setOnClickListener(this);
        setOver.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setTime: setTime();break;
            case R.id.setRing: setRingtone();break;
            case R.id.setOver: setAlarm(calendar);break;

        }
    }

    //启动闹玲，设置闹玲
    private void setAlarm(Calendar calendar){
        Intent intent = new Intent();
        intent.setClass(this, AlarmBroadcastReceiver.class);
        intent.putExtra("msg", "Get up!Get up!");
        intent.putExtra("ringURI", ringUri.toString());
        Log.d(TAG, ringUri.toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pendingIntent);
    }
    //设置时间
    private void setTime(){
        Date date = new Date();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                calendar.set(Calendar.HOUR,hour);
                calendar.set(Calendar.MINUTE,minute);
            }
        }, hour, minute, true).show();
    }
    //设置闹玲铃声
    private void setRingtone(){
        Intent intent = new Intent();
        intent.setAction(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, false);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置闹玲铃声");
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALL);
        Uri pickedUri = RingtoneManager.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_ALARM);
        if (pickedUri!=null) {
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,pickedUri);
            ringUri = pickedUri;
        }
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!=RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 1:
                //获取选中的铃声的URI
                Uri pickedURI = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                Log.i(TAG,pickedURI.toString());
                RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_ALARM, pickedURI);
                getName(RingtoneManager.TYPE_ALARM);
                break;
            default:
                break;
        }
    }
    private void getName(int type){
        Uri pickedUri = RingtoneManager.getActualDefaultRingtoneUri(this, type);
        Log.i(TAG,pickedUri.toString());
        Cursor cursor = this.getContentResolver().query(pickedUri, new String[]{MediaStore.Audio.Media.TITLE}, null, null, null);
        if (cursor!=null) {
            if (cursor.moveToFirst()) {
                String ring_name = cursor.getString(0);
                Log.i(TAG,ring_name);
                String[] c = cursor.getColumnNames();
                for (String string : c) {
                    Log.i(TAG,string);
                }
            }
            cursor.close();
        }
    }


}