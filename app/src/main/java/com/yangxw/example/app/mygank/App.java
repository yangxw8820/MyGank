package com.yangxw.example.app.mygank;

import android.app.Application;

import com.yangxw.example.app.mygank.db.DataBase;

/**
 * Created by YCL on 2016/6/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataBase.init(this);
    }

}
