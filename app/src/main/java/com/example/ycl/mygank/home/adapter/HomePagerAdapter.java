package com.example.ycl.mygank.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ycl.mygank.Config;
import com.example.ycl.mygank.home.fargment.CategoryFragment;

/**
 * Created by YCL on 2016/6/12.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private static final String[] TITLES = Config.CATEGORYS;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return CategoryFragment.newInstance(getPageTitle(position));
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
