package com.wzh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: wzh
 * @ClassName: DateUtil
 * @Description:
 * @Date: 2020/3/28 22:57
 */
public class DateUtil {
    //转为当前时间
    public static String dateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        return time;
    }
}
