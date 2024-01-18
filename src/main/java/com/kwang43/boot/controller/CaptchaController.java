package com.kwang43.boot.controller;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.utils.CaptchaGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaController {
    @Autowired
    private CaptchaGenerator captchaGenerator;

    @GetMapping("/captcha")
    @ApiOperation(value="获取图片验证码", notes="")
    public Response getCaptcha() throws Exception {
        return captchaGenerator.generateCaptcha();
    }
}

