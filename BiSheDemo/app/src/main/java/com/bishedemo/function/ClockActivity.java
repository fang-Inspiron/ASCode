package com.bishedemo.function;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bishedemo.R;
import com.bishedemo.UniversalActivity;
import com.bishedemo.clock.CallAlarm;

import java.io.File;
import java.util.Calendar;

/**
 * Created by fang on 2017/5/3.
 */

public class ClockActivity extends UniversalActivity {
    Button buttonTime;
    Button buttonRing;
    Button buttonDelete;
    TextView textTime;
    TextView textDelete;
    EditText et_set;

    long time;
    String time1String = null;
    String defalutString = "目前无设置";

    Calendar c = Calendar.getInstance();
    /* 自定义的类型 */
    public static final int CODE_ALARM = 1;
    /**
     * 闹钟铃声文件夹
     * /system/media/audio/alarms          系统闹钟铃声
     * /sdcard/music/alarms                用户闹钟铃声
     */
    private String strAlarmFolder = "/system/media/audio/alarms";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_main);

        //取得活动的Preferences对象
        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        time1String = settings.getString("TIME1", defalutString);

        findId();
        InitButtonTime();
        InitButtonRing();
        InitButtonDelete();
        textDelete.setText(time1String);
    }

    public void InitButtonRing() {

        buttonRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasFolder(strAlarmFolder)) {
                    // 打开系统铃声设置
                    Intent intent = new Intent(
                            RingtoneManager.ACTION_RINGTONE_PICKER);
                    // 设置铃声类型和title
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
                            RingtoneManager.TYPE_ALARM);
                    intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE,
                            "设置闹钟铃声");
                    // 当设置完成之后返回到当前的Activity
                    startActivityForResult(intent, CODE_ALARM);
                }
            }
        });
    }

    /**
     * 当设置铃声之后的回调函数
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        // 得到我们选择的铃声
        Uri pickedUri = data
                .getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
        if (pickedUri != null) {
            switch (requestCode) {
                case CODE_ALARM:
                    // 将我们选择的铃声设置成为默认闹钟铃声
                    RingtoneManager.setActualDefaultRingtoneUri(this,
                            RingtoneManager.TYPE_ALARM, pickedUri);
                    break;
            }
        }


        Intent intent = new Intent(ClockActivity.this, CallAlarm.class);
        String set_thing = et_set.getText().toString();
        if (set_thing.equals("")) {
            set_thing = "快完成你制定的计划吧！";
        }
        intent.putExtra("setThing", set_thing);

        intent.putExtra("ringUri", pickedUri.toString());
        PendingIntent sender = PendingIntent.getBroadcast(ClockActivity.this, 0, intent, 0);
        AlarmManager am;
        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
        Toast.makeText(ClockActivity.this, "已设置完成！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 检测是否存在指定的文件夹,如果不存在则创建
     *
     * @param strFolder 文件夹路径
     */
    private boolean hasFolder(String strFolder) {
        boolean btmp = false;
        File f = new File(strFolder);
        if (!f.exists()) {
            if (f.mkdirs()) {
                btmp = true;
            } else {
                btmp = false;
            }
        } else {
            btmp = true;
        }
        return btmp;
    }

    public void InitButtonTime() {
        buttonTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                new TimePickerDialog(ClockActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.set(Calendar.SECOND, 0);
                                c.set(Calendar.MILLISECOND, 0);
                                time = c.getTimeInMillis();

                                String tmpS = format(hourOfDay) + "：" + format(minute);
                                textTime.setText(tmpS);
                                textDelete.setText(tmpS);

                                //SharedPreferences保存数据，并提交
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();

                                Toast.makeText(ClockActivity.this, "设置闹钟时间为" + tmpS,
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }, mHour, mMinute, true).show();
            }
        });

    }

    public void InitButtonDelete() {

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ClockActivity.this, CallAlarm.class);
                PendingIntent sender = PendingIntent.getBroadcast(
                        ClockActivity.this, 0, intent, 0);
                AlarmManager am;
                am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(ClockActivity.this, "闹钟时间删除",
                        Toast.LENGTH_SHORT).show();
                textDelete.setText("目前无设置");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "目前无设置");
                editor.commit();
            }
        });

    }

    private String format(int x) {
        String s = "" + x;
        if (s.length() == 1) s = "0" + s;
        return s;
    }

    private void findId() {
        buttonTime = (Button) findViewById(R.id.buttonTime);
        buttonRing = (Button) findViewById(R.id.buttonRing);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        textTime = (TextView) findViewById(R.id.textTime);
        textDelete = (TextView) findViewById(R.id.textDelete);
        et_set = (EditText) findViewById(R.id.et_set);
    }
}
