package com.yangxw.example.app.mygank.collect;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.yangxw.example.app.mygank.R;
import com.yangxw.example.app.mygank.base.BaseFragment;
import com.yangxw.example.app.mygank.base.adapter.BaseRecyclerViewAdapter;
import com.yangxw.example.app.mygank.bean.DataResultInfo;
import com.yangxw.example.app.mygank.collect.adapter.CollectAdapter;
import com.yangxw.example.app.mygank.collect.pressenter.CollectPresenter;
import com.yangxw.example.app.mygank.collect.view.ICollectView;
import com.yangxw.example.app.mygank.detail.DetailActivity;
import com.yangxw.example.app.mygank.widget.DividerItemDecoration;

import java.util.List;

public class CollectFragment extends BaseFragment implements ICollectView {

    private CollectPresenter presenter;
    private boolean isLoadMore = false;

    private android.support.v7.widget.RecyclerView rv;
    private android.support.v4.widget.SwipeRefreshLayout swipeRL;

    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private CollectAdapter adapter;
    private LinearLayoutManager layoutManager;

    public static CollectFragment newInstance() {
        Bundle args = new Bundle();
        CollectFragment fragment = new CollectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CollectFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getActivity().setTitle(R.string.title_activity_collect);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collect, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.swipeRL = (SwipeRefreshLayout) view.findViewById(R.id.swipeRL);
        this.rv = (RecyclerView) view.findViewById(R.id.rv);

        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.load();
            }
        };
        swipeRL.setOnRefreshListener(refreshListener);

        layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new CollectAdapter(null);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                DetailActivity.open(getContext(), adapter.getDataFromPosition(position));
            }
        });
        rv.setAdapter(adapter);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && dy > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    int position = layoutManager.findLastVisibleItemPosition();
                    int count = adapter.getItemCount();
                    if (position + 1 >= count) {
                        presenter.loadMore();
                        return;
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        presenter = new CollectPresenter(this);

        presenter.load();
    }

    @Override
    public void startLoading() {
        swipeRL.post(new Runnable() {
            @Override
            public void run() {
                swipeRL.setRefreshing(true);
            }
        });
    }

    @Override
    public boolean canLoadMore() {
        return !isLoadMore;
    }

    @Override
    public void startLoadMore() {
        isLoadMore = true;
    }

    @Override
    public void loadSuccess(List<DataResultInfo> list) {
        adapter.notifyDataRefresh(list);

        swipeRL.setRefreshing(false);
    }

    @Override
    public void loadFail(String msg) {

        swipeRL.setRefreshing(false);
    }

    @Override
    public void loadMoreSuccess(List<DataResultInfo> list) {
        adapter.notifyDataMore(list);

        swipeRL.setRefreshing(false);
        isLoadMore = false;
    }

    @Override
    public void loadMoreFail(String msg) {

        swipeRL.setRefreshing(false);
        isLoadMore = false;
    }

}
