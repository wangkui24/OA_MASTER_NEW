package com.kwang43.boot.service.impl;

import com.kwang43.boot.config.DecryptService;
import com.kwang43.boot.config.Response;
import com.kwang43.boot.core.MessageCode;
import com.kwang43.boot.domain.SystemUser;
import com.kwang43.boot.domain.SystemUserDto;
import com.kwang43.boot.model.BaseEnum;
import com.kwang43.boot.model.dto.LoginDto;
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


@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private DecryptService decryptService;

    @Override
    public Response<Object> login(LoginDto loginDto) {
        try {
            if (redisUtils.exists(loginDto.getUuid())) {
                Object codeValue = redisUtils.get(loginDto.getUuid());
                log.info("codeValue: [{}]", codeValue);
                if (loginDto.getCode().equals(codeValue)) {
                    List<SystemUser> accounts = systemUserRepository.findByUsername(loginDto.getUsername());
                    if (StringUtils.isEmpty(accounts)) {
                        return new Response<>(HttpStatus.NO_CONTENT, MessageCode.Account.ACCOUNT_NOT_EXIST);
                    } else {
                        String decryptedPassword = decryptService.decrypt(accounts.get(0).getPassword());
//                        log.info("decryptedPassword: {}", decryptedPassword);
//                        log.info(decryptService.decrypt(loginDto.getPassword()));
                        if (decryptService.decrypt(loginDto.getPassword()).equals(decryptedPassword)) {
                            SystemUser systemUser = accounts.get(0);
                            if (systemUser.getStatus().equals(BaseEnum.SystemUser.StatusEnum.INACTIVE)) {
                                return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Account.ACCOUNT_IS_INACTIVE);
                            } else if (systemUser.getStatus().equals(BaseEnum.SystemUser.StatusEnum.BLOCKED)) {
                                return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Account.ACCOUNT_IS_BLOCKED);
                            }
                            SystemUserDto systemUserDto = new SystemUserDto(systemUser);
                            String token = jwtUtils.generateToken(loginDto.getUsername(), systemUser.getEmail(), systemUserDto.getRoleName());
                            HashMap<Object, Object> map = new HashMap<>();
                            map.put("token", token);
                            map.put("user_info", systemUserDto);
                            return new Response<>("登陆成功!", map);
                        } else {
                            return new Response<>(HttpStatus.WARN, MessageCode.Account.PASSWORD_ERROR);
                        }
                    }
                } else {
                    return new Response<>(HttpStatus.ERROR, MessageCode.Captcha.CAPTCHA_ERROR);
                }
            } else {
                return new Response<>(HttpStatus.ERROR, MessageCode.Captcha.CAPTCHA_ERROR_OR_EXPIRE);
            }
        } catch (Exception e) {
            log.info("error: [{}]", e.getMessage());
            return null;
        }
    }
}
