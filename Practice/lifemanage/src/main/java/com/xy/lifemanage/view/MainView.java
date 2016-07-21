package com.xy.lifemanage.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xy.lifemanage.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nemo on 2016/5/10 0010.
 */
public class MainView extends AppCompatActivity{

    public final static String TAG = "MainView";
    private long mExitTime;
    /**
     * 主界面的viewpager
     */
    private ViewPager vp_main_tab = null;
    private FragmentPagerAdapter mAdapter = null;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    /**
     * 底部的四个radiobutton
     */
    private RadioButton rb_main_tab_menu1 = null;
    private RadioButton rb_main_tab_menu2 = null;
    private RadioButton rb_main_tab_menu3 = null;

    private RadioGroup rg_main_menu = null;

    private ProjectFragment projectFragment;
    private OrganizeFragment organizeFragment;
    private MyFragment myFragment;

    public static Map<String,Object> data = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_main);
        findViews();
        initAdapter();
        initViewPager();
    }

    private void findViews() {
        rb_main_tab_menu1 = (RadioButton) findViewById(R.id.rb_main_tab_menu1);
        rb_main_tab_menu2 = (RadioButton) findViewById(R.id.rb_main_tab_menu2);
        rb_main_tab_menu3 = (RadioButton) findViewById(R.id.rb_main_tab_menu3);
        rg_main_menu = (RadioGroup) findViewById(R.id.rg_main_menu);
        vp_main_tab = (ViewPager) findViewById(R.id.vp_main_tab);
        projectFragment = new ProjectFragment();
        organizeFragment = new OrganizeFragment();
        myFragment = new MyFragment();

        mFragments.add(projectFragment);
        mFragments.add(organizeFragment);
        mFragments.add(myFragment);
    }


    private void initAdapter() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        mAdapter = new FragmentPagerAdapter(fm) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public android.support.v4.app.Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }

            // 初始化每个页卡选项
            @Override
            public Object instantiateItem(ViewGroup arg0, int arg1) {
                return super.instantiateItem(arg0, arg1);
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                super.destroyItem(container, position, object);
            }
        };
    }

    private void initViewPager() {
        vp_main_tab.setAdapter(mAdapter);
        vp_main_tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rb_main_tab_menu1.setChecked(true);
                        break;
                    case 1:
                        rb_main_tab_menu2.setChecked(true);
                        break;
                    case 2:
                        rb_main_tab_menu3.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        rg_main_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup rg, int id) {
                switch (id) {
                    case R.id.rb_main_tab_menu1:
                        vp_main_tab.setCurrentItem(0);
                        break;
                    case R.id.rb_main_tab_menu2:
                        vp_main_tab.setCurrentItem(1);
                        break;
                    case R.id.rb_main_tab_menu3:
                        vp_main_tab.setCurrentItem(2);
                        break;
                    default:
                        break;
                }

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();

            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
