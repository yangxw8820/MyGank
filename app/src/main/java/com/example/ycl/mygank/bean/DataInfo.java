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
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
