package com.tenpo.app.service;

import com.tenpo.app.client.ExternalServiceClient;
import com.tenpo.app.controller.model.Response;
import com.tenpo.app.model.ExternalPercentageResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArithmeticServiceTest {

    @MockBean
    private ExternalServiceClient externalServiceClient;

    @Autowired
    private ArithmeticService arithmeticService;

    @Test
    public void whenCalculate_thenCalculationResponse() throws Exception {
        var result = 10.0F;
        var response = new ExternalPercentageResponse(result);

        given(externalServiceClient.getPercentageFromExternalService()).willReturn(response);

        assertEquals(arithmeticService.calculate(10, 10).getResult(), 22);
    }
}