package com.kwang43.boot.config;

import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;

public class ThreadMdcUtils {
    public static Runnable wrapAsync(Runnable task, Map<String,String> context){
        return () -> {
            if(context==null){
                MDC.clear();
            }else {
                MDC.setContextMap(context);
            }
            if(MDC.get("traceId")==null){
                MDC.put("traceId", UUID.randomUUID().toString().replace("-", ""));
            }
            try {
                task.run();
            }finally {
                MDC.clear();
            }
        };
    }
}
