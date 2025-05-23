package com.example.products.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Products API")
                        .version("v1")
                        .description("API for managing products and categories. Version 1."));
//                .servers(List.of(
//                        new Server().url("/").description("Default Server URL")
//                ));
    }
}
