package com.npm.empenios.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean de configuracion para SwaggerOpenAPI
 */
@Configuration
public class ApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Empeños - Valuación de artículos")
                        .description("Documentación REST")
                        .version("v0.0.1"));
    }
}
