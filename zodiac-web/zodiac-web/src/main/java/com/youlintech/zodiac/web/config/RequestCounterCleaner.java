package com.youlintech.zodiac.web.config;

import com.youlintech.zodiac.web.interceptor.RateLimitInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RequestCounterCleaner {


    @Scheduled(fixedRate = 60000) // 每分钟清理一次
    public void cleanRequestCounts() {
        log.info("清理请求表内容");
        RateLimitInterceptor.requestCounts.clear();
    }
}



