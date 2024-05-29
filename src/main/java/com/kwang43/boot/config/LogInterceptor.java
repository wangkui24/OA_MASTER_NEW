package com.kwang43.boot.config;

import com.kwang43.boot.core.Const;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 日志拦截器
 */

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //log.info("进入 LogInterceptor");
        //添加MDC值
        MDC.put(Const.LOG_MDC_ID, UUID.randomUUID().toString().replace("-", ""));
        //打印接口请求信息
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("[请求接口] : {} : {}", method, uri);
        //打印请求参数
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //log.info("执行 LogInterceptor");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //log.info("退出 LogInterceptor");
        //打印请求结果
        //删除MDC值
        MDC.remove(Const.LOG_MDC_ID);
    }
}
