package xingkong.tool.core;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import lombok.extern.slf4j.Slf4j;

/**
 * 日期工具类
 *
 * @author xingkong
 */
@Slf4j
public final class DateTool {

    public static class Format {

        /**
         * 缺省的时间格式
         */
        public static String UTC_DATETIME = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        public static String DEFAULT_DATETIME = "yyyy-MM-dd HH:mm:ss";
        public static String DEFAULT_DATE = "yyyy-MM-dd";
        public static String DEFAULT_TIME = "HH:mm:ss";

        /**
         * 没有连字符和空格的时间格式
         */
        public static final String NOSPACE_DATETIME = "yyyyMMddHHmmss";
        public static final String NOSPACE_DATE = "yyyyMMdd";
        public static final String NOSPACE_TIME = "hhmmss";

        /**
         * 中国的时间格式
         */
        public static final String CHINESE_DATETIME = "yyyy年MM月dd日 HH点mm分ss秒";
        public static final String CHINESE_DATE = "yyyy年MM月dd日";
        public static final String CHINESE_TIME = "HH点mm分ss秒";

        /**
         * 时间
         */
        public static final String START_TIME = " 00:00:00";
        public static final String END_TIME = " 23:59:59";

    }

    /**
     * @desc 获取格式化后的时间字符串
     *
     * @param longTime
     *            时间戳
     * @return
     */
    public static Date getDate(String longTime) {
        Long time = 0L;
        try {
            time = Long.parseLong(longTime);
        } catch (Exception e) {
            log.error("covert failed.");
        }
        Date date = new Date(time);

        return date;
    }

    /**
     * @desc 获取格式化后的时间字符串
     *
     * @param strTime
     *            时间戳
     * @return
     */
    public static Date getDate(String strTime, String formart) {
        try {
            SimpleDateFormat ft = new SimpleDateFormat(formart);
            return ft.parse(strTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @desc 获取格式化后的时间字符串
     *
     * @param longTime
     *            时间戳
     * @param formatText
     *            格式化字符串
     * @return
     */
    public static String getString(long longTime, String formatText) {
        SimpleDateFormat ft = new SimpleDateFormat(formatText);
        return ft.format(longTime);
    }

    /**
     * @desc 获取格式化后的时间字符串
     *
     * @param date
     *            日期
     * @param formatText
     *            格式化字符串
     * @return
     */
    public static String getString(Date date, String formatText) {
        SimpleDateFormat ft = new SimpleDateFormat(formatText);
        return ft.format(date);
    }

    /**
     * 获取当前日期与时间
     */
    public static String getCurrentDateTime(String formatText) {
        SimpleDateFormat ft = new SimpleDateFormat(formatText);
        return ft.format(new Date());
    }

    /**
     * 获取当前日期与时间
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat ft = new SimpleDateFormat(Format.DEFAULT_DATETIME);
        return ft.format(new Date());
    }

    /**
     * 获取当前时间的小时
     */
    public static int getCurrentDateHour(Long time) {
        Date date = new Date(time);
        return date.getHours();
    }

    /**
     * convent method to get days after or before 根据天数偏移量计算日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getDateAfter(Date date, int days) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 返回当前时间24小时 时数组
     *
     * @return
     */
    public static ArrayList<String> getCurrentDateBeforeHour(int intervals) {
        ArrayList<String> pastHoursList = new ArrayList<>();
        for (int i = 0; i < intervals; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, i);
            pastHoursList.add(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
        }
        return pastHoursList;
    }

    /**
     * 获取过去 任意天内的日期数组
     *
     * @param intervals
     *            intervals天内
     * @param formatText
     *            格式化字符串
     * @return 日期数组
     */
    public static ArrayList<String> getCurrentDateBeforeDate(int intervals, String formatText) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = 0; i < intervals; i++) {
            pastDaysList.add(getPastDate(i, formatText));
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @param formatText
     *            格式化字符串
     * @return
     */
    public static String getPastDate(int past, String formatText) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(formatText);
        String result = format.format(today);
        return result;
    }

    /**
     * 获取当前日期与时间的字符串形式 使用DateTimeFormatter格式化时间
     *
     * @return
     */
    public static String getCurrentDateStr() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Format.DEFAULT_DATETIME);
        LocalDateTime localDateTime = LocalDateTime.now();
        String format1 = dateTimeFormatter.format(localDateTime);
        return format1;
    }

