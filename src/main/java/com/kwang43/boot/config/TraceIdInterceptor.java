package com.kwang43.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;


@Component
//创建拦截器并拦截所有请求，为其生成traceId
public class TraceIdInterceptor extends HandlerInterceptorAdapter {
    private static final String TRACE_ID = "traceId";
    String pathPatterns = "/**";
    List<String> excludeURL   = new ArrayList<String>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = String.format("%s", UUID.randomUUID().toString().replace("-", ""));
        MDC.put(TRACE_ID, traceId);
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(TRACE_ID);
    }
}
