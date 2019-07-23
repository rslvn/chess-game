package com.game.chess.console.controller;

import com.game.chess.console.annotation.CurrentUser;
import com.game.chess.console.domain.dto.ApiKeyDto;
import com.game.chess.console.domain.jwt.CustomUserDetails;
import com.game.chess.console.service.ConsoleApiKeyService;
import com.game.chess.db.model.ApiKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Bank account management services")
@Slf4j
@AllArgsConstructor(onConstructor_ = {@Autowired})
@RestController
@RequestMapping(ApiKeyController.SERVICE_PATH)
public class ApiKeyController {

    public static final String SERVICE_PATH = "console/api/key";

    private ConsoleApiKeyService consoleApiKeyService;
    private ConversionService conversionService;

    @ApiOperation(value = "Test security", response = ApiKeyDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 401, message = "Auth necessary"),
            @ApiResponse(code = 400, message = "Bad Request."),
            @ApiResponse(code = 500, message = "Internal Error.")
    })
    @PutMapping
    public ApiKeyDto saveApiKey(@CurrentUser CustomUserDetails userDetails) {
        LOG.info("/{} called", SERVICE_PATH);

        ApiKey apiKey = consoleApiKeyService.createApiKey(userDetails.getUser());

        return conversionService.convert(apiKey, ApiKeyDto.class);
    }

}
