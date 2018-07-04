package com.shandong.human.resource.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化工具类
 * 主要包括：
 * 1.字符串转为日期
 * 2.字符串转为日期
 *
 * @author tyee.noprom@qq.com
 * @time 2/11/16 11:57 AM.
 */
public class DateUtil {

    /**
     * 字符串转为日期
     *
     * @param dateStr   原始日期字符串
     * @param fromatStr 日期格式
     * @return 日期
     */
    public static Date String2Date(String dateStr, String fromatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(fromatStr);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转为日期
     *
     * @param dateStr 原始日期字符串
     * @return 日期
     */
    public static Date String2Date(String dateStr) {
        return String2Date(dateStr, "yyyy-MM-dd");
    }

}
