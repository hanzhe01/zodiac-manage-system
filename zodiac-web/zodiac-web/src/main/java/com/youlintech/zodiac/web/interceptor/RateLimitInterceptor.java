package com.youlintech.zodiac.web.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    public static final Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();
        AtomicInteger requestCount = requestCounts.computeIfAbsent(ipAddress, k -> new AtomicInteger(0));
        System.out.println("当前ip:"+ipAddress+"请求次数："+requestCount.incrementAndGet());
        if (requestCount.incrementAndGet() > 15) { // 限制每分钟每个IP最多10次请求
            response.setContentType("text/html;charset=UTF-8");

            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("请求过于频繁，请稍后再试。");
//            throw new RuntimeException("请求过于频繁，请稍后再试。");
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String ipAddress = request.getRemoteAddr();
        AtomicInteger requestCount = requestCounts.get(ipAddress);
        if (requestCount != null) {
            requestCount.decrementAndGet();
        }
    }
}



