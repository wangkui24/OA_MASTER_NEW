package com.kwang43.boot.controller;

import com.kwang43.boot.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 功能：验证码操作处理
 * 作者：kwang43
 * 日期：2023/12/25 11:05
 */
@RestController
public class CaptchaController {
    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/captcha")
    public void generateCaptcha(HttpServletResponse response) throws IOException {
        // 生成验证码图片
        BufferedImage image = captchaService.generateCaptchaImage();

        // 设置响应头
        response.setContentType("image/png");
        response.setCharacterEncoding("UTF-8");

        // 将图片写入响应输出流
        ImageIO.write(image, "png", response.getOutputStream());
    }
}

