package com.example.basicmvp.notes.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mithilesh on 8/30/16.
 */
public class DateTimeUtils {
    public static String formateToDate(Date now) {
        String pattern = "dd MMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(now);

        return date;
    }
}
