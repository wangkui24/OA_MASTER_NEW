package com.kwang43.boot.core;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/16 12:55
 */
public class Const {
    private Const() {
    }

    public static  final String IN_SERVICE = "IN_SERVICE";
    public static  final String JOINING_IN = "JOINING_IN";
    public static  final String RESIGNING = "RESIGNING";
    public static  final String RESIGNED = "RESIGNED";

    public static  final String INACTIVE = "INACTIVE";
    public static  final String ACTIVE = "ACTIVE";
    public static  final String BLOCKED = "BLOCKED";

    public static  final String PENDING = "PENDING";
    public static  final String APPROVED = "APPROVED";
    public static  final String REJECTED = "REJECTED";
    public static  final String CANCEL = "CANCEL";

    public static final String LOG_MDC_ID = "trace_id";

    //for pagination
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_PAGE_INDEX = "0";
    public static final String DEFAULT_MAX_PAGE_SIZE = "3000";
}