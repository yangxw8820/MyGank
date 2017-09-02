package com.yangxw.example.app.mygank.db;

import com.yangxw.example.app.mygank.Category;
import com.yangxw.example.app.mygank.bean.DataResultInfo;

import java.util.List;

import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by YCL on 2016/6/15.
 */
public class CategoryDB extends DataBase {

    private static final String NAME = "category.realm";

    private static CategoryDB db;
    public static CategoryDB getInstance() {
        if (db == null){
            db = new CategoryDB();
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
     * @param category
     * @param pageSize
     * @param page
     * @return
     */
    public List<DataResultInfo> get(String category, int pageSize, int page){
        checkRealm();
        RealmQuery<DataResultInfo> realmQuery = realm.where(DataResultInfo.class);
        if (!Category.ALL.equals(category)){
            realmQuery.equalTo("type", category);
        }
        RealmResults<DataResultInfo> infos = realmQuery
                .findAllSorted("createdAt", Sort.DESCENDING);

        return get(infos, pageSize, page);
    }

}
