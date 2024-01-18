package com.kwang43.boot.utils;

import com.kwang43.boot.config.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.kwang43.boot.utils.HttpStatus.ERROR;

@Slf4j
@Component
public class CaptchaGenerator {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Response generateCaptcha() throws Exception {
        try {
//            int i = 1/0;
//            System.out.println(i);
            // 生成验证码图片
            BufferedImage image = new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.setFont(new Font("Arial", Font.PLAIN, 26));
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 100, 50);
            g.setColor(Color.BLACK);

            // 生成随机字符串并计算宽度
            String randomString = generateRandomString();
            FontMetrics fontMetrics = g.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(randomString);

            // 计算居中位置
            int x = (100 - stringWidth) / 2;
            int y = 40;

            // 绘制字符串
            g.drawString(randomString, x, y);
            g.dispose();

            // 将验证码图片转换为Base64编码
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            byte[] bytes = outputStream.toByteArray();
            String base64 = Base64.getEncoder().encodeToString(bytes);

            // 生成UUID并存储在Redis中，设置有效期为2分钟
            String uuid = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(uuid, randomString, 2, TimeUnit.MINUTES);
            HashMap<String, Object> map = new HashMap<>();
            map.put("image", base64);
            log.info("base64: {}", base64);
            map.put("uuid", uuid);
            log.info("uuid: {}", uuid);
            return new Response<>(map);
        }
        catch (Exception e){
            log.error("捕获到异常: {}", e.getMessage());
            return new Response<>(ERROR, "生成验证码失败");
        }
    }

    private String generateRandomString() {
        int length = 4;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (Math.random() * 26 + 'A');
            sb.append(c);
        }
        return sb.toString();
    }
}

