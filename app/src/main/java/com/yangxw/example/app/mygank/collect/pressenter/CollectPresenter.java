package com.yangxw.example.app.mygank.collect.pressenter;

import com.yangxw.example.app.mygank.bean.DataInfo;
import com.yangxw.example.app.mygank.collect.model.CollectModel;
import com.yangxw.example.app.mygank.collect.model.ICollectModel;
import com.yangxw.example.app.mygank.collect.view.ICollectView;
import com.yangxw.example.app.mygank.rx.ObserverImp2;

/**
 * Created by YCL on 2016/8/1.
 */
public class CollectPresenter {

    public static final int PAGE_SIZE = 20;
    private int page;

    private ICollectView view;
    private ICollectModel model;

    public CollectPresenter(ICollectView view) {
        this.view = view;
        this.model = new CollectModel();
    }

    public void load(){
        view.startLoading();
        page = 1;
        model.query(PAGE_SIZE, page).subscribe(new ObserverImp2<DataInfo>() {
            @Override
            public void onError(Throwable e) {
                view.loadFail(e.getMessage());
            }

            @Override
            public void onNext(DataInfo info) {
                if (info.isError()) {
                    view.loadFail(info.getMsg());
                } else {
                    view.loadSuccess(info.getResults());
                }
            }
        });
    }

    public void loadMore(){
        if (view.canLoadMore()){
            view.startLoadMore();
            page++;
            model.query(PAGE_SIZE, page).subscribe(new ObserverImp2<DataInfo>() {
                @Override
                public void onError(Throwable e) {
                    view.loadMoreFail(e.getMessage());
                }

                @Override
                public void onNext(DataInfo info) {
                    if (info.isError()) {
                        view.loadMoreFail(info.getMsg());
                    } else {
                        view.loadMoreSuccess(info.getResults());
                    }
                }
            });
        }
    }

}
