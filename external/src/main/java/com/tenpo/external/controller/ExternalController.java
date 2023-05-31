package com.tenpo.external.controller;

import com.tenpo.external.controller.model.Response;
import com.tenpo.external.service.ExternalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external")
public class ExternalController {

    private final ExternalService externalService;

    public ExternalController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping(produces = "application/json", path = "/percentage")
    public ResponseEntity<Response> calculatePercentage() {
        return ResponseEntity.ok(new Response(externalService.calculatePercentage()));
    }

}
