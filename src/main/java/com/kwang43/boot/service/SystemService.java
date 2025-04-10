package com.kwang43.boot.service;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.model.dto.ForgetPasswordDto;
import com.kwang43.boot.model.dto.LoginDto;
import com.kwang43.boot.model.response.LoginResponse;

public interface SystemService {
    LoginResponse login(LoginDto loginDto);

    Response<Object> forget_password(ForgetPasswordDto forgetPasswordDto);
}
