package com.example.ycl.mygank.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YCL on 2016/6/13.
 */
public class DateUtil {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    public static final SimpleDateFormat dateFormatTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 2016-06-07T19:20:28.541Z
     * @param date
     * @return
     */
    public static final String format(String date){
        try {
            Date d = dateFormat.parse(date);
//            String s = dateFormatTo.format(d);

            return dateFormatTo.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
