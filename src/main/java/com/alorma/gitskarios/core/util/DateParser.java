package com.alorma.gitskarios.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateParser {

    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public long getMillisFromDateClearDay(String createdAt) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
            Date date = sdf.parse(createdAt);

            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);

            calendar.set(Calendar.YEAR, 1970);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            return calendar.getTimeInMillis();
        } catch (Exception e) {
            //TODO please, remove this clause :D
            return 0;
        }
    }
}
