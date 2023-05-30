package com.tenpo.app.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.github.bucket4j.local.LocalBucket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitConfig {

    @Value("${RPM_ALLOWED}")
    private int requestsPerMinuteAllowed;

    @Bean
    public LocalBucket rateLimitBucket() {
        Bandwidth limit = Bandwidth.classic(requestsPerMinuteAllowed, Refill.greedy(requestsPerMinuteAllowed, Duration.ofMinutes(1)));
        return Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

}
