package com.link.fan.utils.alipay.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getPayDate () {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
