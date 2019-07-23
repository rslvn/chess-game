package com.game.chess.db.service;

import com.game.chess.common.exception.NotFoundException;
import com.game.chess.db.model.ApiKey;
import com.game.chess.db.repository.ApiKeyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.Cacheable;

@AllArgsConstructor(onConstructor_ = {@Autowired})
@Service
//@CacheConfig(cacheNames = {"chess-apiKeys"})
public class ApiKeyService {

    private ApiKeyRepository apiKeyRepository;

    //    @Cacheable
    public ApiKey getApiKey(String apiKey) {
        return apiKeyRepository.findById(apiKey)
                .orElseThrow(() -> NotFoundException.to("ApiKey not found by id: %s", apiKey));
    }

    public Optional<ApiKey> getOptionalApiKey(String apiKey) {
        return apiKeyRepository.findById(apiKey);
    }

    public ApiKey saveApiKey(ApiKey apiKey) {
        return apiKeyRepository.save(apiKey);
    }
}
