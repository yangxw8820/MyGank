package com.yangxw.example.app.mygank.collect.model;

import com.yangxw.example.app.mygank.bean.DataInfo;
import com.yangxw.example.app.mygank.bean.DataResultInfo;
import com.yangxw.example.app.mygank.db.CollectDB;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by YCL on 2016/8/1.
 */
public class CollectModel implements ICollectModel {
    @Override
    public Observable<DataInfo> query(final int pageSize, final int page) {
        return Observable.create(new Observable.OnSubscribe<DataInfo>() {
            @Override
            public void call(Subscriber<? super DataInfo> subscriber) {
                List<DataResultInfo> infos = CollectDB.newInstance().get(pageSize, page);
                DataInfo info = new DataInfo();
                if (infos == null || infos.isEmpty()){
                    info.setError(true);
                    info.setMsg("没有查询到数据");
                } else {
                    info.setError(false);
                    info.setMsg("查询成功");
                }
                info.setFromLocal(true);
                info.setResults(infos);
                subscriber.onNext(info);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread());
    }
}
