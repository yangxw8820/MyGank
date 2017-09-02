package com.yangxw.example.app.mygank.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by YCL on 2016/6/14.
 */
public class DisplayUtil {

    public static int px2dp(Context context, float px){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) (px / dm.density + 0.5f);
    }

    public static int dp2px(Context context, float dp){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) (dm.density * dp + 0.5f);
    }

}
