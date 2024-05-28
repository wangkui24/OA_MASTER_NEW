package com.kwang43.boot.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date getNowTime() {
        return Calendar.getInstance().getTime();
    }
}
