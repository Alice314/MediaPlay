package com.wusui.mediaplay.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wusui.mediaplay.ui.fragment.GangandTaiFragment;
import com.wusui.mediaplay.ui.fragment.InlandFragment;
import com.wusui.mediaplay.ui.fragment.WesternFragment;

/**
 * Created by fg on 2016/5/14.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public final int COUNT = 3;
    private String[] titles = new String[]{"本地", "欧美", "港台"};
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: return new InlandFragment();
            case 1: return new WesternFragment();
            case 2: return new GangandTaiFragment();
            default: return new InlandFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
