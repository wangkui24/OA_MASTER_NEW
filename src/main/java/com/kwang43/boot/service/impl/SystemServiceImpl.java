package com.kwang43.boot.service.impl;

import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.domain.OaUsers;
import com.kwang43.boot.repository.EmployeeRepository;
import com.kwang43.boot.repository.OaUsersRepository;
import com.kwang43.boot.utils.*;
import com.kwang43.boot.config.EmailService;
import com.kwang43.boot.config.Response;
import com.kwang43.boot.core.MessageCode;
import com.kwang43.boot.model.BaseEnum;
import com.kwang43.boot.model.dto.ForgetPasswordDto;
import com.kwang43.boot.model.dto.LoginDto;
import com.kwang43.boot.service.SystemService;
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
                Object code = redisUtils.get(loginDto.getUuid());
                log.info("code: [{}]", code);
                if (loginDto.getCode().equals(code)) {
                    List<Employee> employeeAccounts = employeeRepository.findByEmail(loginDto.getEmail());
                    List<OaUsers> oaAccounts = oaUsersRepository.findByEmail(loginDto.getEmail());
                    if (StringUtils.isEmpty(oaAccounts)) {
                        if (StringUtils.isEmpty(employeeAccounts)) {
                            return new Response<>(HttpStatus.NO_CONTENT, MessageCode.Account.ACCOUNT_NOT_EXIST);
                        }
                        // check the status of employee
                        Employee employee = employeeAccounts.get(0);
                        if (employee.getStatus().equals(BaseEnum.Employee.EmployeeStatusEnum.RESIGNED)) {
                            return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Employee.EMPLOYEE_HAS_RESIGNED);
                        } else if (employee.getStatus().equals(BaseEnum.Employee.EmployeeStatusEnum.JOINING_IN)) {
                            return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Employee.EMPLOYEE_HAS_NOT_IN_SERVICE);
                        }
                        // create oa user for employee
                        return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Account.ACCOUNT_IS_INACTIVE);
                    } else {
                        String decryptedPassword = decryptService.decrypt(employeeAccounts.get(0).getPassword());
                        if (decryptService.decrypt(loginDto.getPassword()).equals(decryptedPassword)) {
                            OaUsers oaUsers = oaAccounts.get(0);
                            if (oaUsers.getStatus().equals(BaseEnum.OaUser.StatusEnum.INACTIVE)) {
                                return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Account.ACCOUNT_IS_INACTIVE);
                            } else if (oaUsers.getStatus().equals(BaseEnum.OaUser.StatusEnum.BLOCKED)) {
                                return new Response<>(HttpStatus.FORBIDDEN, MessageCode.Account.ACCOUNT_IS_BLOCKED);
                            }
                            String token = jwtUtils.generateToken(loginDto.getEmail(), loginDto.getPassword());
                            HashMap<Object, Object> map = new HashMap<>();
                            map.put("token", token);
                            map.put("user_info", oaUsers);
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

    private Boolean createOaUserAccountByEmployee(Employee employee) {
        try {
            OaUsers oaUsers = new OaUsers();
            oaUsers.setNickName(employee.getNickName());
            oaUsers.setEmail(employee.getEmail());
            oaUsers.setCellphone(employee.getCellphone());
//            oaUsers.setOaRoles(employee.setOaRoles());
            oaUsers.setCreateDatetime(DateUtils.getNowTime());
            oaUsers.setCreateBy(BaseEnum.Defalut.SYSTEM);
            return true;
        }
        catch(Exception e) {
            log.info("OA账号创建失败: [{}]", e.getMessage());
            return false;
        }
    }
}
