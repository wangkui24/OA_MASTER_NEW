package com.kwang43.boot.service;

import com.kwang43.boot.config.Response;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public interface CaptchaService {

    Response<Object> generateCaptchaImage();
}