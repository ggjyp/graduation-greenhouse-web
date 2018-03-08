package com.jyp.greenhouse.core.util;


import com.jyp.greenhouse.web.constants.Constants;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by oplsu on 2016/11/3.
 */
public class TimeUtil {
    //   得到当前时间戳--秒
    public static long getNowTimestamp() {
        return new Date().getTime() / 1000;
    }

    //    得到时间戳对应的Date
    public static Date parseTime(String t) {
        return new Date(Long.parseLong(t) * 1000);
    }

    //    时间date转时间字符串
    public static String DateToString(String pattern, Date date) {
        String datestr = new SimpleDateFormat(pattern).format(date);
        return datestr;
    }

    //    时间戳转时间字符串
    public static String TimeStampDate(String pattern, String timestampString) {
        if (timestampString == null || timestampString.equals("") || timestampString.equals("0")) {
            return "";
        }
        String date = new SimpleDateFormat(pattern).format(parseTime(timestampString));
        return date;
    }

    //    时间字符串转时间戳
    public static long TimeStrToStamp(String pattern, String str) {
        if (StringUtil.isBlank(str))
            return 0;
        return parse(pattern, str).getTime() / 1000;
    }

    /**
     * 时间字符串转时间date
     * 解析日期，注:此处为严格模式解析，即20151809这样的日期会解析错误
     *
     * @param pattern yyyy-MM-dd
     * @param date    yyyy-MM-dd
     * @return
     */
    public static Date parse(String pattern, String date) {
        return parse(pattern, date, Constants.LOCALE_CHINA);
    }

    public static Date parse(String pattern, String date, Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
        format.setLenient(false);
        Date result = null;
        try {
            result = format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //    得到某月第一天2016-11-12
    public static String getMonthFirstDay(String datadate) {
        Date date = null;
        String day_first = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(datadate);
        } catch (Exception ex) {
            return "";
        }
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        day_first = format.format(calendar.getTime());
        return day_first;
    }

    //    得到某月最后一天2016-11-12
    public static String getMonthLastDay(String datadate) {
        Date date = null;
        String day_last = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(datadate);
        } catch (Exception ex) {
            return "";
        }
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);     //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        day_last = format.format(calendar.getTime());
        return day_last;
    }

    //得到指定日期下一天的日期2016-11-12
    public static String getNextDate(String start, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse("yyyy-MM-dd", start));
        calendar.add(Calendar.DATE, day);
        return TimeStampDate("yyyy-MM-dd", calendar.getTime().getTime() / 1000 + "");
    }

    /**
     * @param date
     * @param flag 0:陵城00:00:00 1：午夜：23:59:59
     * @return
     */
    public static Date weakTime(Date date, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

        if (flag == 0) {
            return cal.getTime();
        } else if (flag == 1) {
            //凌晨23:59:59
            cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
        }
        return cal.getTime();
    }

    //得到指定日期后面n个月的日期
    public static Date getMonthDate(Date start, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }


    /**
     * 得到日期a和b相差的天数
     *
     * @param a
     * @param b
     * @return
     */
    public static int differ(Date a, Date b) {
        long dif = Math.abs(a.getTime() - b.getTime()) / 1000;
        return (int) (dif / (3600 * 24));
    }


    /**
     * 得到当前日期是周几
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 根据生日计算年龄
     * @param birthday yyyy-MM-dd
     * @return
     */
    public static int getAge(String birthday){
        Date birthDay = parse("yyyy-MM-dd", birthday);
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age;
    }

    /**
     * 将SiteWhere发送的日期转换为yyyy-MM-dd HH:mm:ss格式
     * @param date SiteWhere的日期格式为2016-12-10T13:11:45.119-0500
     * @return
     */
    public static String parseSiteWhereDate(String date) {
        return date.substring(0, 10)+" "+ date.substring(11,19);
    }

    public static void main(String[] args)
    {
        String date = "2016-12-10T13:11:45.119-0500";
        System.out.println(parseSiteWhereDate(date));
    }
}
