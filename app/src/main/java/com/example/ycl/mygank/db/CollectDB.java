package com.example.ycl.mygank.db;

import android.os.Bundle;

import io.realm.Realm;

/**
 * Created by YCL on 2016/7/25.
 */

public class CollectDB extends DataBase {
    private static final String NAME = "collect.realm";

    private static CollectDB db;
    public static CollectDB newInstance() {
        if (db == null){
            db = new CollectDB();
        }

        return db;
    }

    @Override
    protected String getName() {
        return NAME;
    }

    @Override
    protected long getVersion() {
        return 1;
    }

}
