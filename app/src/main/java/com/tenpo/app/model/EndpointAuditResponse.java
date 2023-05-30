package com.tenpo.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EndpointAuditResponse {
    private List<EndpointAuditDTO> allEndpointsAudits;
    private int currentPage;
    private long totalItems;
    private int totalPages;
}
