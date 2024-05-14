package com.kwang43.boot.controller;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.model.dto.ForgetPasswordDto;
import com.kwang43.boot.model.dto.LoginDto;
import com.kwang43.boot.service.SystemService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("")
public class SystemController {

    @Autowired
    private SystemService systemService;

    /**
     * 登录方法
     *
     * @param loginDto
     * @return com.kwang43.boot.config.Result
     **/
    @PostMapping("/login")
    @ApiOperation(value="登录", notes="")
    public Response<Object> login(@RequestBody LoginDto loginDto) {
        return systemService.login(loginDto);
    }

    /**
     * 忘记密码
     *
     * @param forgetPasswordDto
     * @return com.kwang43.boot.config.Result
     **/
    @PostMapping("/forget_password")
    @ApiOperation(value="忘记密码", notes="")
    public Response<Object> forget_password(@RequestBody ForgetPasswordDto forgetPasswordDto) {
        return systemService.forget_password(forgetPasswordDto);
    }
}
