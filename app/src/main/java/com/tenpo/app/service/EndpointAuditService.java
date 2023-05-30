package com.tenpo.app.service;

import com.tenpo.app.model.EndpointAuditDTO;
import com.tenpo.app.model.EndpointAuditEntity;
import com.tenpo.app.model.EndpointAuditResponse;
import com.tenpo.app.repository.EndpointAuditRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EndpointAuditService {

    private final EndpointAuditRepository endpointAuditRepository;

    public EndpointAuditService(EndpointAuditRepository endpointAuditRepository) {
        this.endpointAuditRepository = endpointAuditRepository;
    }

    public EndpointAuditResponse getAllEndpointsAudit(int page, int size) {
        Page<EndpointAuditEntity> entities = endpointAuditRepository.findAll(PageRequest.of(page, size));

        return new EndpointAuditResponse(toDTOList(entities.getContent()), entities.getNumber() + 1, entities.getTotalElements(),
                entities.getTotalPages());
    }

    private List<EndpointAuditDTO> toDTOList(List<EndpointAuditEntity> entities) {
        return entities.stream()
                .map(entity -> new EndpointAuditDTO(entity.getUri(), entity.getStatus(), entity.getResponse()))
                .collect(Collectors.toList());
    }

    @Async
    public void saveEndpointAudit(String uri, int status, String response) {
        var endpointAuditEntity = new EndpointAuditEntity();
        endpointAuditEntity.setUri(uri);
        endpointAuditEntity.setStatus(status);
        endpointAuditEntity.setResponse(response);
        endpointAuditRepository.save(endpointAuditEntity);
    }
}
