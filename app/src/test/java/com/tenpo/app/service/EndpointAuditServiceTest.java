package com.tenpo.app.service;

import com.tenpo.app.client.ExternalServiceClient;
import com.tenpo.app.model.EndpointAuditEntity;
import com.tenpo.app.model.ExternalPercentageResponse;
import com.tenpo.app.repository.EndpointAuditRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.will;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EndpointAuditServiceTest {

    @MockBean
    private EndpointAuditRepository endpointAuditRepository;

    @Autowired
    private EndpointAuditService endpointAuditService;

    @Test
    public void whenGetAllEndpointsAudit_thenEndpointAuditResponse() throws Exception {
        var result = 10.0F;
        var response = new ExternalPercentageResponse(result);
        EndpointAuditEntity endpointAuditEntity = new EndpointAuditEntity();
        endpointAuditEntity.setUri("GET /api/arithmetic");
        endpointAuditEntity.setResponse("{\"result\":10.0}");
        endpointAuditEntity.setStatus(HttpStatus.OK.value());
        List<EndpointAuditEntity> endpointAuditEntityList = List.of(endpointAuditEntity);
        Page<EndpointAuditEntity> endpointAuditEntityPage = new PageImpl<>(endpointAuditEntityList, PageRequest.of(1, 10), 1);

        given(endpointAuditRepository.findAll(any())).willReturn(endpointAuditEntityPage);

        assertEquals(endpointAuditService.getAllEndpointsAudit(1, 10).getAllEndpointsAudits().get(0).getStatus(), HttpStatus.OK.value());
    }

}