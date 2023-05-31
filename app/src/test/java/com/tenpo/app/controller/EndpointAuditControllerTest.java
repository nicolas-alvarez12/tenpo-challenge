package com.tenpo.app.controller;

import com.tenpo.app.config.RateLimitConfig;
import com.tenpo.app.controller.model.Response;
import com.tenpo.app.model.EndpointAuditDTO;
import com.tenpo.app.model.EndpointAuditResponse;
import com.tenpo.app.service.ArithmeticService;
import com.tenpo.app.service.EndpointAuditService;
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
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EndpointAuditController.class)
@ContextConfiguration(classes = {RateLimitConfig.class})
@Import(EndpointAuditController.class)
class EndpointAuditControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EndpointAuditService endpointAuditService;

    @Test
    public void whenAuditEndpoints_thenReturnEndpointsAudited() throws Exception {
        var endpointAuditDTO = new EndpointAuditDTO("GET /api/arithmetic", HttpStatus.OK.value(), "{\"result\":10.0}");
        List<EndpointAuditDTO> endpointAuditDTOList = List.of(endpointAuditDTO);
        var response = new EndpointAuditResponse(endpointAuditDTOList, 1, 1, 1);

        given(endpointAuditService.getAllEndpointsAudit(anyInt(), anyInt())).willReturn(response);

        mvc.perform(MockMvcRequestBuilders.get("/api/audit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.allEndpointsAudits", hasSize(1)));
    }
}