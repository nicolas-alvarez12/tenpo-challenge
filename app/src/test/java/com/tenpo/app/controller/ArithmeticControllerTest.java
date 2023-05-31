package com.tenpo.app.controller;

import com.tenpo.app.config.RateLimitConfig;
import com.tenpo.app.controller.model.Response;
import com.tenpo.app.service.ArithmeticService;
import com.tenpo.app.service.EndpointAuditService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArithmeticController.class)
@ContextConfiguration(classes = {RateLimitConfig.class})
@Import(ArithmeticController.class)
class ArithmeticControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArithmeticService arithmeticService;
    @MockBean
    private EndpointAuditService endpointAuditService;

    @Test
    public void whenCalculate_thenCalculationResponse() throws Exception {
        var result = 10.0;
        var response = new Response((float) result);

        given(arithmeticService.calculate(anyFloat(), anyFloat())).willReturn(response);

        mvc.perform(MockMvcRequestBuilders.get("/api/arithmetic")
                        .param("firstValue", "10")
                        .param("secondValue", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(result)));
    }
}