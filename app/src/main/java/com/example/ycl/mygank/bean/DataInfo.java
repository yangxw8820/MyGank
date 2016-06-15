package com.example.ycl.mygank.bean;

import java.util.List;

/**
 * Created by ycl on 16/6/7.
 */

public class DataInfo {
    /**
     * error : false
     * results : [{"_id":"5754fd03421aa948eea75a3d","createdAt":"2016-06-06T12:33:07.589Z","desc":"Google Agera Wiki 中文版","publishedAt":"2016-06-07T11:43:18.947Z","source":"chrome","type":"Android","url":"https://github.com/captain-miao/AndroidAgeraTutorial/wiki","used":true,"who":"马琳"}]
     */

    private boolean error;
    private String msg;
    private boolean isFromLocal;
    /**
     * _id : 5754fd03421aa948eea75a3d
     * createdAt : 2016-06-06T12:33:07.589Z
     * desc : Google Agera Wiki 中文版
     * publishedAt : 2016-06-07T11:43:18.947Z
     * source : chrome
     * type : Android
     * url : https://github.com/captain-miao/AndroidAgeraTutorial/wiki
     * used : true
     * who : 马琳
     */

    private List<DataResultInfo> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isFromLocal() {
        return isFromLocal;
    }

    public void setFromLocal(boolean fromLocal) {
        isFromLocal = fromLocal;
    }

    public List<DataResultInfo> getResults() {
        return results;
    }

    public void setResults(List<DataResultInfo> results) {
        this.results = results;
    }

}
