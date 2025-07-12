package org.example.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.repository.ApplicationRepository;
import org.example.security.auth.ApiKeyAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final ApplicationRepository applicationRepository;

    public ApiKeyAuthFilter(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        if ((method.equals("GET") || method.equals("POST")) && path.startsWith("/api/tracker")) {

            String apiKey = request.getHeader("X-API-Key");

            if (apiKey == null || apiKey.isBlank()) {
                respondUnauthorized(response, "Missing API Key");
            }

            applicationRepository.findByApikey(apiKey).ifPresentOrElse(app -> {
                ApiKeyAuthenticationToken authToken = new ApiKeyAuthenticationToken(apiKey, app);
                SecurityContextHolder.getContext().setAuthentication(authToken);
                try {
                    filterChain.doFilter(request, response);
                } catch (IOException | ServletException e) {
                    throw new RuntimeException(e);
                }
            }, () -> respondUnauthorized(response, "Invalid API Key"));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void respondUnauthorized(HttpServletResponse response, String message) {
        try {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain");
            response.getWriter().write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
