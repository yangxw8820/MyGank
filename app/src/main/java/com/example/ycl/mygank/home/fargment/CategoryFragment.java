package com.example.ycl.mygank.home.fargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.example.ycl.mygank.Config;
import com.example.ycl.mygank.R;
import com.example.ycl.mygank.api.API;
import com.example.ycl.mygank.bean.DataInfo;
import com.example.ycl.mygank.home.DetailActivity;
import com.example.ycl.mygank.home.adapter.CategoryAdapter;
import com.example.ycl.mygank.rx.ObserverImp1;
import com.example.ycl.mygank.widget.DividerItemDecoration;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by YCL on 2016/6/12.
 */
public class CategoryFragment extends Fragment {

    private static final String PARAM1 = "title";

    private static final int PAGE_SIZE = 20;

    private String title;
    private int page = 1;

    private SwipeRefreshLayout swipe;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;


    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private CategoryAdapter adapter;

    private DataInfo info;

    private boolean isLoadingMore = false;

    public static CategoryFragment newInstance(CharSequence title) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(PARAM1, title.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            title = getArguments().getString(PARAM1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData(page);
            }
        };
        swipe.setOnRefreshListener(onRefreshListener);

        rv = (RecyclerView) view.findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new CategoryAdapter(null);
        adapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                DataInfo.Results results = adapter.getDataFromPosition(position);
                DetailActivity.open(getActivity(), results.getUrl());
            }
        });
        rv.setAdapter(adapter);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 && dy > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    int position = layoutManager.findLastVisibleItemPosition();
                    int count = adapter.getItemCount();
                    if (!isLoadingMore && position + 1 >= count) {
                        loadData(page + 1);
                    }
                }

            }
        });

        autoRefresh();
    }

    private void autoRefresh() {
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                onRefreshListener.onRefresh();
            }
        });
    }

    private void loadData(final int pageT) {
        isLoadingMore = true;
        API.PROXY.data(title, PAGE_SIZE, pageT)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ObserverImp1<String>() {
                    @Override
                    public void onNext(String s) {
                        if (swipe.isRefreshing()) {
                            swipe.setRefreshing(false);
                        }
                        isLoadingMore = false;

                        info = JSON.parseObject(s, DataInfo.class);
                        if (!info.isError()) {
                            page = pageT;
                            if (pageT == 1) {
                                adapter.notifyDataRefresh(info.getResults());
                            } else {
                                adapter.notifyDataMore(info.getResults());
                            }
                        } else {
                            // 错误处理
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (swipe.isRefreshing()) {
                            swipe.setRefreshing(false);
                        }
                        isLoadingMore = false;
                    }
                });
    }

}
