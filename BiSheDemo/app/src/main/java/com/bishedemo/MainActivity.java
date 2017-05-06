package com.bishedemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bishedemo.bean.Channel;
import com.bishedemo.fragment.NewsFragment;
import com.bishedemo.function.ClockActivity;
import com.bishedemo.function.LifeHelperActivity;
import com.bishedemo.function.SettingActivity;
import com.bishedemo.http.ApiUtils;
import com.bishedemo.note.ui.activity.NoteActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Channel> channelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final DrawerArrowDrawable indicator = new DrawerArrowDrawable(this);
        indicator.setColor(Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(indicator);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (((ViewGroup) drawerView).getChildAt(1).getId() == R.id.leftSideBar) {
                    indicator.setProgress(slideOffset);
                }
            }
        });

        //主页新闻：AppBar TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        channelList= ApiUtils.getChannelList();
        setupViewPager(viewPager, channelList);
    }

    private void setupViewPager(ViewPager viewPager, List<Channel> channelList) {
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

        for (int i = 0; i < channelList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("name", channelList.get(i).getChannelId());//"toutiao"根据id查询
            bundle.putString("appTitle", channelList.get(i).getName());//“头条”根据appTitle命名
            NewsFragment newsFragment = new NewsFragment();
            newsFragment.setArguments(bundle);
            adapter.addFragment(newsFragment,channelList.get(i).getName());
        }
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode (TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    static class MyAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitle = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitle.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return true;
    }

    public void onClick(View view) {
        if (view instanceof TextView) {
            String title = ((TextView) view).getText().toString();
            Intent intent = new Intent();
            if (title.equals("日记")) {
                intent.setClass(this, NoteActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            } else if (title.equals("闹钟")) {
                intent.setClass(this, ClockActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            } else if (title.equals("小助手")) {
                intent.setClass(this, LifeHelperActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            } else if (title.equals("设置")) {
                intent.setClass(this, SettingActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        } else if (view.getId() == R.id.userInfo) {
            startActivity(UniversalActivity.newIntent(this, "个人中心"));
        }
    }


}
