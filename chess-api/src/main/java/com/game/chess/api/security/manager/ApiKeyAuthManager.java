package com.game.chess.api.security.manager;

import com.game.chess.db.service.ApiKeyService;
import lombok.Builder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

@Builder
public class ApiKeyAuthManager implements AuthenticationManager {


    private ApiKeyService apiKeyService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String apiKey = (String) authentication.getPrincipal();
        apiKeyService.getOptionalApiKey(apiKey)
                .orElseThrow(() -> new BadCredentialsException("Invalid ApiKey"));

        authentication.setAuthenticated(true);

        return authentication;
    }
}
