package com.youlintech.zodiac.chatai.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtil {
    public static Date getNowDate() {
        // 获取当前系统日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 将LocalDateTime转换为Date
        Date date = convertToDate(currentDateTime);
        return date;
    }
    private static Date convertToDate(LocalDateTime localDateTime) {
        // 获取系统默认时区的ZoneId
        ZoneId zoneId = ZoneId.systemDefault();

        // 将LocalDateTime转换为ZonedDateTime
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);

        // 将ZonedDateTime转换为Instant，再转换为Date
        return Date.from(zonedDateTime.toInstant());
    }
}
