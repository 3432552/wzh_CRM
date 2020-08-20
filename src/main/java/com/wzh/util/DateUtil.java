package com.wzh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: wzh
 * @ClassName: DateUtil
 * @Description:
 * @Date: 2020/3/28 22:57
 */
public class DateUtil {
    //转化为当前时间 String
    public static String expireTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expireTime = sdf.format(date);
        return expireTime;
    }

    //转为当前时间 Date
    public static Date dateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = sdf.format(date);
        Date time = null;
        try {
            time = sdf.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
