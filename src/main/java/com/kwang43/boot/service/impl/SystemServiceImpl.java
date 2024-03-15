package com.kwang43.boot.service.impl;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.core.MessageCode;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemUserRepository systemUserRepository;

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
//                        throw new DataIntegrityViolationException(MessageCode.Account.ACCOUNT_NOT_EXIST);
                       return new Response<>(HttpStatus.NO_CONTENT, MessageCode.Account.ACCOUNT_NOT_EXIST);
                    }
                    else {
                       if (loginDto.getPassword().equals(accounts.get(0).getPassword())) {
                           SystemUser systemUser = accounts.get(0);
                           if (systemUser.getStatus().equals(BaseEnum.SystemUser.StatusEnum.INACTIVE)) {
//                               throw new DataIntegrityViolationException(MessageCode.Account.ACCOUNT_IS_INACTIVE);
                               return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Account.ACCOUNT_IS_INACTIVE);
                           }
                           else if (systemUser.getStatus().equals(BaseEnum.SystemUser.StatusEnum.BLOCKED)) {
//                               throw new DataIntegrityViolationException(MessageCode.Account.ACCOUNT_IS_BLOCKED);
                               return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Account.ACCOUNT_IS_BLOCKED);
                           }
                           SystemUserDto systemUserDto = new SystemUserDto(systemUser);
                           String token = jwtUtils.generateToken(loginDto.getUsername(),systemUser.getEmail(), systemUserDto.getRoleName());
                           HashMap<Object, Object> map = new HashMap<>();
                           map.put("token", token);
                           map.put("user_info", systemUserDto);
                           return new Response<>("登陆成功!", map);
                       }
                       else {
//                           throw new DataIntegrityViolationException(MessageCode.Account.PASSWORD_ERROR);
                           return new Response<>(HttpStatus.WARN, MessageCode.Account.PASSWORD_ERROR);
                       }
                    }
                }
                else {
//                    throw new DataIntegrityViolationException(MessageCode.Account.CAPTCHA_ERROR);
                    return new Response<>(HttpStatus.ERROR, MessageCode.Account.CAPTCHA_ERROR);
                }
            }
            else {
//                throw new DataIntegrityViolationException(MessageCode.Account.CAPTCHA_ERROR_OR_EXPIRE);
                return new Response<>(HttpStatus.ERROR, MessageCode.Account.CAPTCHA_ERROR_OR_EXPIRE);
            }
        } catch (Exception e) {
            log.info("error: [{}]", e.getMessage());
            return null;
        }
    }
}
