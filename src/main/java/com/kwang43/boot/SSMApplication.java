package com.kwang43.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能：以XML方式整合SSM.
 * MapperScan:扫描Mybatis接口文件
 * 作者：kwang43
 * 日期：2023/10/11 15:17
 */

@SpringBootApplication
public class SSMApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSMApplication.class, args);
    }
}