    /**
     * 获取当前日期与时间的字符串形式 使用DateTimeFormatter格式化时间
     *
     * @return
     */
    public static String getDateStr(Date date, String formatStr) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatStr);
        String format1 = dateTimeFormatter.format(localDateTime);
        return format1;
    }

    /**
     * 获取当前日期为yyyy-MM-dd
     *
     * @return
     */
    public static Date getCurrentDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
        LocalDateTime localDateTime = LocalDateTime.now();
        String format1 = dateTimeFormatter.format(localDateTime);
        return getDateByStr(format1);
    }

    /**
     * 将str转换为date
     *
     * @return
     */
    public static Date getDateByStr(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Format.DEFAULT_DATETIME);
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, dateTimeFormatter);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * 将str转换为date
     *
     * @return
     */
    public static Date getDateByStr(String dateStr, String dateFormatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatStr);
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, dateTimeFormatter);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * getMonthsBetween(查询两个日期相隔的月份 )
     *
     * @param startDate
     *            订单开始日期1 (格式yyyy-MM-dd)
     * @param endDate
     *            截止日期2 (格式yyyy-MM-dd)
     * @return
     */
    public static int getMonthsBetween(Date startDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDate);
        c2.setTime(endDate);
        int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        int month = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        return Math.abs(year * 12 + month);
    }

    /**
     * 判断两个日期是否在同一天
     *
     * @param date1
     * @param date2
     * @return
     */

    public static boolean isTheSameDay(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
            && (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * @desc: 获取时间戳
     * @author zhuyd
     * @param date
     * @return Long
     */
    public static Long getTimestamp(Date date) {
        Long time;
        try {
            if (isNotEmpty(date)) {
                time = date.getTime();
                return time;
            }
        } catch (Exception e) {
            log.error("covert failed.");
        }
        return null;
    }

    /**
     * @desc 判断日期是否不为空
     * @author zhuyd
     * @param date
     * @return boolean
     */
    public static boolean isNotEmpty(Date date) {
        if (date == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @desc 判断日期是否为空
     * @author zhuyd
     * @param date
     * @return boolean
     */
    public static boolean isEmpty(Date date) {
        return !isNotEmpty(date);
    }

    /**
     * @desc 得到两个时间的间隔天数
     * @return boolean
     */
    public static int getSpanDaysFromTowDates(Date startDate, Date endDate) {
        int days = (int)((endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000));
        return days;
    }

    /**
     * 获得当天零时零分零秒
     * 
     * @return
     */
    public static Date getInitDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * @desc 得到两个时间的间隔小时
     * @return boolean
     */
    public static int hourDiff(Date startDate, Date endDate) {
        int hours = new BigDecimal(endDate.getTime() - startDate.getTime())
            .divide(new BigDecimal(60 * 60 * 1000), 0, BigDecimal.ROUND_HALF_UP).intValue();
        return hours;
    }

    /**
     * 日期赋值
     * 
     * @param date
     * @return
     */
    private static Calendar getCalendarOfWeek(Date date) {
        Date dd = new Date();
        if (StringTool.isNotEmpty(date)) {
            dd = date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(dd);
        return cal;
    }

    /**
     * @desc 获取周一,传null为当前日期
     * @param date
     * @return date
     */
    public static Date getMondayDate(Date date) {
        Calendar cal = getCalendarOfWeek(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();

    }

    /**
     * @desc 获取周日,传null为当前日期
     * @param date
     * @return date
     */
    public static Date getSundayDate(Date date) {
        Calendar cal = getCalendarOfWeek(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();

    }

    /**
     * 日期赋值
     *
     * @param date
     * @return
     */
    public static Calendar getCalendarOfMonth(Date date) {
        Date dd = new Date();
        if (StringTool.isNotEmpty(date)) {
            dd = date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dd);
        return cal;
    }

    /**
     * @desc 获取月份的第一天,传null为当前日期
     * @param date
     * @return date
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar cal = getCalendarOfMonth(date);
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * @desc 获取月份的第一天,传null为当前日期
     * @param date
     * @return date
     */
    public static Date getMonthLastDay(Date date) {
        Calendar cal = getCalendarOfMonth(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return cal.getTime();
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat(Format.DEFAULT_DATE);
        System.out.println(sdf.format(getMondayDate(null)));
        System.out.println(sdf.format(getSundayDate(null)));
        System.out.println(sdf.format(getMonthFirstDay(null)));
        System.out.println(sdf.format(getMonthLastDay(null)));

        Date date = new Date("2019/02/05");
        System.out.println(sdf.format(getMondayDate(date)));
        System.out.println(sdf.format(getSundayDate(date)));
        System.out.println(sdf.format(getMonthFirstDay(date)));
        System.out.println(sdf.format(getMonthLastDay(date)));

        date = new Date("2020/02/05");
        System.out.println(sdf.format(getMondayDate(date)));
        System.out.println(sdf.format(getSundayDate(date)));
        System.out.println(sdf.format(getMonthFirstDay(date)));
        System.out.println(sdf.format(getMonthLastDay(date)));

        date = new Date("2018/02/05");
        System.out.println(sdf.format(getMondayDate(date)));
        System.out.println(sdf.format(getSundayDate(date)));
        System.out.println(sdf.format(getMonthFirstDay(date)));
        System.out.println(sdf.format(getMonthLastDay(date)));

        date = new Date("2017/02/05");
        System.out.println(sdf.format(getMondayDate(date)));
        System.out.println(sdf.format(getSundayDate(date)));
        System.out.println(sdf.format(getMonthFirstDay(date)));
        System.out.println(sdf.format(getMonthLastDay(date)));

        date = new Date("2016/02/05");
        System.out.println(sdf.format(getMondayDate(date)));
        System.out.println(sdf.format(getSundayDate(date)));
        System.out.println(sdf.format(getMonthFirstDay(date)));
        System.out.println(sdf.format(getMonthLastDay(date)));
        String str = "2020-04-02T03:35:13.739055275Z";

    }
}
