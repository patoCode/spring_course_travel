package com.f5.travel.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

// @Configuration -> CARGA UNA VEZ INICIADA LA APP
@Configuration
@OpenAPIDefinition(
    info = @Info(
                title= "TRAVEL API",
                version = "1.0",
                description = "Documentacion del API de travels del curso de UDEMY"
    )
)
public class OpenApiConfig {



}