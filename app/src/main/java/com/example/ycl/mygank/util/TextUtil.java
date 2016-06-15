package com.example.ycl.mygank.util;

import android.text.TextUtils;

/**
 * Created by YCL on 2016/6/15.
 */
public class TextUtil {

    public static boolean isEmpty(String str){
        if (str == null || str.length() == 0){
            return true;
        } else{
            return false;
        }
    }

}
