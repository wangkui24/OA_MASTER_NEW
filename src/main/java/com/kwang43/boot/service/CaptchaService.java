package com.kwang43.boot.service;

import com.kwang43.boot.config.Response;
import com.kwang43.boot.model.response.CaptchaResponse;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public interface CaptchaService {
    CaptchaResponse generateCaptchaImage(String old_uuid);
}