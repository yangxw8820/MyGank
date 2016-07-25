package com.example.ycl.mygank;

import android.app.Application;

import com.example.ycl.mygank.db.DataBase;

/**
 * Created by YCL on 2016/6/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataBase.init(getApplicationContext());
    }

}
