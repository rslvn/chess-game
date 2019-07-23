package com.game.chess.console.service;

import com.game.chess.db.model.ApiKey;
import com.game.chess.db.model.User;
import com.game.chess.db.service.ApiKeyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class ConsoleApiKeyService {

    private ApiKeyService apiKeyService;

    public ApiKey createApiKey(User user) {

        ApiKey apiKey = ApiKey.builder().user(user).build();

        return apiKeyService.saveApiKey(apiKey);
    }

}
