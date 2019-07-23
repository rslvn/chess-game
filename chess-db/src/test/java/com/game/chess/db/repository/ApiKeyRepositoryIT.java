package com.game.chess.db.repository;

import com.game.chess.db.model.ApiKey;
import com.game.chess.db.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@EnableAutoConfiguration // create MongoTemplate and MongoOperations
@EnableMongoRepositories(basePackages = "com.game.chess.db.repository")
@RunWith(SpringRunner.class)
public class ApiKeyRepositoryIT {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testMultiApiKey() {

        User user = User.builder().email("test@email.com").password("pwd").build();
        User saved = userRepository.save(user);
        LOG.info("Saved User: {}", saved);

        ApiKey apiKey1 = ApiKey.builder().user(saved).build();

        ApiKey apiKey2 = ApiKey.builder().user(saved).build();

        apiKeyRepository.save(apiKey1);
        apiKeyRepository.save(apiKey2);

        List<ApiKey> apiKeyList =apiKeyRepository.findAll();
        LOG.info("ALL SIZE of LIST: {}", apiKeyList.size());
        apiKeyList.forEach(apiKey -> LOG.info("ALL API KEY: {}", apiKey));

         apiKeyList = apiKeyRepository.findApiKeysByUserId(saved.getId());
        LOG.info("SIZE of LIST: {}", apiKeyList.size());
        apiKeyList.forEach(apiKey -> LOG.info("API KEY: {}", apiKey));


    }
}