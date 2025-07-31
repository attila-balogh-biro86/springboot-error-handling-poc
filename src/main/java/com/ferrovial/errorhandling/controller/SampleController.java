package com.ferrovial.errorhandling.controller;

import com.ferrovial.errorhandling.exception.CustomException;
import com.ferrovial.errorhandling.service.ExternalServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

    private final ExternalServiceClient externalServiceClient;

    public SampleController(ExternalServiceClient externalServiceClient) {
        this.externalServiceClient = externalServiceClient;
    }

    @GetMapping("/fail")
    public String fail() {
        throw new CustomException("Manual failure triggered");
    }

    @GetMapping("/external")
    public String callExternal() {
        return externalServiceClient.callExternalService();
    }
}