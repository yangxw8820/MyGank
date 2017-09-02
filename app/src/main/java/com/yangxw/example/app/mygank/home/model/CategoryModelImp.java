package com.yangxw.example.app.mygank.home.model;

import com.alibaba.fastjson.JSON;
import com.yangxw.example.app.mygank.api.API;
import com.yangxw.example.app.mygank.bean.DataInfo;
import com.yangxw.example.app.mygank.bean.DataResultInfo;
import com.yangxw.example.app.mygank.db.CategoryDB;

import java.util.List;

import rx.Observable;
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
                .map(new Func1<String, DataInfo>() {
                    @Override
                    public DataInfo call(String s) {
                        DataInfo info = JSON.parseObject(s, DataInfo.class);
                        info.setFromLocal(false);
                        CategoryDB.getInstance().save(info.getResults());
                        return info;
                    }
                })
                .onErrorReturn(new Func1<Throwable, DataInfo>() {
                    @Override
                    public DataInfo call(Throwable throwable) {
                        DataInfo info = new DataInfo();
                        info.setError(true);
                        info.setMsg(throwable.getMessage());
                        return info;
                    }
                })
                .map(new Func1<DataInfo, DataInfo>() { // 统一处理数据
                    @Override
                    public DataInfo call(DataInfo info) {
                        if (info.isError()){
                            info.setFromLocal(true);
                        }

                        List<DataResultInfo> infos = CategoryDB.getInstance().get(classify, pageSize, page);
                        info.setResults(infos);
                        info.setError(false);

                        return info;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
