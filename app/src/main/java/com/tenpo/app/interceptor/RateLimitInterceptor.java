package com.tenpo.app.interceptor;

import io.github.bucket4j.local.LocalBucket;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final LocalBucket rateLimitBucket;

    public RateLimitInterceptor(LocalBucket rateLimitBucket) {
        this.rateLimitBucket = rateLimitBucket;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!rateLimitBucket.tryConsume(1)) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "There were too many requests in the last minute");
        }
        return true;
    }
}
