package com.offlinecache;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.offlinecache.fragment.MeFragment;
import com.offlinecache.fragment.SaveFragment;
import com.offlinecache.fragment.LocalityFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg_main_menu;
    private RadioButton rb_save;
    private RadioButton rb_locality;
    private RadioButton rb_me;

    private ViewPager vp_main_tab;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private Fragment fragment_save;
    private Fragment fragment_locality;
    private Fragment fragment_me;
    private RelativeLayout animLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findID();
        initAdapter();
        initViewPager();
    }

    private void findID() {
        rg_main_menu = (RadioGroup) findViewById(R.id.rg_main_menu);
        rb_save = (RadioButton) findViewById(R.id.rb_save);
        rb_locality = (RadioButton) findViewById(R.id.rb_locality);
        rb_me = (RadioButton) findViewById(R.id.rb_me);
        vp_main_tab = (ViewPager) findViewById(R.id.vp_main_tab);
        animLayout = (RelativeLayout) findViewById(R.id.anim);

        fragment_save = new SaveFragment();
        fragment_locality = new LocalityFragment();
        fragment_me = new MeFragment();

        mFragments.add(fragment_save);
        mFragments.add(fragment_locality);
        mFragments.add(fragment_me);
    }

    public void initAdapter() {
        FragmentManager fm = getSupportFragmentManager();
        mAdapter = new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            //初始化每个选项卡
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }
        };

    }

    public void initViewPager() {
        vp_main_tab.setAdapter(mAdapter);
        vp_main_tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rb_save.setChecked(true);
                        break;
                    case 1:
                        rb_locality.setChecked(true);
                        break;
                    case 2:
                        rb_me.setChecked(true);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg_main_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_save:
                        vp_main_tab.setCurrentItem(0);
                        break;
                    case R.id.rb_locality:
                        vp_main_tab.setCurrentItem(1);
                        break;
                    case R.id.rb_me:
                        vp_main_tab.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
