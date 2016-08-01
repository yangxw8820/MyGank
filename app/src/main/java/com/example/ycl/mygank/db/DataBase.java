package com.example.ycl.mygank.db;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.ycl.mygank.Category;
import com.example.ycl.mygank.bean.DataResultInfo;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by YCL on 2016/7/25.
 */

public abstract class DataBase {

    protected static Context mContext;
    protected Realm realm;

    public DataBase() {
        init();
    }

    protected void init(){
        if (mContext == null){
            throw new RuntimeException("必须先调用init初始化");
        }

        RealmConfiguration configuration = new RealmConfiguration.Builder(mContext)
                .name(getName())
                .schemaVersion(getVersion())
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(configuration);
    }

    public static void init(Context context) {
        if (context == null){
            throw new IllegalArgumentException("Context不能为null");
        }

        mContext = context.getApplicationContext();
    }

    protected void checkRealm(){
        if (realm == null){
            init();
        }
    }

    /**
     * saveOrUpdate
     * @param info
     */
    public void save(DataResultInfo info){
        checkRealm();
        String s = JSON.toJSONString(info, true);
        realm.beginTransaction();
        realm.createOrUpdateObjectFromJson(DataResultInfo.class, s);
        realm.commitTransaction();
    }

    /**
     * saveOrUpdateAll
     * @param results
     */
    public void save(List<DataResultInfo> results){
        checkRealm();
        final String s = JSON.toJSONString(results, true);
//        System.out.println(s);
        realm.beginTransaction();
        realm.createOrUpdateAllFromJson(DataResultInfo.class, s);
        realm.commitTransaction();

//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.createAllFromJson(DataResultInfo.class, s);
//            }
//        });
    }

    public boolean delete(String id){
        checkRealm();
        realm.beginTransaction();
        boolean b = realm.where(DataResultInfo.class)
                .equalTo("id", id)
                .findAll()
                .deleteFirstFromRealm();
        realm.commitTransaction();
        return b;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean isExist(String id){
        return get(id) != null;
    }

    /**
     *
     * @param id
     * @return
     */
    public DataResultInfo get(String id){
        checkRealm();
        DataResultInfo info = realm.where(DataResultInfo.class)
                .equalTo("id", id)
                .findFirst();
//        String s = JSON.toJSONString(info);
//        return JSON.parseObject(s, DataInfo.Results.class);
        return info;
    }

    /**
     * 分页
     * @param infos
     * @param pageSize
     * @param page
     * @return
     */
    protected List<DataResultInfo> get(RealmResults<DataResultInfo> infos, int pageSize, int page){
        if (infos == null){
            return new ArrayList<>();
        }
        int start = (page - 1) * pageSize;
        if (start < 0){
            start = 0;
        }

        int end = start + pageSize;
        int size = infos.size();
        if (end > size){
            end = size;
        }

        List<DataResultInfo> list = infos.subList(start, end);
        String s = JSON.toJSONString(list, true);

        return JSON.parseArray(s, DataResultInfo.class);
    }

    public void close(){
        if (realm != null){
            realm.close();
            realm = null;
        }
    }

    protected abstract String getName();
    protected abstract long getVersion();

}
