package com.zcf.world.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yuan
 * @date 2019/2/12 0012
 */
public class DataTimeUtils {
    /**
     * 把Date转化为 LocalDateTime
     *
     * @param date 传入Date
     * @return 返回的 LocalDateTime
     */
    public static LocalDateTime changeDateToLocalDate(Date date) {
        Instant instant=date.toInstant();
        ZoneId zoneId=ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 根据指定时间获取每个月所有星期的开始时间和结束时间
     *
     * @param year  某年
     * @param month 某月
     * @return 每周开始和结束时间
     */
    public static List<String> weeKStartAndEndDateOfMonth(Integer year, Integer month) {
        LocalDate date=LocalDate.of(year, month, 1);

        // 获取该月第一天
        LocalDate firstDay=date.with(TemporalAdjusters.firstDayOfMonth());
        // 获取该月最后一天
        LocalDate lastDay=date.with(TemporalAdjusters.lastDayOfMonth());
        // 该月的第一个周一
        LocalDate start=date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        List<String> list=new ArrayList<>();
        // 处理每个月的1号不是周一的情况
        if (!firstDay.equals(start)) {
            StringBuilder strbur=new StringBuilder();
            strbur.append(firstDay.toString())
                    .append("~")
                    .append(start.plusDays(-1).toString());
            list.add(strbur.toString());
        }
        while (start.isBefore(lastDay)) {
            StringBuilder strbur=new StringBuilder();
            strbur.append(start.toString());
            LocalDate temp=start.plusDays(6);
            if (temp.isBefore(lastDay)) {
                strbur.append("~")
                        .append(temp.toString());
            } else {
                strbur.append("~")
                        .append(lastDay.toString());
            }
            list.add(strbur.toString());
            start=start.plusWeeks(1);
        }
        return list;
    }

    /**
     * 年月日格式 LocalDate\LocalDateTime
     *
     * @param temporal
     * @return
     */
    public static String formatYYYYMMDD(TemporalAccessor temporal) {
        return format(temporal, "yyyy-MM-dd");
    }

    /**
     * 时分秒格式化
     *
     * @param localTime
     * @return
     */
    public static String formatHHmmss(LocalTime localTime) {
        return format(localTime, "HH:mm:ss");
    }

    /**
     * 年月日时分秒格式 LocalDate\LocalDateTime
     *
     * @param temporal
     * @return
     */
    public static String formatYMDHMS(TemporalAccessor temporal) {
        return format(temporal, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 字符串解析成LocalDateTime
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String time, String pattern) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * 字符串解析成LocalDate
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDate parseLocalDate(String time, String pattern) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(time, formatter);
    }

    /**
     * 字符串解析成Date
     *
     * @param time    格式化的时间
     * @param pattern 匹配的格式
     * @return
     */
    public static Date parse2Date(String time, String pattern) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
        return Date.from(LocalDate.parse(time, formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 格式化
     */
    private static String format(TemporalAccessor temporal, String pattern) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
        return formatter.format(temporal);
    }

}
