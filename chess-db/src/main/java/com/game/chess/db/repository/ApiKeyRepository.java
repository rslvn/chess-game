package com.game.chess.db.repository;

import com.game.chess.db.model.ApiKey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApiKeyRepository extends MongoRepository<ApiKey, String> {

    List<ApiKey> findApiKeysByUserId(String userId);

}
