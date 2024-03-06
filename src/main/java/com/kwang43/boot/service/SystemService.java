package com.kwang43.boot.service;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.model.dto.LoginDto;

public interface SystemService {
    Response<Object> login(LoginDto loginDto);
}
