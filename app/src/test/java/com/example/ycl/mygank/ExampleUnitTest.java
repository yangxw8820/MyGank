package com.example.ycl.mygank;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat dateFormatTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        Date date = dateFormat.parse("2016-06-07T19:20:28.541Z");
        String s = dateFormatTo.format(date);
        System.out.println(s);

    }
}