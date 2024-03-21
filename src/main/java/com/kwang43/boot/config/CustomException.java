package com.kwang43.boot.config;

// 定义一个自定义异常类
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
