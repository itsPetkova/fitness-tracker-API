package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.AppRegisterDto;
import org.example.dto.AppRegisterResponse;
import org.example.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applications")
public class Application {
    private final ApplicationService applicationService;

    public Application(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping("/register")
    public ResponseEntity<AppRegisterResponse> register(@Valid @RequestBody AppRegisterDto registerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.registerApplication(registerDto));
    }
}
