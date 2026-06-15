package com.kwang43.boot.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 硬性绑定该异常对应的 HTTP Code 为 404
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2188091376011670778L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
