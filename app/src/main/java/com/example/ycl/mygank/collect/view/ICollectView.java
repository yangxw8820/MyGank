package com.example.ycl.mygank.collect.view;

import com.example.ycl.mygank.bean.DataResultInfo;

import java.util.List;

/**
 * Created by YCL on 2016/8/1.
 */
public interface ICollectView {

    boolean canLoadMore();

    void startLoading();
    void startLoadMore();

    void loadSuccess(List<DataResultInfo> list);
    void loadFail(String msg);

    void loadMoreSuccess(List<DataResultInfo> list);
    void loadMoreFail(String msg);

}
