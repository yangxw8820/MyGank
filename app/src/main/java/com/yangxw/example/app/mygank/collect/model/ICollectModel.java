package com.yangxw.example.app.mygank.collect.model;

import com.yangxw.example.app.mygank.bean.DataInfo;

import rx.Observable;

/**
 * Created by YCL on 2016/8/1.
 */

public interface ICollectModel {

    Observable<DataInfo> query(int pageSize, int page);

}
