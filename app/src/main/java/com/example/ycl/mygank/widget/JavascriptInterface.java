package com.example.ycl.mygank.widget;

import android.webkit.ValueCallback;

/**
 * Created by ycl on 16/7/24.
 */

public class JavascriptInterface {

    @android.webkit.JavascriptInterface
    public Object evaluateJavascript(String name){
        System.out.println(name);
        return name;
    }

    @android.webkit.JavascriptInterface
    public void evaluateJavascript(String name, ValueCallback<String> resultCallback){
        System.out.println(name);
    }

}
