package com.game.chess.api.security.filter;

import lombok.Builder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

@Builder
public class ApiKeyFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String header;

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(header);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        // no credentials for ApiKey
        return null;
    }
}
