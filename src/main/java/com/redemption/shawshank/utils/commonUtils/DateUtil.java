package com.redemption.shawshank.utils.commonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Author : xingshukui .
 * Date : 2017/7/7.
 * Desc :
 */
public class DateUtil {


    public static java.sql.Date getCurrentDateTime() {
        Calendar newcalendar = null;
        newcalendar = Calendar.getInstance();
        String year = String.valueOf(newcalendar.get(1));
        String month = String.valueOf(newcalendar.get(2) + 1);
        String day = String.valueOf(newcalendar.get(5));
        String hour = String.valueOf(newcalendar.get(11));
        String min = String.valueOf(newcalendar.get(12));
        String sec = String.valueOf(newcalendar.get(12));
        return stringToSqlDate(year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec);
    }

    public static java.sql.Date stringToSqlDate(String str) {
        return stringToUtilDate(str) != null && str.length() >= 1?new java.sql.Date(stringToUtilDate(str).getTime()):null;
    }

    public static java.util.Date stringToUtilDate(String str) {
        if(null != str && str.length() > 0) {
            try {
                return str.length() <= "yyyy-MM-dd".length()?(new SimpleDateFormat("yyyy-MM-dd")).parse(str):(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(str);
            } catch (ParseException var2) {
                return null;
            }
        } else {
            return null;
        }
    }


}
