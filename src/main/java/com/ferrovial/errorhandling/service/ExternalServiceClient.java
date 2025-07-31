package com.ferrovial.errorhandling.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @CircuitBreaker(name = "externalService", fallbackMethod = "fallback")
    @Retry(name = "externalService")
    public String callExternalService() {
        return restTemplate.getForObject("https://httpstat.us/503", String.class);
    }

    public String fallback(Throwable t) {
        return "Fallback response due to: " + t.getMessage();
    }
}
