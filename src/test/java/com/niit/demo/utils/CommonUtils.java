package com.niit.demo.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class contains utilities like checking future date or getting a back date etc
 */
public class CommonUtils {
    /**
     * Get a date "days" back from today with format "format"
     *
     * @param days
     * @param format
     * @return
     */
    public static String getDate(int days, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        Date date = DateUtils.addDays(new Date(), days);
        return simpleDateFormat.format(date);
    }

    /**
     * Check if the gives date is a future date or not
     *
     * @param date
     * @param format
     * @return
     */
    public static boolean dateIsFutureDate(String date, String format) {
        if (LocalDate.parse(date, DateTimeFormatter.ofPattern(format)).isAfter(LocalDate.now())) {
            return true;
        }
        return false;
    }
}
