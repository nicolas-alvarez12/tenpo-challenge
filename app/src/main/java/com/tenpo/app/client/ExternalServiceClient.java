package com.tenpo.app.client;

import com.tenpo.app.exception.ExternalServiceException;
import com.tenpo.app.model.ExternalPercentageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class ExternalServiceClient {

    @Value("${EXTERNAL_SERVICE_PATH}")
    private String externalServicePath;
    @Value("${EXTERNAL_SERVICE_MAX_RETRIES}")
    private int externalServiceMaxRetries;
    @Value("${EXTERNAL_SERVICE_MIN_BACKOFF}")
    private int externalServiceMinBackoff;

    @Cacheable("percentage")
    public ExternalPercentageResponse getPercentageFromExternalService() {
        return WebClient.builder()
                .build()
                .get()
                .uri(externalServicePath)
                .retrieve()
                .onStatus(
                        HttpStatus::is5xxServerError,
                        response -> Mono.error(new ExternalServiceException("Got"
                                + response.statusCode()
                                + " while executing "
                                + externalServicePath)))
                .bodyToMono(ExternalPercentageResponse.class)
                .retryWhen(Retry.backoff(externalServiceMaxRetries, Duration.ofMillis(externalServiceMinBackoff))
                        .filter(throwable -> throwable instanceof ExternalServiceException)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                            throw new ExternalServiceException(retrySignal.failure().getMessage());
                        }))
                .block();
    }
}
