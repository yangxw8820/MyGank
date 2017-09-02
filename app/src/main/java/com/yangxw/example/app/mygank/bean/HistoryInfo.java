package com.yangxw.example.app.mygank.bean;

import java.util.List;

/**
 * Created by YCL on 2016/6/8.
 */
public class HistoryInfo {
    /**
     * error : false
     * results : ["2016-06-08","2016-06-07"]
     */

    private boolean error;
    private List<String> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
