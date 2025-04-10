package com.kwang43.boot.controller;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.model.response.CaptchaResponse;
import com.kwang43.boot.service.CaptchaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaController {
    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/captcha")
    @ApiOperation(value="获取图片验证码", notes="")
    public Response<CaptchaResponse> getCaptcha(@RequestParam(required = false) String old_uuid) {
        return new Response<>(captchaService.generateCaptchaImage(old_uuid));
    }
}

