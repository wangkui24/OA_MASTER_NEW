package com.kwang43.boot.utils;

import com.kwang43.boot.config.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CaptchaGenerator {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Result generateCaptcha(Result res) throws Exception {
        // 生成验证码图片
        BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 100, 50);
        g.setColor(Color.BLACK);
        g.drawString(generateRandomString(), 20, 40);
        g.dispose();

        // 将验证码图片转换为Base64编码
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        byte[] bytes = outputStream.toByteArray();
        String base64 = Base64.getEncoder().encodeToString(bytes);

        // 生成UUID并存储在Redis中，设置有效期为2分钟
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(uuid, base64, 2, TimeUnit.MINUTES);

        res.put("base64", base64);
        res.put("uuid", uuid);
        return res;
    }

    private String generateRandomString() {
        int length = 5;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (Math.random() * 26 + 'A');
            sb.append(c);
        }
        return sb.toString();
    }
}
