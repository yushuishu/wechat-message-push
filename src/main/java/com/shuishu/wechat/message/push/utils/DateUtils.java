package com.shuishu.wechat.message.push.utils;


import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：谁书-ss
 * @date ：2023-03-23 21:23
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：公共工具类
 * <p></p>
 */
public class DateUtils {

    /**
     * 获取 阳历
     *
     * @return -
     */
    public static String getLunarDateStr() {
        //今天的阳历 yyyy-MM-dd
        String toDateStr = DateUtil.format(new Date(), DatePattern.NORM_DATE_FORMAT);
        //今天的农历 通过公历构建
        ChineseDate chineseDate = new ChineseDate(DateUtil.parseDate(toDateStr));

        // 一月
        String chineseMonth = chineseDate.getChineseMonth();
        // 正月
        chineseDate.getChineseMonthName();
        // 初一
        String chineseDay = chineseDate.getChineseDay();
        // 庚子年甲申月癸卯日
        String cyclicalYmd = chineseDate.getCyclicalYMD();
        // 庚子
        chineseDate.getCyclical();
        // 生肖：鼠
        chineseDate.getChineseZodiac();
        // 传统节日（部分支持，逗号分隔）：春节
        String festivals = chineseDate.getFestivals();
        if (StringUtils.hasText(festivals)) {
            return "农历" + chineseMonth + chineseDay + "，" + cyclicalYmd + "，" + festivals;
        }
        return "农历" + chineseMonth + chineseDay + "，" + cyclicalYmd;
    }

    /**
     * 获取汉字的今天周几
     *
     * @return -
     */
    public static String getChinaWeek() {
        int week = DateUtil.thisDayOfWeek();
        //周的汉字
        return switch (week) {
            case 1 -> "日";
            case 2 -> "一";
            case 3 -> "二";
            case 4 -> "三";
            case 5 -> "四";
            case 6 -> "五";
            case 7 -> "六";
            default -> "";
        };
    }


    /**
     * 得到二个日期间的间隔天数
     *
     * @param date1 -
     * @param date2 -
     * @return -
     */
    public static int getDayByTwoDay(String date1, String date2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0L;
        try {
            java.util.Date date = myFormatter.parse(date1);
            java.util.Date myDate = myFormatter.parse(date2);
            day = (date.getTime() - myDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return 0;
        }
        return (int) day;
    }


}
