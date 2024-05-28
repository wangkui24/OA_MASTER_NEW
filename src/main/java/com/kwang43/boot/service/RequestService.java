package com.kwang43.boot.service;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.model.dto.RequestLeaveDto;


public interface RequestService {

    Response<Boolean> requestLeave(RequestLeaveDto requestLeaveDto);
}