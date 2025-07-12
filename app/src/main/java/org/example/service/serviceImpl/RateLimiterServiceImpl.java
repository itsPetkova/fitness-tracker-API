package org.example.service.serviceImpl;

import org.example.service.RateLimiterService;
import org.example.utils.TokenBucket;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterServiceImpl implements RateLimiterService {
    private final ConcurrentHashMap<String, TokenBucket> buckets = new ConcurrentHashMap<>();

    @Override
    public boolean isRequestAllowed(String apiKey) {
        if (!buckets.containsKey(apiKey)) {
            TokenBucket bucket = new TokenBucket(Instant.now());
            buckets.put(apiKey, bucket);
            return true;
        }

        TokenBucket requestBucket = buckets.get(apiKey);
        if (Instant.now().isAfter(requestBucket.getLastRequest().plusSeconds(1))) {
            requestBucket.setLastRequest(Instant.now());
            return true;
        }
        return false;
    }
}
