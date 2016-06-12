package com.example.ycl.mygank.bean;

import java.util.List;

/**
 * Created by YCL on 2016/6/8.
 */
public class HistoryDataInfo {

    private boolean error;
    /**
     * _id : 57579bb9421aa90ec874ea3e
     * content : <h3>&lt;&lt;&lt;&lt;&lt; 【WEB 外包】<a href="http://waibao.io/money/57579ef01d41c8658536d2bd">开发一款产品展示网站</a> &gt;&gt;&gt;&gt;&gt;&gt;</h3>
     * publishedAt : 2016-06-08T12:11:00.0Z
     * title : 昨天的魔兽首映，去看了吗！
     */

    private List<Results> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public static class Results {
        private String _id;
        private String content;
        private String publishedAt;
        private String title;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
