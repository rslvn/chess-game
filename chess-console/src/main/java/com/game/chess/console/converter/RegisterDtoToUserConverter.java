package com.game.chess.console.converter;

import com.game.chess.console.domain.dto.RegistrationDto;
import com.game.chess.db.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegisterDtoToUserConverter implements Converter<RegistrationDto, User> {

    @Override
    public User convert(RegistrationDto registrationDto) {
        return User.builder()
                .email(registrationDto.getEmail())
                .password(registrationDto.getPassword())
                .build();
    }
}
