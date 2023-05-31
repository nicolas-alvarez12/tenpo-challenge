package com.tenpo.app.controller;

import com.google.gson.Gson;
import com.tenpo.app.controller.model.Response;
import com.tenpo.app.service.ArithmeticService;
import com.tenpo.app.service.EndpointAuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/arithmetic")
public class ArithmeticController {

    private final ArithmeticService arithmeticService;
    private final EndpointAuditService endpointAuditService;

    public ArithmeticController(ArithmeticService arithmeticService, EndpointAuditService endpointAuditService) {
        this.arithmeticService = arithmeticService;
        this.endpointAuditService = endpointAuditService;

    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Response> calculate(@RequestParam float firstValue, @RequestParam float secondValue) {
        Response response = arithmeticService.calculate(firstValue, secondValue);

        var gson = new Gson();
        this.endpointAuditService.saveEndpointAudit("GET /api/arithmetic", HttpStatus.OK.value(), gson.toJson(response));

        return ResponseEntity.ok(response);
    }

}
