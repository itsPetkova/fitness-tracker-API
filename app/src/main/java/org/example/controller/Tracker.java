package org.example.controller;

import org.example.entity.Application;
import org.example.dto.DataEntryDto;
import org.example.dto.DataEntryResponse;
import org.example.service.EntryService;
import org.example.service.RateLimiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tracker")
public class Tracker {
    private final EntryService entryService;
    private final RateLimiterService rateLimiterService;

    public Tracker(EntryService entryService, RateLimiterService rateLimiterService) {
        this.entryService = entryService;
        this.rateLimiterService = rateLimiterService;
    }

    @PostMapping
    public ResponseEntity<String> saveEntry(@RequestBody DataEntryDto dataEntry) {
        Application app = getApplication();

        if (isRateLimited(app)) {
            return ResponseEntity.status(429).body("You have reached your request limit");
        }

        entryService.saveEntry(dataEntry);
        return ResponseEntity.created(URI.create("/api/tracker")).build();
    }

    @GetMapping
    public ResponseEntity<List<DataEntryResponse>> getEntries() {
        Application app = getApplication();

        if (isRateLimited(app)) {
            return ResponseEntity.status(429).build();
        }

        return ResponseEntity.ok(entryService.getAllEntries());
    }

    private static Application getApplication() {
        return (Application) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

    private boolean isRateLimited(Application app) {
        return "basic".equalsIgnoreCase(app.getCategory()) &&
                !rateLimiterService.isRequestAllowed(app.getApikey());
    }
}