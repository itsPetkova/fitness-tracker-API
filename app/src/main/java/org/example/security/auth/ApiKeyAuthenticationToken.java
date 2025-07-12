package org.example.security.auth;

import org.example.entity.Application;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class ApiKeyAuthenticationToken extends AbstractAuthenticationToken {
    private final String apiKey;
    private final Application application;

    public ApiKeyAuthenticationToken(String apiKey, Application application) {
        super(null);
        this.apiKey = apiKey;
        this.application = application;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return apiKey;
    }

    @Override
    public Object getPrincipal() {
        return application;
    }
}

