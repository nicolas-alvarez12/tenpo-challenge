package com.tenpo.app.controller;

import com.tenpo.app.service.EndpointAuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audit")
public class EndpointAuditController {

    private final EndpointAuditService endpointAuditService;

    public EndpointAuditController(EndpointAuditService endpointAuditService) {
        this.endpointAuditService = endpointAuditService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> auditEndpoints(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(endpointAuditService.getAllEndpointsAudit(page, size));
    }

}
