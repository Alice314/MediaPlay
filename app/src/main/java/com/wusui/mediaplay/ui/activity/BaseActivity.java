package com.wusui.mediaplay.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wusui.mediaplay.R;
import com.wusui.mediaplay.ui.adapter.MyFragmentPagerAdapter;

/**
 * Created by fg on 2016/5/14.
 */
public class BaseActivity extends AppCompatActivity{

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initTabLayout();

    }

    private void initTabLayout() {
        mPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewPager = (ViewPager) findViewById(R.id.vPager);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);

        mToolbar.setTitle("Backdrops");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
    }
}


