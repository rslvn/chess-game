package com.game.chess.console.converter;

import com.game.chess.console.domain.dto.LoginDto;
import com.game.chess.db.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoginDtoToUserConverter implements Converter<LoginDto, User> {

    @Override
    public User convert(LoginDto loginDto) {
        return User.builder()
                .email(loginDto.getEmail())
                .password(loginDto.getPassword())
                .build();
    }

}