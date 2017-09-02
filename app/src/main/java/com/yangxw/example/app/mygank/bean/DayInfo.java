package com.yangxw.example.app.mygank.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YCL on 2016/6/8.
 */
public class DayInfo {
    /**
     * Category : ["Category","前端","瞎推荐","iOS","Android","拓展资源","福利","休息视频"]
     * error : false
     * results : {"Android":[{"_id":"574d6a92421aa910abe2bf99","createdAt":"2016-05-31T18:42:26.172Z","desc":"EventBus3 事件管理插件","publishedAt":"2016-06-01T12:01:44.959Z","source":"chrome","type":"Android","url":"https://github.com/kgmyshin/eventbus3-intellij-plugin","used":true,"who":"蒋朋"}]}
     */

    private boolean error;
    private Results results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public static class Results {
        private List<String> category;
        private List<CategoryBean> categorys;

        public List<String> getCategory() {
            return category;
        }

        public void setCategory(List<String> category) {
            this.category = category;
        }

        public List<CategoryBean> getCategorys() {
            return categorys;
        }

        public void setCategorys(List<CategoryBean> categorys) {
            this.categorys = categorys;
        }

        public static class CategoryBean{
            private String category;
            private List<Category> categories;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public List<Category> getCategories() {
                return categories;
            }

            public void setCategories(List<Category> categories) {
                this.categories = categories;
            }

            /**
             * _id : 574c0cd3421aa910abe2bf72
             * createdAt : 2016-05-30T17:50:11.804Z
             * desc : ReactNative亲戚称谓计算器
             * publishedAt : 2016-06-01T12:01:44.959Z
             * source : chrome
             * type : Category
             * url : https://github.com/lishengzxc/RnRelative
             * used : true
             * who : 咕咚
             */
            public static class Category {
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

    }

    /**
     * 用这个来处理返回的数据
     * @param s
     * @return
     */
    public static DayInfo parse(String s){
        DayInfo info = new DayInfo();

        JSONObject jsonObject = JSON.parseObject(s);
        boolean error = jsonObject.getBoolean("error");
        JSONObject results = jsonObject.getJSONObject("results");
        JSONArray category = jsonObject.getJSONArray("Category");

        if (!error){
            Results results1 = new Results();
            List<String> category1 = category.toJavaObject(List.class);
            results1.setCategory(category1);

            List<Results.CategoryBean> categorys = new ArrayList<>();
            int size = category.size();
            for (int i = 0; i < size; i++) {
                String obj = (String) category.get(i);
                JSONArray objects = results.getJSONArray(obj);
                Results.CategoryBean bean = new Results.CategoryBean();
                bean.setCategory(obj);
                bean.setCategories(JSON.parseArray(objects.toJSONString(), Results.CategoryBean.Category.class));
                categorys.add(bean);
            }
            results1.setCategorys(categorys);

            info.setResults(results1);
        }
        info.setError(error);

        return info;
    }

}
