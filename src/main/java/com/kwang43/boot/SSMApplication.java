package com.kwang43.boot;

import com.kwang43.boot.utils.StringUtils;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.regex.Pattern;

/**
 * 功能：以XML方式整合SSM.
 * MapperScan:扫描Mybatis接口文件
 * 作者：kwang43
 * 日期：2023/10/11 15:17
 */

@EnableScheduling
@EnableAdminServer
@SpringBootApplication
@ComponentScan(value = "com.kwang43")
public class SSMApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSMApplication.class, args);
    }
}