package com.bishedemo.note;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import com.bishedemo.R;

/**
 * Created by fang on 2016/11/20.
 */

public class WritePadDialog extends Dialog implements View.OnClickListener{

    private Context mContext;
    private WriteDialogListener mWriteDialogListener;
    private PaintView mPaintView;
    private FrameLayout mFrameLayout;
    private Button mBtnOK, mBtnClear, mBtnCancel;

    public WritePadDialog(Context context, WriteDialogListener writeDialogListener) {
        super(context);
        this.mContext = context;
        this.mWriteDialogListener = writeDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //无标题
        setContentView(R.layout.write_pad);
        initView();
    }

    private void initView() {
        mFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        mBtnOK = (Button) findViewById(R.id.write_pad_ok);
        mBtnCancel = (Button) findViewById(R.id.write_pad_cancel);
        mBtnClear = (Button) findViewById(R.id.write_pad_clear);

        // 获取屏幕尺寸
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int screenWidth = mDisplayMetrics.widthPixels;
        int screenHeight = mDisplayMetrics.heightPixels;
        mPaintView = new PaintView(mContext, screenWidth, dip2px(getContext(),300));
        mFrameLayout.addView(mPaintView);
        mPaintView.requestFocus();

        mBtnOK.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnClear.setOnClickListener(this);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.write_pad_ok:
                if(mPaintView.getPath().isEmpty()) {
                    return;
                }
                mWriteDialogListener.onPaintDone(mPaintView.getPaintBitmap());
                dismiss();
                break;
            case R.id.write_pad_cancel:
                cancel();
                break;
            case R.id.write_pad_clear:
                mPaintView.clear();
                break;
        }
    }
}
