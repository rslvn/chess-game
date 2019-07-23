package com.game.chess.console.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(value = "Login Response", description = "The login request payload")
public class TokenDto {

    @ApiModelProperty(value = "A valid access token", required = true, allowableValues = "NonEmpty String")
    private String accessToken;
}
