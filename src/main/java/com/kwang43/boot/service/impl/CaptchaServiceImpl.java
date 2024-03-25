package com.kwang43.boot.service.impl;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.core.MessageCode;
import com.kwang43.boot.service.CaptchaService;
import com.kwang43.boot.utils.HttpStatus;
import com.kwang43.boot.utils.RedisUtils;
import com.kwang43.boot.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 40;
    private static final int CODE_COUNT = 4;
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Response<Object> generateCaptchaImage(String old_uuid) {
        // 如果提供了上一次生成验证码的uuid且其在redis中存在，则删除旧的验证码
        // 保证同一设备同一时间在redis最多只能有一条数据
        if (StringUtils.isNotEmpty(old_uuid) && redisUtils.exists(old_uuid)) {
            redisUtils.remove(old_uuid);
        }
        try {
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
            redisUtils.set(uuid, randomString, 2);
            HashMap<String, Object> map = new HashMap<>();
            map.put("image", base64);
            map.put("uuid", uuid);
            log.info("base64: [{}], uuid: [{}]", base64, uuid);
            return new Response<>(map);
        } catch (Exception e) {
            log.error("捕获到异常: [{}]", e.getMessage());
            return new Response<>(HttpStatus.ERROR, MessageCode.Captcha.CREATE_CAPTCHA_FAILED);
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
