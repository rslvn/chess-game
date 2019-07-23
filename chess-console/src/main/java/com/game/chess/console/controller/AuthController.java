package com.game.chess.console.controller;

import com.game.chess.console.domain.dto.LoginDto;
import com.game.chess.console.domain.dto.RegistrationDto;
import com.game.chess.console.domain.dto.TokenDto;
import com.game.chess.console.service.AuthService;
import com.game.chess.db.model.User;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api("User authentication services")
@Slf4j
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RestController
@RequestMapping(AuthController.SERVICE_PATH)
public class AuthController {

    public static final String SERVICE_PATH = "console/auth";
    public static final String METHOD_REGISTER = "/register";
    public static final String METHOD_LOGIN = "/login";

    private static final String ERROR_MESSAGE_EMAIL_NULL = "Email can not be null";
    private static final String ERROR_MESSAGE_PSWD_NULL = "Password can not be null";

    private AuthService authService;
    private ConversionService conversionService;

    @ApiOperation(value = "Logs the user in to the system and return the auth tokens", response = TokenDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "Internal Error.")
    })
    @PostMapping(value = METHOD_LOGIN)
    public TokenDto login(@ApiParam(value = "The number of the customer") @RequestBody @Valid LoginDto loginDto) {
        LOG.info("/{}{} called. {}", SERVICE_PATH, METHOD_LOGIN, loginDto);
        validate(loginDto);
        User user = conversionService.convert(loginDto, User.class);

        String accessToken = authService.login(user);

        return conversionService.convert(accessToken, TokenDto.class);
    }

    @ApiOperation(value = "Registers users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "Internal Error.")
    })
    @PostMapping(value = METHOD_REGISTER)
    public void register(@ApiParam(value = "The number of the customer") @RequestBody @Valid RegistrationDto registrationDto) {
        LOG.info("/{}{} called. {}", SERVICE_PATH, METHOD_REGISTER, registrationDto);
        validate(registrationDto);
        User user = conversionService.convert(registrationDto, User.class);

        authService.register(user);
    }


    private void validate(LoginDto loginDto) {
        // we used checkArgument instead of checkNotNull because we want an IllegalArgumentException
        Preconditions.checkArgument(loginDto.getEmail() != null, ERROR_MESSAGE_EMAIL_NULL);
        Preconditions.checkArgument(loginDto.getPassword() != null, ERROR_MESSAGE_PSWD_NULL);

    }

    private void validate(RegistrationDto registrationDto) {
        // we used checkArgument instead of checkNotNull because we want an IllegalArgumentException
        Preconditions.checkArgument(registrationDto.getEmail() != null, ERROR_MESSAGE_EMAIL_NULL);
        Preconditions.checkArgument(registrationDto.getPassword() != null, ERROR_MESSAGE_PSWD_NULL);

    }
}
