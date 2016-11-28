package com.qnote.application;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qnote.application.crop.view.CropFragment;
import com.qnote.application.doc.view.DocFragment;
import com.qnote.application.mine.view.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewPage;
    private List<Fragment> pageList;
    private RadioGroup mRadioGroup;
    private RadioButton mineRB;
    private RadioButton cropRB;
    private RadioButton docRB;
    private DocFragment docFragment;
    private CropFragment cropFragment;
    private MineFragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        pageList=new ArrayList<Fragment>();
        docFragment=new DocFragment();
        cropFragment=new CropFragment();
        mineFragment=new MineFragment();
        pageList.add(docFragment);
        pageList.add(cropFragment);
        pageList.add(mineFragment);
        mViewPage.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPage.addOnPageChangeListener(new PageChangeListener());
        initTabRG();


    }

    private void initView() {
        mViewPage = (ViewPager) findViewById(R.id.viewPager);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        docRB = (RadioButton) findViewById(R.id.tab_doc);
        cropRB = (RadioButton) findViewById(R.id.tab_crop);
        mineRB = (RadioButton) findViewById(R.id.tab_mine);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int postion) {
            return pageList.get(postion);
        }

        @Override
        public int getCount() {
            return pageList.size();
        }

    }
    private void initTabRG() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_doc:
                        mViewPage.setCurrentItem(0);
                        break;
                    case R.id.tab_crop:
                        mViewPage.setCurrentItem(1);
                        break;
                    case R.id.tab_mine:
                        mViewPage.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int postion) {
            switch (postion) {
                case 0:
                    docRB.setChecked(true);
                    break;
                case 1:
                    cropRB.setChecked(true);
                    break;
                case 2:
                    mineRB.setChecked(true);
                    break;
            }
        }

    }

}
