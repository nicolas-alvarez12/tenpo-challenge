package com.tenpo.app.service;

import com.tenpo.app.client.ExternalServiceClient;
import com.tenpo.app.controller.model.Response;
import com.tenpo.app.model.ExternalPercentageResponse;
import org.springframework.stereotype.Service;

@Service
public class ArithmeticService {

    private final ExternalServiceClient externalServiceClient;

    public ArithmeticService(ExternalServiceClient externalServiceClient) {
        this.externalServiceClient = externalServiceClient;
    }

    public Response calculate(float firstValue, float secondValue) {
        float percentage = 10F;
        ExternalPercentageResponse externalPercentageResponse = externalServiceClient.getPercentageFromExternalService();

        if (externalPercentageResponse != null && externalPercentageResponse.getPercentage() != null) {
            float sum = firstValue + secondValue;
            percentage = sum + externalPercentageResponse.getPercentage() * sum / 100;
        }

        return new Response(percentage);
    }
}
