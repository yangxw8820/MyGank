package com.yangxw.example.app.mygank.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.yangxw.example.app.mygank.Config;
import com.yangxw.example.app.mygank.home.fargment.CategoryFragment;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by YCL on 2016/6/12.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private static final String[] TITLES = Config.CATEGORIES;

    private Map<Integer, Fragment> fragments = new WeakHashMap<>();

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        if (fragment == null){
            fragment = CategoryFragment.newInstance(getPageTitle(position));
            fragments.put(position, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
