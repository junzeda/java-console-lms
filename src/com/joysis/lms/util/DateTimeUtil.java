package com.joysis.lms.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDateTimeString() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(FORMATTER);
    }

    public static Timestamp getCurrentSqlTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
