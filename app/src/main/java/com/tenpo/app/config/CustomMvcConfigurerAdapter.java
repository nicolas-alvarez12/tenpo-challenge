package com.tenpo.app.config;

import com.tenpo.app.interceptor.RateLimitInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class CustomMvcConfigurerAdapter implements WebMvcConfigurer {

    private final RateLimitInterceptor rateLimitInterceptor;

    public CustomMvcConfigurerAdapter(RateLimitInterceptor rateLimitInterceptor) {
        this.rateLimitInterceptor = rateLimitInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitInterceptor);
    }
}
