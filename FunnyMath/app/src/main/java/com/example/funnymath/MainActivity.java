package com.example.funnymath;

import com.data.ResolveDigitalData;
import com.function.AddActivity;
import com.function.CentiMeterActivity;
import com.function.CountAngleActivity;
import com.function.InEquationActivity;
import com.function.MultiplyActivity;
import com.function.ResolveDigitalActivity;
import com.function.TableActivity;
import com.function.VerticalFormulaActivity;
import com.ui.BaseActivity;
import com.ui.CircleLayout;
import com.ui.CircleLayout.OnItemClickListener;
import com.ui.CircleLayout.OnItemSelectedListener;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends BaseActivity implements
        OnItemSelectedListener, OnItemClickListener, OnClickListener {

    private RelativeLayout relbtn;
    private CircleLayout circleMenu;
    private ImageView img1,img2,img3,img4,img5,img6,img7,img8;
    private ImageView imageView_setting, imageView_us;

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent;
        switch (v.getId()) {
            case R.id.relbtn:
                break;
            case R.id.img1:
                intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
                break;
            case R.id.img2:
                intent = new Intent(getApplicationContext(), MultiplyActivity.class);
                startActivity(intent);
                break;
            case R.id.img3:
                intent = new Intent(getApplicationContext(), CentiMeterActivity.class);
                startActivity(intent);
                break;
            case R.id.img4:
                intent = new Intent(getApplicationContext(), InEquationActivity.class);
                startActivity(intent);
                break;
            case R.id.img5:
                intent = new Intent(getApplicationContext(), ResolveDigitalActivity.class);
                startActivity(intent);
                break;
            case R.id.img6:
                intent = new Intent(getApplicationContext(), VerticalFormulaActivity.class);
                startActivity(intent);
                break;
            case R.id.img7:
                intent = new Intent(getApplicationContext(), CountAngleActivity.class);
                startActivity(intent);
                break;
            case R.id.img8:
                intent = new Intent(getApplicationContext(), TableActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_setting:
                intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_us:
                intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position, long id, String name) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onItemSelected(View view, int position, long id, String name) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setView() {
        // TODO Auto-generated method stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        circleMenu = (CircleLayout) findViewById(R.id.main_circle_layout);
        relbtn = (RelativeLayout) findViewById(R.id.relbtn);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);
        img7 = (ImageView) findViewById(R.id.img7);
        img8 = (ImageView) findViewById(R.id.img8);
        imageView_setting = (ImageView) findViewById(R.id.imageView_setting);
        imageView_us = (ImageView) findViewById(R.id.imageView_us);
    }

    @Override
    public void setListener() {
        // TODO Auto-generated method stub
        circleMenu.setOnItemSelectedListener(this);
        circleMenu.setOnItemClickListener(this);
        relbtn.setOnClickListener(this);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        img7.setOnClickListener(this);
        img8.setOnClickListener(this);
        imageView_setting.setOnClickListener(this);
        imageView_us.setOnClickListener(this);
    }

}
