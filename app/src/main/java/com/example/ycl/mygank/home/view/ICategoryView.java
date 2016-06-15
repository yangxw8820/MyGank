package com.example.ycl.mygank.home.view;

import com.example.ycl.mygank.bean.DataInfo;

/**
 * Created by YCL on 2016/6/15.
 */
public interface ICategoryView {

//    boolean canRefresh();
    boolean canLoadMore();

    void startRefresh();
    void startLoadMore();

    void completedRefresh(DataInfo info);
    void completedLoadMore(DataInfo info);

    void completedMsg(boolean isError, String msg);

}
