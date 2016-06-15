package com.example.ycl.mygank.home.model;

import com.alibaba.fastjson.JSON;
import com.example.ycl.mygank.api.API;
import com.example.ycl.mygank.bean.DataInfo;
import com.example.ycl.mygank.db.CategoryDB;
import com.example.ycl.mygank.db.bean.ResultsInfo;
import com.example.ycl.mygank.util.TextUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by YCL on 2016/6/15.
 */
public class CategoryModelImp implements ICategoryModel {

    @Override
    public Observable<DataInfo> data(final String classify, final int pageSize, final int page) {
        return API.PROXY.data(classify, pageSize, page)
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Func1<Throwable, String>() {
                    @Override
                    public String call(Throwable throwable) {
                        return null;
                    }
                })
                .map(new Func1<String, DataInfo>() { // 处理网络or数据库数据来源
                    @Override
                    public DataInfo call(String s) {
                        DataInfo info = null;
                        if (s == null){
                            info = new DataInfo();
                            info.setError(false);

                            List<ResultsInfo> infos = CategoryDB.getInstance().get(classify, pageSize, page);
                            String jsonString = JSON.toJSONString(infos, true);
                            List<DataInfo.Results> list = JSON.parseArray(jsonString, DataInfo.Results.class);
                            info.setResults(list);
                        } else {
                            info = JSON.parseObject(s, DataInfo.class);
                            CategoryDB.getInstance().save(info.getResults());
                        }

                        return info;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
