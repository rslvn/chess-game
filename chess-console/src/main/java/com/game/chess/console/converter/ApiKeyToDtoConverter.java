package com.game.chess.console.converter;

import com.game.chess.console.domain.dto.ApiKeyDto;
import com.game.chess.db.model.ApiKey;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyToDtoConverter implements Converter<ApiKey, ApiKeyDto> {

    @Override
    public ApiKeyDto convert(ApiKey apiKey) {
        return ApiKeyDto.builder()
                .apiKey(apiKey.getId())
                .build();
    }

}