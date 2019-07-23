package com.game.chess.console.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(value = "Created ApiKey", description = "The creating ApiKey response payload")
public class ApiKeyDto {

    @ApiModelProperty(value = "A valid apiKey", required = true, allowableValues = "NonEmpty String")
    private String apiKey;
}
