package com.xy.lifemanage.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.xy.lifemanage.view.LoginView;

/**
 * Created by nemo on 2016/5/9 0009.
 */
public class ToastUtils {
    public static void toast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static void progress(ProgressDialog pd, Context context, String str) {
        pd.setTitle("提示");
        pd.setMessage(str);
        pd.show();
    }

    public static void dialog(final Activity activity, String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(activity.getApplicationContext(), LoginView.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
