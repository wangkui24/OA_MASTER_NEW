package com.kwang43.boot.service.impl;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.domain.SystemUser;
import com.kwang43.boot.model.dto.LoginDto;
import com.kwang43.boot.repository.EmployeeRepository;
import com.kwang43.boot.repository.SystemUserRepository;
import com.kwang43.boot.service.SystemService;
import com.kwang43.boot.utils.HttpStatus;
import com.kwang43.boot.utils.RedisUtils;
import com.kwang43.boot.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public Response<Object> login(LoginDto loginDto) {
        try {
            if (redisUtils.exists(loginDto.getUuid())) {
                Object codeValue = redisUtils.get(loginDto.getUuid());
                log.info("codeValue: {}", codeValue);
                if (loginDto.getCode().equals(codeValue)) {
                    List<SystemUser> accounts = systemUserRepository.findByUsername(loginDto.getUsername());
                    if (StringUtils.isEmpty(accounts)) {
                       return new Response<>(HttpStatus.NO_CONTENT, "该用户不存在!");
                    }
                    else {
                       if (loginDto.getPassword().equals(accounts.get(0).getPassword())) {
                           return new Response<>(HttpStatus.SUCCESS, "登录成功!");
                       }
                       else {
                           return new Response<>(HttpStatus.WARN, "密码错误!");
                       }
                    }
                }
                else {
                    return new Response<>(HttpStatus.ERROR, "验证码错误,请重新输入!");
                }
            }
            else {
                return new Response<>(HttpStatus.ERROR, "验证码错误或已过期,请刷新验证码!");
            }
        } catch (Exception e) {
            log.info("error: {}", e.getMessage());
            return null;
        }
    }
}
