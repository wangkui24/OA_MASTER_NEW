package com.kwang43.boot.service.impl;

import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.domain.OaUsers;
import com.kwang43.boot.domain.OaUsersDto;
import com.kwang43.boot.repository.EmployeeRepository;
import com.kwang43.boot.repository.OaUsersRepository;
import com.kwang43.boot.utils.DecryptUtils;
import com.kwang43.boot.config.EmailService;
import com.kwang43.boot.config.Response;
import com.kwang43.boot.core.MessageCode;
import com.kwang43.boot.model.BaseEnum;
import com.kwang43.boot.model.dto.ForgetPasswordDto;
import com.kwang43.boot.model.dto.LoginDto;
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
    private OaUsersRepository oaUsersRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private DecryptUtils decryptService;

    @Autowired
    private EmailService emailService;

    @Override
    public Response<Object> login(LoginDto loginDto) {
        try {
            if (redisUtils.exists(loginDto.getUuid())) {
                Object codeValue = redisUtils.get(loginDto.getUuid());
                log.info("codeValue: [{}]", codeValue);
                if (loginDto.getCode().equals(codeValue)) {
                    List<Employee> employeeAccounts = employeeRepository.findByEmail(loginDto.getEmail());
                    List<OaUsers> oaAccounts = oaUsersRepository.findByEmail(loginDto.getEmail());
                    if (StringUtils.isEmpty(employeeAccounts)) {
                        return new Response<>(HttpStatus.NO_CONTENT, MessageCode.Account.ACCOUNT_NOT_EXIST);
                    } else {
                        String decryptedPassword = decryptService.decrypt(employeeAccounts.get(0).getPassword());
                        if (decryptService.decrypt(loginDto.getPassword()).equals(decryptedPassword)) {
                            Employee employee = employeeAccounts.get(0);
                            if (employee.getStatus().equals(BaseEnum.Employee.AccountStatusEnum.INACTIVE)) {
                                return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Account.ACCOUNT_IS_INACTIVE);
                            } else if (employee.getStatus().equals(BaseEnum.Employee.AccountStatusEnum.BLOCKED)) {
                                return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Account.ACCOUNT_IS_BLOCKED);
                            }
                            OaUsersDto oaUsersDto = new OaUsersDto(employee);
                            String token = jwtUtils.generateToken(loginDto.getEmail(), employee.getEmail(), oaUsersDto.getRoleName());
                            HashMap<Object, Object> map = new HashMap<>();
                            map.put("token", token);
                            map.put("user_info", oaUsersDto);
                            return new Response<>(map);
                        } else {
                            return new Response<>(HttpStatus.ERROR, MessageCode.Account.PASSWORD_ERROR);
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

    @Override
    public Response<Object> forget_password(ForgetPasswordDto forgetPasswordDto) {
        try {
            List<OaUsers> accountsByUsername = oaUsersRepository.findByUsername(forgetPasswordDto.getUsername());
            if (StringUtils.isEmpty(accountsByUsername)) {
                return new Response<>(HttpStatus.NO_CONTENT, MessageCode.Account.USERNAME_NOT_EXIST);
            }
            if (!accountsByUsername.get(0).getEmail().equals(forgetPasswordDto.getEmail())) {
                return new Response<>(HttpStatus.NO_CONTENT, MessageCode.Account.EMAIL_NOT_MATCH);
            }
            emailService.sendPasswordResetEmail(forgetPasswordDto.getEmail(), "https://baidu.com");
            return new Response<>("邮件发送成功!");
        } catch (Exception e) {
            log.info("邮件发送失败: [{}]", e.getMessage());
            return new Response<>(HttpStatus.ERROR, MessageCode.Account.SEND_EMAIL_FAILED);
        }
    }

    private Response<Object> loginByUserType(ForgetPasswordDto forgetPasswordDto) {
        return new Response<>("成功!");
    }
}
