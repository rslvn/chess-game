package com.game.chess.console.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.chess.console.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData() {

        return new ApiInfo(
                "Chess Game API",
                "API documentation",
                "0.0.1",
                "",
                new Contact("Resul Avan", "https://github.com/rslvn", "avan.resul@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }
}
