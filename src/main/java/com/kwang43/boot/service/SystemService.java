package com.kwang43.boot.service;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.model.dto.ForgetPasswordDto;
import com.kwang43.boot.model.dto.LoginDto;
import com.kwang43.boot.model.dto.SaveOaUsersDto;
import com.kwang43.boot.model.response.LoginResponse;

public interface SystemService {
    LoginResponse login(LoginDto loginDto);

    Boolean saveOaUser(SaveOaUsersDto saveOaUsersDto);

    Response<Object> forgetPassword(ForgetPasswordDto forgetPasswordDto);
}
