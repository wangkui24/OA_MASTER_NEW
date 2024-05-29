package com.kwang43.boot.core;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Slf4j
public class MdcTaskExecutor extends ThreadPoolTaskExecutor {
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        log.info("mdc thread pool task executor submit");
        Map<String, String> context = MDC.getCopyOfContextMap();
        return super.submit(() -> {
            T result;
            if (context != null) {
                //将父线程的MDC内容传给子线程
                MDC.setContextMap(context);
            } else {
                //直接给子线程设置MDC
                MDC.put(Const.LOG_MDC_ID, UUID.randomUUID().toString().replace("-", ""));
            }
            try {
                //执行任务
                result = task.call();
            } finally {
                try {
                    MDC.clear();
                } catch (Exception e) {
                    log.warn("MDC clear exception", e);
                }
            }
            return result;
        });
    }

    @Override
    public void execute(Runnable task) {
        log.info("mdc thread pool task executor execute");
        Map<String, String> context = MDC.getCopyOfContextMap();
        super.execute(() -> {
            if (context != null) {
                //将父线程的MDC内容传给子线程
                MDC.setContextMap(context);
            } else {
                //直接给子线程设置MDC
                MDC.put(Const.LOG_MDC_ID, UUID.randomUUID().toString().replace("-", ""));
            }
            try {
                //执行任务
                task.run();
            } finally {
                try {
                    MDC.clear();
                } catch (Exception e) {
                    log.warn("MDC clear exception", e);
                }
            }
        });
    }
}
