package com.example.ycl.mygank.rx;

import rx.Observer;

/**
 * Created by YCL on 2016/6/8.
 */
public abstract class ObserverImp1<T> implements Observer<T> {
    @Override
    public void onCompleted(){}

    @Override
    public void onError(Throwable e){
        e.printStackTrace();
    }

}
