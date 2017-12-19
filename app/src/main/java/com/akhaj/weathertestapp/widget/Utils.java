package com.akhaj.weathertestapp.widget;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Date long2Date(long value) {
        if (value == 0) {
            return null;
        }

        Date date;
        try {
            date = new Date(value);
        } catch (Exception e) {
            date = new Date();
        }
        return date;
    }

    public static String date2DayMonthString(Date date) {
        if (date != null) {
            return new SimpleDateFormat("EEE, dd MMM", java.util.Locale.getDefault()).format(date);
        } else {
            return "";
        }
    }

    public static String convertTemp(String t) {
        return String.valueOf(Math.round(Double.parseDouble(t)));
    }

}
