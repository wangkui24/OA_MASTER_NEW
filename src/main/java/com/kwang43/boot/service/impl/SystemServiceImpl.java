package com.kwang43.boot.service.impl;

import com.kwang43.boot.config.EncryptionService;
import com.kwang43.boot.domain.Employee;
import com.kwang43.boot.domain.OaRoles;
import com.kwang43.boot.domain.OaUsers;
import com.kwang43.boot.domain.Permission;
import com.kwang43.boot.config.BaseMapperService;
import com.kwang43.boot.model.dto.SaveOaUsersDto;
import com.kwang43.boot.model.response.LoginResponse;
import com.kwang43.boot.model.dto.OaUsersDto;
import com.kwang43.boot.model.response.SecurityResponse;
import com.kwang43.boot.repository.EmployeeRepository;
import com.kwang43.boot.repository.OaUsersRepository;
import com.kwang43.boot.utils.*;
//import com.kwang43.boot.config.EmailService;
import com.kwang43.boot.config.Response;
import com.kwang43.boot.core.MessageCode;
import com.kwang43.boot.model.BaseEnum;
import com.kwang43.boot.model.dto.ForgetPasswordDto;
import com.kwang43.boot.model.dto.LoginDto;
import com.kwang43.boot.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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

//    @Autowired
//    private EmailService emailService;

    @Autowired
    private BaseMapperService baseMapperService;

    @Autowired
    private EncryptionService encryptionService;

    @Override
    public LoginResponse login(LoginDto loginDto) {
        log.info("loginDto: [{}]", loginDto);
        if (redisUtils.exists(loginDto.getUuid())) {
            Object code = redisUtils.get(loginDto.getUuid());
            if (loginDto.getCode().equals(code)) {
                OaUsers oaAccounts = oaUsersRepository.findByEmail(loginDto.getEmail());
                if (oaAccounts == null) {
                    throw new DataIntegrityViolationException(MessageCode.Account.ACCOUNT_NOT_EXIST);
                } else {
                    if (encryptionService.decrypt(oaAccounts.getPassword()).equals(loginDto.getPassword())) {
                        OaUsers oaUsers = oaAccounts;
                        if (oaUsers.getStatus().equals(BaseEnum.OaUser.StatusEnum.INACTIVE)) {
                            throw new DataIntegrityViolationException(MessageCode.Account.ACCOUNT_IS_INACTIVE);
                        } else if (oaUsers.getStatus().equals(BaseEnum.OaUser.StatusEnum.BLOCKED)) {
                            throw new DataIntegrityViolationException(MessageCode.Account.ACCOUNT_IS_BLOCKED);
                        }

                        OaUsersDto userInfo = baseMapperService.getOaUsersBaseDto(oaUsers);
                        OaRoles oaRoles = oaUsers.getOaRoles();
                        String roleName = null;
                        List<String> permissions = null;
                        if (oaRoles != null) {
                            roleName = oaRoles.getRoleName();
                            userInfo.setRoleName(roleName);
                            if (oaRoles.getPermission() != null) {
                                permissions = oaRoles.getPermission().stream()
                                        .map(Permission::getName)
                                        .collect(Collectors.toList());
                            }
                        }

                        String accessToken = jwtUtils.generateToken(loginDto.getEmail(), oaUsers.getNickName(), roleName, permissions);
                        String refreshToken = jwtUtils.generateRefreshToken(loginDto.getEmail());

                        SecurityResponse securityResponse = SecurityResponse.builder()
                                .access_token(accessToken)
                                .refresh_token(refreshToken)
                                .token_type("Bearer")
                                .expires_in(jwtUtils.getExpiration())
                                .permissions(permissions)
                                .build();

                        LoginResponse loginResponse = new LoginResponse();
                        loginResponse.setOaAccount(userInfo);
                        loginResponse.setSecurity(securityResponse);

                        redisUtils.remove(loginDto.getUuid());
                        return loginResponse;
                    } else {
                        throw new DataIntegrityViolationException(MessageCode.Account.PASSWORD_ERROR);
                    }
                }
            } else {
                throw new DataIntegrityViolationException(MessageCode.Captcha.CAPTCHA_ERROR);
            }
        } else {
            throw new DataIntegrityViolationException(MessageCode.Captcha.CAPTCHA_ERROR_OR_EXPIRE);
        }
    }

    @Override
    public Boolean saveOaUser(SaveOaUsersDto saveOaUsersDto) {
        OaUsers oaUsers = oaUsersRepository.findByEmail(saveOaUsersDto.getEmail());
        if (oaUsers == null) {
            throw new DataIntegrityViolationException(MessageCode.Account.EMAIL_IS_EXISTED);
        }
        return true;
    }


    @Override
    public Response<Object> forgetPassword(ForgetPasswordDto forgetPasswordDto) {
        try {
            List<OaUsers> accountsByUsername = oaUsersRepository.findByNickName(forgetPasswordDto.getUsername());
            if (StringUtils.isEmpty(accountsByUsername)) {
                return new Response<>(HttpStatus.NO_CONTENT, MessageCode.Account.USERNAME_NOT_EXIST);
            }
            if (!accountsByUsername.get(0).getEmail().equals(forgetPasswordDto.getEmail())) {
                return new Response<>(HttpStatus.NO_CONTENT, MessageCode.Account.EMAIL_NOT_MATCH);
            }
//            emailService.sendPasswordResetEmail(forgetPasswordDto.getEmail(), "https://baidu.com");
            return new Response<>("邮件发送成功!");
        } catch (Exception e) {
            log.info("邮件发送失败: [{}]", e.getMessage());
            return new Response<>(HttpStatus.ERROR, MessageCode.Account.SEND_EMAIL_FAILED);
        }
    }

    private Response<Object> createOaAccountByEmployee(Employee employee) {
        try {
            OaUsers oaUsers = new OaUsers();
            oaUsers.setEmail(employee.getEmail());
            oaUsers.setCellphone(employee.getCellphone());
            oaUsers.setStatus(BaseEnum.OaUser.StatusEnum.INACTIVE);
//            oaUsers.setRoleId(employee.getRoleId());
            oaUsers.setCreateDatetime(DateUtils.getNowTime());
            oaUsers.setCreateBy(BaseEnum.Defalut.SYSTEM);
            oaUsersRepository.save(oaUsers);
            log.info("OA账号创建成功: [{}]", employee);
            return new Response<>(true);
        }
        catch(Exception e) {
            log.error("OA账号创建失败: [{}]", e.getMessage());
            return new Response<>(HttpStatus.ERROR, MessageCode.Account.CREATE_OA_ACCOUNT_FAILED);
        }
    }
}
