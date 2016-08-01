package com.example.ycl.mygank.collect.model;

import com.example.ycl.mygank.bean.DataInfo;

import rx.Observable;

/**
 * Created by YCL on 2016/8/1.
 */

public interface ICollectModel {

    Observable<DataInfo> query(int pageSize, int page);

}
