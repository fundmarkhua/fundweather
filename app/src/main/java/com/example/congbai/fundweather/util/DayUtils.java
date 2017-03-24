package com.example.congbai.fundweather.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by fundmarkhua on 2017/3/22
 * Email:57525101@qq.com
 * 关于日期时间的工具类
 */

public class DayUtils {
    //定义本地化参数
    private static final Locale locale = Locale.CHINA;

    /**
     * 根据输入的日期计算和当前时间的关系  如五分钟后， 一小时前，明天，后天等
     *
     * @param date 日期格式 1990-01-01 10:10:10
     * @return 返回今天、明天、具体日期
     */
    public static String getDate(String date) {
        Calendar today = Calendar.getInstance();
        Calendar old = Calendar.getInstance();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
            Date dayStart = df.parse(date);    //dayStart是类似"2013-02-02"的字符串
            old.setTime(dayStart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 此处好像是去除0
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        old.set(Calendar.HOUR, 0);
        old.set(Calendar.MINUTE, 0);
        old.set(Calendar.SECOND, 0);
        old.set(Calendar.MILLISECOND, 0);
        // 老的时间减去今天的时间
        long intervalMilli = old.getTimeInMillis() - today.getTimeInMillis();
        int xCts = (int) (intervalMilli / (24 * 60 * 60 * 1000));
        // -2:前天 -1：昨天 0：今天 1：明天 2：后天， out：显示日期
        String day = date;
        if (xCts >= -2 && xCts != 0) {
            switch (xCts) {
                case -2:
                    day = "前天";
                    break;
                case -1:
                    day = "昨天";
                    break;
                case 1:
                    day = "明天";
                    break;
                case 2:
                    day = "后天";
                    break;
            }
        } else if (xCts == 0) {
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
                Date dayStart = df.parse(date);
                long oldTime = dayStart.getTime();
                long nowTime = System.currentTimeMillis();
                int timeDiff = (int) ((nowTime - oldTime) / 1000 / 60);
                int cha = Math.abs(timeDiff);
                String dayChar = "前";
                if (timeDiff < 0) {
                    dayChar = "后";
                }
                if (cha < 5) {
                    day = "刚刚";
                } else if (cha < 10) {
                    day = "5分钟";
                } else if (cha < 20) {
                    day = "10分钟";
                } else if (cha < 30) {
                    day = "20分钟";
                } else if (cha < 40) {
                    day = "30分钟";
                } else if (cha < 50) {
                    day = "40分钟";
                } else if (cha < 60) {
                    day = "50分钟";
                } else if (cha < 120) {
                    day = "一小时";
                } else if (cha < 180) {
                    day = "两小时";
                } else if (cha < 240) {
                    day = "三小时";
                } else if (cha < 300) {
                    day = "四小时";
                } else if (cha < 360) {
                    day = "五小时";
                } else if (cha < 420) {
                    day = "六小时";
                } else if (cha < 480) {
                    day = "七小时";
                } else if (cha < 360) {
                    day = "八小时";
                } else {
                    day = "今天";
                }
                if (cha < 360 && cha >= 5) {
                    day += dayChar;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return day;
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss格式 时间字符串
     */
    public static String getCurrTime() {

        Calendar calendar = Calendar.getInstance(locale);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);

        return dateFormat.format(date);
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd 格式 时间字符串
     */
    public static String getCurrTimeSimple() {

        Calendar calendar = Calendar.getInstance(locale);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        return dateFormat.format(date);
    }

    /**
     * 获取当前时间 格式订制
     *
     * @param formatStr 时间格式
     * @return 定制格式的时间字符串
     */
    public static String getCurrTimeCustom(String formatStr) {

        Calendar calendar = Calendar.getInstance(locale);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr, locale);
        return dateFormat.format(date);
    }

    /**
     * 比较时间大小
     *
     * @param DATE1 时间1 格式 yyyy-MM-dd HH:mm:ss 下同
     * @param DATE2 时间2
     * @return 时间1 晚于 时间2 返回true
     */
    public static boolean compare_date(String DATE1, String DATE2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return true;
            }
            if (dt1.getTime() < dt2.getTime()) {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 获取两个时间的差 时间1晚为正值，时间1早为负值
     *
     * @param str1  时间1
     * @param str2  时间2
     * @return 相差分钟数
     */
    public static long getDistanceTimesTwo(String str1, String str2) {
        long time = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        Date one;
        Date two;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff = time1 - time2;
            time = diff / (60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

}
