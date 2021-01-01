package com.mljr.gps.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 日期工具类
 * @Date : 2018/9/7 14:58
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public class DateUtils {

    private static final String CURRENT_DAY_FORMAT = "yyyy-MM-dd";

    public static String getCurrentDay(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CURRENT_DAY_FORMAT);
        return simpleDateFormat.format(date);
    }
}
