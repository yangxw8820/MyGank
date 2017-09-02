package com.yangxw.example.app.mygank.home.fargment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.ycl.mygank.R;
import com.yangxw.example.app.mygank.base.BaseFragment;
import com.yangxw.example.app.mygank.db.CategoryDB;
import com.yangxw.example.app.mygank.home.adapter.HomePagerAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment {

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private HomePagerAdapter adapter;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getActivity().setTitle(R.string.app_name);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        CategoryDB.getInstance().close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        this.pager = (ViewPager) view.findViewById(R.id.pager);
        adapter = new HomePagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }

}
