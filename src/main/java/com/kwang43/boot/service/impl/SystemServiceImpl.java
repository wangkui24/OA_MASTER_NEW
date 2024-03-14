package com.kwang43.boot.service.impl;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.domain.SystemRole;
import com.kwang43.boot.domain.SystemUser;
import com.kwang43.boot.domain.SystemUserDto;
import com.kwang43.boot.model.BaseEnum;
import com.kwang43.boot.model.dto.LoginDto;
import com.kwang43.boot.repository.EmployeeRepository;
import com.kwang43.boot.repository.SystemRoleRepository;
import com.kwang43.boot.repository.SystemUserRepository;
import com.kwang43.boot.service.SystemService;
import com.kwang43.boot.utils.HttpStatus;
import com.kwang43.boot.utils.JwtUtils;
import com.kwang43.boot.utils.RedisUtils;
import com.kwang43.boot.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private SystemRoleRepository systemRoleRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Response<Object> login(LoginDto loginDto) {
        try {
            if (redisUtils.exists(loginDto.getUuid())) {
                Object codeValue = redisUtils.get(loginDto.getUuid());
                log.info("codeValue: [{}]", codeValue);
                if (loginDto.getCode().equals(codeValue)) {
                    List<SystemUser> accounts = systemUserRepository.findByUsername(loginDto.getUsername());
                    if (StringUtils.isEmpty(accounts)) {
                       return new Response<>(HttpStatus.NO_CONTENT, "该用户不存在!");
                    }
                    else {
                       if (loginDto.getPassword().equals(accounts.get(0).getPassword())) {
                           HashMap<Object, Object> map = new HashMap<>();
                           SystemUser systemUser = accounts.get(0);
                           if (systemUser.getStatus().equals(BaseEnum.SystemUser.StatusEnum.INACTIVE)) {
                               return new Response<>(HttpStatus.FORBIDDEN, "您的账号未激活!");
                           }
                           else if (systemUser.getStatus().equals(BaseEnum.SystemUser.StatusEnum.BLOCKED)) {
                               return new Response<>(HttpStatus.FORBIDDEN, "您的账号被禁用!");
                           }
                           SystemUserDto systemUserDto = new SystemUserDto(systemUser);
                           String token = jwtUtils.generateToken(loginDto.getUsername(),systemUser.getEmail(), systemUserDto.getRoleName());
                           map.put("token", token);
                           map.put("user_info", systemUserDto);
                           return new Response<>("登陆成功!", map);
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
            log.info("error: [{}]", e.getMessage());
            return null;
        }
    }
}
