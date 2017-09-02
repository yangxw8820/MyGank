package com.yangxw.example.app.mygank.home.model;

import com.yangxw.example.app.mygank.bean.DataInfo;

import rx.Observable;

/**
 * Created by YCL on 2016/6/15.
 */
public interface ICategoryModel {

    Observable<DataInfo> data(String classify, int pageSize, int page);

}
