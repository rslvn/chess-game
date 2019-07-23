package com.game.chess.console.converter;

import com.game.chess.console.domain.dto.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TokenToDtoConverter implements Converter<String, TokenDto> {

    private static final String TOKEN_FORMAT = "%s %s";

    @Value("${app.jwt.header.prefix}")
    private String tokenRequestHeaderPrefix;

    @Override
    public TokenDto convert(String accessToken) {
        return TokenDto.builder()
                .accessToken(String.format(TOKEN_FORMAT, tokenRequestHeaderPrefix, accessToken))
                .build();
    }

}