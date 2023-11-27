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


    //for pagination
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Integer DEFAULT_PAGE_INDEX = 0;
    public static final Integer DEFAULT_MAX_PAGE_SIZE = 3000;
}