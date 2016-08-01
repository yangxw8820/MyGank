package com.example.ycl.mygank.home.fargment;

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

import com.example.ycl.mygank.R;
import com.example.ycl.mygank.base.BaseFragment;
import com.example.ycl.mygank.bean.DataInfo;
import com.example.ycl.mygank.bean.DataResultInfo;
import com.example.ycl.mygank.detail.DetailActivity;
import com.example.ycl.mygank.home.adapter.CategoryAdapter;
import com.example.ycl.mygank.home.presenter.CategoryPresenter;
import com.example.ycl.mygank.home.view.ICategoryView;
import com.example.ycl.mygank.widget.DividerItemDecoration;

/**
 * Created by YCL on 2016/6/12.
 */
public class CategoryFragment extends BaseFragment implements ICategoryView {

    private static final String TITLE = "title";

    private String title;

    private SwipeRefreshLayout swipe;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;

    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private CategoryAdapter adapter;

    private boolean isLoadingMore = false;

    private CategoryPresenter presenter;

    public static CategoryFragment newInstance(CharSequence title) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
        }

        presenter = new CategoryPresenter(this);
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
                presenter.refresh(title);
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
                DataResultInfo result = adapter.getDataFromPosition(position);
                DetailActivity.open(getActivity(), result);
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
                        presenter.loadMore(title);
                        return;
                    }
                }

                super.onScrolled(recyclerView, dx, dy);
            }
        });

        presenter.refresh(title);
    }

    @Override
    public boolean canLoadMore() {
        return !isLoadingMore;
    }

    @Override
    public void startRefresh() {
//        swipe.setRefreshing(true);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
            }
        });
    }

    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    @Override
    public void completedRefresh(DataInfo info) {
        swipe.setRefreshing(false);
        adapter.notifyDataRefresh(info.getResults());
    }

    @Override
    public void completedLoadMore(DataInfo info) {
        isLoadingMore = false;
        adapter.notifyDataMore(info.getResults());
    }

    @Override
    public void completedMsg(boolean isError, String msg) {
        swipe.setRefreshing(false);
        isLoadingMore = false;
    }

}
