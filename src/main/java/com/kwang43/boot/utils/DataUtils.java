package com.kwang43.boot.utils;

import java.math.BigDecimal;
import java.util.Date;

public class DataUtils {

    private DataUtils() {
    }

    public static String nvl(String string, String value) {
        return StringUtils.isEmpty(string) ? value : string;
    }

    public static Date nvl(Date date, Date value) {
        return null == date ? value : date;
    }

    public static String nvl(String string) {
        return nvl(string, "");
    }

    public static BigDecimal nvl(BigDecimal value) {
        return nvl(value, BigDecimal.ZERO);
    }

    public static BigDecimal nvl(BigDecimal value, BigDecimal defaultValue) {
        return null == value ? defaultValue : value;
    }
}
