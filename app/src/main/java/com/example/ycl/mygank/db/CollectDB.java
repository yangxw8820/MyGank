package com.example.ycl.mygank.db;

import android.os.Bundle;

import com.example.ycl.mygank.bean.DataResultInfo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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

    /**
     *
     * @param pageSize
     * @param page
     * @return
     */
    public List<DataResultInfo> get(int pageSize, int page){
        checkRealm();
        RealmResults<DataResultInfo> results = realm.where(DataResultInfo.class)
                .findAllSorted("publishedAt", Sort.DESCENDING);
        return get(results, pageSize, page);
    }

}
