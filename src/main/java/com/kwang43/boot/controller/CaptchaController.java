package com.kwang43.boot.controller;

import com.kwang43.boot.config.Result;
import com.kwang43.boot.utils.CaptchaGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
public class CaptchaController {
    @Autowired
    private CaptchaGenerator captchaGenerator;

    @GetMapping("/captcha")
    @ApiOperation(value="获取图片验证码", notes="")
    public Result getCaptcha(HttpServletResponse response) throws Exception {
        Result res = Result.success();
        captchaGenerator.generateCaptcha(res);
        return res;
    }

}

