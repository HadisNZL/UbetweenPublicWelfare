package com.ubetween.ubetweenpublicwelfare.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 日期转换管理类（整理后）
 *
 * @author hadis on 16.3.10.
 */
public class DateUtils {

    public static String[] weekArray = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    public static String[] weekArray2 = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    static SimpleDateFormat formatter = new SimpleDateFormat("MM-dd", Locale.getDefault());
    static String dateTitle = "今天\n01-01\t周一";
    public static String dateStyle = "yyyy-MM-dd";
    public static String monStyle = "yyyy-MM";
    public static String timeStyle = "HH:mm";

    static int weeks = 0;// 用来全局控制 上一周，本周，下一周的周数变化
    static int MaxDate; // 一月最大天数
    static int MaxYear; // 一年最大天数

    /**
     * 返回数据格式：[今天 ,01-01 ,周一] tag: 0今天 1明天
     *
     * @author:lmw
     * @date:2014-3-9 下午5:51:10
     */
    public static String getDateTitle(int tag) {
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, tag);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        dateTitle = (tag == 0 ? "今天" : "明天") + "\n" + formatter.format(calendar.getTime()) + "\t" + weekArray[w];
        return dateTitle;
    }

    public static String getDate(String style) {
        SimpleDateFormat formatter = new SimpleDateFormat(style, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        return formatter.format(calendar.getTime());
    }

    public static String getTime(String style) {
        SimpleDateFormat formatter = new SimpleDateFormat(style, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        return formatter.format(calendar.getTime());
    }

    public static String clanderTodatetime(Calendar calendar, String style) {
        SimpleDateFormat formatter = new SimpleDateFormat(style, Locale.getDefault());
        return formatter.format(calendar.getTime());
    }

    /**
     * 获取当前日期是星期几
     */
    public static String[] getAfterFiveDay() {
        // 获取当天是这周的第几天
        Calendar cal = Calendar.getInstance();
        Date curDate = new Date(System.currentTimeMillis());
        cal.setTime(curDate);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return getweeks(weekArray[w]);
    }

    // 获取今后五天的星期名称
    public static String[] getweeks(String currWeedDay) {
        String[] weeks = new String[5];
        int i;
        for (i = 0; i < weekArray.length; i++) {
            if (weekArray[i] == currWeedDay) {
                break;
            }
        }
        for (int j = 0; j < 5; j++) {
            i++;
            if (i > 6) {
                i = 0;
            }
            weeks[j] = weekArray[i];
        }
        return weeks;

    }

    // 获取本周一日期
    public static String getMondayOFWeek(String style) {
        if (style == null) {
            style = "yyyy-MM-dd";
        }
        weeks = 0;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(style, Locale.getDefault());
        sdf.format(monday);
        return sdf.format(monday);
    }

    // 计算本月最大天数
    @SuppressWarnings("unused")
    private static int getMonthPlus() {
        Calendar cd = Calendar.getInstance();
        int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
        cd.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        cd.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        MaxDate = cd.get(Calendar.DATE);
        if (monthOfNumber == 1) {
            return -MaxDate;
        } else {
            return 1 - monthOfNumber;
        }
    }

    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    public static int getSundayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 7; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    /**
     * 获取明天的日期
     *
     * @return
     */
    public static Date getTomorrowDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, +1);

        return cal.getTime();
    }

    public static String getTomorrowDates(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateStyle, Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, +1);

        return formatter.format(cal.getTime());
    }

    // 获得本周七天日期
    @SuppressWarnings("deprecation")
    public static String[][] thisweekdays() {
        String[][] days = new String[7][2];
        Date currentDate = new Date();
        int b = currentDate.getDay();
        Date fdate;
        Long fTime = currentDate.getTime() - b * 24 * 3600000;
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            SimpleDateFormat mon = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
            SimpleDateFormat day = new SimpleDateFormat("dd", Locale.getDefault());
            days[a - 1][0] = mon.format(fdate);
            days[a - 1][1] = day.format(fdate);
        }
        return days;
    }

    // 某天是星期的第几天
    public static int getIndexOfWeek(String date) {
        Calendar cal = Calendar.getInstance();
        if (date == null) {
            // 获取当天是这周的第几天
            Date curDate = new Date(System.currentTimeMillis());
            cal.setTime(curDate);
        } else {
            cal.setTime(strToDate("yyyy-MM-dd", date));
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 0) {
            w = 7;
        }
        return w;
    }

    // 字符串类型日期转化成date类型
    public static Date strToDate(String style, String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(style, Locale.getDefault());
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 用法 String time = DateUtils.dateToStr("yyyy-MM-dd HH:mm", new Date());
     *
     * @param style
     * @param date
     * @return
     */
    public static String dateToStr(String style, Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(style, Locale.getDefault());
        return formatter.format(date);
    }

    // 获取年月日 星期
    public static String getdateAndWeek(String dateTime) {
        String dataOfDateTime = dateTime.substring(0, 10);
        dataOfDateTime += " " + weekArray2[getIndexOfWeek(dataOfDateTime) - 1];
        return dataOfDateTime;
    }
}
