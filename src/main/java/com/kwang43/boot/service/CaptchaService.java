package com.kwang43.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Slf4j
@Service
public class CaptchaService {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 40;
    private static final int CODE_COUNT = 4;
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public BufferedImage generateCaptchaImage() {
        // 创建缓冲图像
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // 设置背景色
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 设置字体和颜色
        Font font = new Font("Arial", Font.BOLD, 24);
        g.setFont(font);
        g.setColor(Color.BLACK);

        // 绘制随机字符
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < CODE_COUNT; i++) {
            char c = CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length()));
            code.append(c);
            g.drawString(String.valueOf(c), 15 * i + 20, 30);
        }
        log.info("code:{}",code);

        // 释放资源
        g.dispose();

        return image;
    }
}