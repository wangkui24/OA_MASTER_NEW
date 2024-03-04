package com.kwang43.boot.controller;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.model.dto.LoginDto;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("")
public class SystemController {

    /**
     * 登录方法
     *
     * @param loginDto
     * @return com.kwang43.boot.config.Result
     **/
//    @PostMapping("/login")
//    @ApiOperation(value="登录", notes="")
//    public Response<Boolean> login(@RequestBody LoginDto loginDto) {
//        return employeeService.login(loginDto);
//    }
}
