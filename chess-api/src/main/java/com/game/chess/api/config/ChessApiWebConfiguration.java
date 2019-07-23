package com.game.chess.api.config;

import com.game.chess.api.security.filter.ApiKeyFilter;
import com.game.chess.api.security.manager.ApiKeyAuthManager;
import com.game.chess.db.service.ApiKeyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@AllArgsConstructor(onConstructor_ = {@Autowired})
@Configuration
@EnableMongoRepositories(basePackages = "com.game.chess.db.repository")
@ComponentScan("com.game.chess.db.service")
@EnableWebSecurity
public class ChessApiWebConfiguration extends WebSecurityConfigurerAdapter {

    private static final String API_KEY_HEADER_NAME = "API_KEY";

    private ApiKeyService apiKeyService;

    @Bean
    public ApiKeyAuthManager apiKeyAuthManager() {
        return ApiKeyAuthManager.builder().apiKeyService(apiKeyService).build();
    }

    @Bean
    public ApiKeyFilter apiKeyFilter() {
        ApiKeyFilter apiKeyFilter = ApiKeyFilter.builder().header(API_KEY_HEADER_NAME).build();
        apiKeyFilter.setAuthenticationManager(apiKeyAuthManager());

        return apiKeyFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/api/game/**").
                csrf().
                disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and()
                .addFilter(apiKeyFilter())
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
