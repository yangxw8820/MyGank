package com.example.ycl.mygank.home.model;

import com.example.ycl.mygank.bean.DataInfo;

import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by YCL on 2016/6/15.
 */
public interface ICategoryModel {

    Observable<DataInfo> data(String classify, int pageSize, int page);

}
