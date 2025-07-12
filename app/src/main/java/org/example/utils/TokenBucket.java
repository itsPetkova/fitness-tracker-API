package org.example.utils;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TokenBucket {
    private Instant lastRequest;

    public TokenBucket(Instant lastRequest) {
        this.lastRequest = lastRequest;
    }
}
