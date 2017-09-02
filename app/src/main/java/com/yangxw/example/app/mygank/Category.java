package com.yangxw.example.app.mygank;

/**
 * Created by YCL on 2016/6/13.
 */
public class Category {

    public static final String ALL = "all";
    public static final String ANDROID = "Android";
    public static final String iOS = "iOS";
    public static final String APP = "App";
    public static final String QIAN_DUAN = "前端";
    public static final String KUO_ZHANG = "拓展资源";
    public static final String FU_LI = "福利";
    public static final String SHI_PIN = "休息视频";
    public static final String TUI_JIAN = "瞎推荐";

    public static String[] toCategories(){
        return new String[]{ALL, ANDROID, iOS, APP, QIAN_DUAN, KUO_ZHANG, FU_LI, SHI_PIN, TUI_JIAN};
    }

}
