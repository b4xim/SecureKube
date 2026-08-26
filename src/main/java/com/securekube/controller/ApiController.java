package com.securekube.controller;

import com.securekube.dto.GreetingResponse;
import com.securekube.dto.HealthResponse;
import com.securekube.dto.VersionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${app.version}")
    private String applicationVersion;

    @GetMapping("/hello")
    public ResponseEntity<GreetingResponse> hello() {
        log.info("GET /api/hello");
        return ResponseEntity.ok(new GreetingResponse("Hello from SecureKube!"));
    }

    @GetMapping("/version")
    public ResponseEntity<VersionResponse> version() {
        log.info("GET /api/version");
        return ResponseEntity.ok(new VersionResponse(applicationName, applicationVersion));
    }

    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {
        log.info("GET /api/health");
        return ResponseEntity.ok(new HealthResponse("UP", "SecureKube API is running"));
    }
}
