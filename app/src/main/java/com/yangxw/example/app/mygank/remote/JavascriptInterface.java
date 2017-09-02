package com.yangxw.example.app.mygank.remote;

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
    public void showHTML(String html){
        System.out.println(html);
    }

}
