package org.example.service;

import org.springframework.stereotype.Service;

@Service
public interface RateLimiterService {
    boolean isRequestAllowed(String apiKey);
}
