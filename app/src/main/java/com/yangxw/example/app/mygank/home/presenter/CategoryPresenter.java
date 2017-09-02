package com.yangxw.example.app.mygank.home.presenter;

import com.yangxw.example.app.mygank.bean.DataInfo;
import com.yangxw.example.app.mygank.home.model.CategoryModelImp;
import com.yangxw.example.app.mygank.home.model.ICategoryModel;
import com.yangxw.example.app.mygank.home.view.ICategoryView;
import com.yangxw.example.app.mygank.rx.ObserverImp1;

/**
 * Created by YCL on 2016/6/15.
 */
public class CategoryPresenter {

    private static final int PAGE_SIZE = 20;

    private int page = 1;

    private ICategoryView view;
    private ICategoryModel model;

    public CategoryPresenter(ICategoryView view) {
        this.view = view;
        this.model = new CategoryModelImp();
    }

    public void refresh(String category) {
        view.startRefresh();
        page = 1;
        model.data(category, PAGE_SIZE, page).subscribe(new ObserverImp1<DataInfo>() {
            @Override
            public void onNext(DataInfo info) {
                if (!info.isError()) {
                    view.completedRefresh(info);
                } else {
                    // 错误处理
                    view.completedMsg(false, info.getMsg());
                }
                view.completedMsg(true, "");
            }
        });
    }

    public void loadMore(String category) {
        if (view.canLoadMore()) {
            view.startLoadMore();
            final int _page = page + 1;
            model.data(category, PAGE_SIZE, _page).subscribe(new ObserverImp1<DataInfo>() {
                @Override
                public void onNext(DataInfo info) {
                    page = _page;
                    if (!info.isError()) {
                        view.completedLoadMore(info);
                    } else {
                        // 错误处理
                        view.completedMsg(false, info.getMsg());
                    }
                    view.completedMsg(true, "");
                }
            });
        }
    }

}
