package com.tenpo.external.controller;

import com.tenpo.external.controller.model.Response;
import com.tenpo.external.service.ExternalService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExternalController.class)
@Import(ExternalController.class)
class ExternalControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExternalService externalService;

    @Test
    public void whenCalculatePercentage_thenReturnResponse() throws Exception {
        given(externalService.calculatePercentage()).willReturn(10.0f);

        mvc.perform(MockMvcRequestBuilders.get("/api/external/percentage"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(10.0)));
    }
}