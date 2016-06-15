package com.example.ycl.mygank.db;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.ycl.mygank.Category;
import com.example.ycl.mygank.bean.DataInfo;
import com.example.ycl.mygank.db.bean.ResultsInfo;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by YCL on 2016/6/15.
 */
public class CategoryDB {

    private static final String NAME = "category.realm";

    private Realm realm;

    private CategoryDB(Context context) {
        if (context == null){
            throw new IllegalArgumentException("Context 不能为null");
        } else {
            RealmConfiguration configuration = new RealmConfiguration.Builder(context.getApplicationContext())
                    .name(NAME)
                    .build();
            Realm.setDefaultConfiguration(configuration);

            realm = Realm.getDefaultInstance();
        }
    }

    private static CategoryDB db;
    public static CategoryDB getInstance() {
        if (db == null){
            throw new RuntimeException("必须先调用init初始化");
        }
        return db;
    }

    public static CategoryDB init(Context context) {
        if (db == null){
            db = new CategoryDB(context);
        }
        return db;
    }

    public void save(List<DataInfo.Results> results){
        final String s = JSON.toJSONString(results);
        realm.beginTransaction();
        realm.createAllFromJson(ResultsInfo.class, s);
        realm.commitTransaction();

//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.createAllFromJson(ResultsInfo.class, s);
//            }
//        });
    }

    public ResultsInfo get(String id){
        ResultsInfo info = realm.where(ResultsInfo.class)
                .equalTo("_id", id)
                .findFirst();
//        String s = JSON.toJSONString(info);
//        return JSON.parseObject(s, DataInfo.Results.class);
        return info;
    }

    public List<ResultsInfo> get(String category, int pageSize, int page){
        RealmQuery<ResultsInfo> realmQuery = realm.where(ResultsInfo.class);
        if (!Category.ALL.equals(category)){
            realmQuery.equalTo("type", category);
        }
        RealmResults<ResultsInfo> infos = realmQuery
                .findAllSorted("createdAt", Sort.DESCENDING);

//        String jsonString = JSON.toJSONString(infos, true);
//        List<DataInfo.Results> list = JSON.parseArray(jsonString, DataInfo.Results.class);

        int start = (page - 1) * pageSize;
        if (start < 0){
            start = 0;
        }

        int end = start + pageSize;
        int size = infos.size();
        if (end > size){
            end = size;
        }
        return infos.subList(start, end);
    }

    public static void close(){
        if (db != null){
            db.realm.close();
            db = null;
        }
    }

}
