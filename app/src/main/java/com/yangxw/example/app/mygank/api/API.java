package com.yangxw.example.app.mygank.api;

import com.yangxw.example.app.mygank.Config;
import com.yangxw.example.app.mygank.string.StringConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ycl on 16/6/7.
 */

public interface API {

    API PROXY = new Retrofit.Builder()
            .client(HttpClientManager.getHttpClient())
            .baseUrl(Config.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(StringConverterFactory.create())
            .build()
            .create(API.class);

    /**
     * 分类数据<br/>
     *
     * @param classify 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * @param pageSize 请求个数： 数字，大于0
     * @param page     第几页：数字，大于0
     * @return
     * @see com.yangxw.example.app.mygank.bean.DataInfo
     */
    @GET("data/{classify}/{pageSize}/{page}")
    Observable<String> data(@Path("classify") String classify, @Path("pageSize") int pageSize, @Path("page") int page);

    /**
     * 每日数据<br>
     * yyyy/mm/dd<br/>
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return
     * @see com.yangxw.example.app.mygank.bean.DayInfo
     */
    @GET("day/{year}/{month}/{day}")
    Observable<String> day(@Path("year") String year, @Path("month") String month, @Path("day") String day);

    /**
     * 随机数据<br/>
     *
     * @param classify 数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
     * @param pageSize 个数： 数字，大于0
     * @return
     * @see com.yangxw.example.app.mygank.bean.DataInfo
     */
    @GET("random/data/{classify}/{pageSize}")
    Observable<String> random(@Path("classify") String classify, @Path("pageSize") int pageSize);

    /**
     * 获取发过干货日期<br/>
     *
     * @return
     * @see com.yangxw.example.app.mygank.bean.HistoryInfo
     */
    @GET("day/history")
    Observable<String> history();

    /**
     * 获取某几日干货<br/>
     *
     * @param pageSize 请求个数： 数字，大于0
     * @param preDay   第几日前：数字，大于0
     * @return
     * @see com.yangxw.example.app.mygank.bean.HistoryDataInfo
     */
    @GET("history/content/{pageSize}/{preDay}")
    Observable<String> history(@Path("pageSize") int pageSize, @Path("preDay") int preDay);

    /**
     * 获取特定日期网站数据<br>
     * yyyy/mm/dd<br/>
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return
     * @see com.yangxw.example.app.mygank.bean.HistoryDataInfo
     */
    @GET("history/content/day/{year}/{month}/{day}")
    Observable<String> history(@Path("year") String year, @Path("month") String month, @Path("day") String day);

    /**
     * 搜索
     * @param classify 可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     * @param pageSize
     * @param page
     * @return
     */
    @GET("search/query/listview/category/{classify}/count/{pageSize}/page/{page}")
    Observable<String> search(@Field("classify") String classify, @Field("pageSize") int pageSize, @Field("page") int page);

    /**
     * 提交干货到审核区
     *
     * @param url   想要提交的网页地址
     * @param desc  对干货内容的描述	单独的文字描述
     * @param who   提交者 ID	干货提交者的网络 ID
     * @param type  干货类型	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | Category
     * @param debug 当前提交为测试数据	如果想要测试数据是否合法, 请设置 debug 为 true! 可选参数: true | false
     * @return
     */
    @FormUrlEncoded
    @POST("add2gank")
    Observable<String> add2Gank(@Field("url") String url, @Field("desc") String desc,
                                @Field("who") String who, @Field("type") String type, @Field("debug") boolean debug);

}
