package com.example.ycl.mygank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ycl.mygank.api.API;
import com.example.ycl.mygank.bean.DayInfo;
import com.example.ycl.mygank.rx.ObserverImp1;

import org.junit.Test;

import java.util.Iterator;

/**
 * Created by YCL on 2016/6/8.
 */
public class APIUnitTest {

    @Test
    public void data() throws Exception{
        API.PROXY.data("Android", 1, 1)
        .subscribe(new ObserverImp1<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void day() throws Exception{
        API.PROXY.day("2016", "06", "01")
        .subscribe(new ObserverImp1<String>() {
            @Override
            public void onNext(String s) {
                DayInfo dayInfo = DayInfo.parse(s);
                System.out.println(dayInfo);
            }
        });
    }

    @Test
    public void random() throws Exception{
        API.PROXY.random("Android", 1)
        .subscribe(new ObserverImp1<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void history() throws Exception{
        API.PROXY.history()
        .subscribe(new ObserverImp1<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void history1() throws Exception{
        API.PROXY.history(1, 1)
        .subscribe(new ObserverImp1<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void history2() throws Exception{
        API.PROXY.history("2016", "06", "08")
        .subscribe(new ObserverImp1<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void add2Gank() throws Exception{
        API.PROXY.add2Gank("https://github.com/likaci/ViFinder", "ViFinder", "likaci", "Android", true)
        .subscribe(new ObserverImp1<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

}
