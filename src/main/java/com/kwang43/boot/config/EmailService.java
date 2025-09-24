package com.kwang43.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendPasswordResetEmail(String toEmail, String resetUrl) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(toEmail);
        mail.setSubject("密码重置");
        mail.setText("请点击以下链接重置您的密码：" + resetUrl);
        mail.setFrom("wangkui24@foxmail.com");
        log.info("发送邮件--start");
        javaMailSender.send(mail);
        log.info("发送邮件--end");
    }
}
