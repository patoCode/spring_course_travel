package com.f5.travel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
// Configuracion necesaria para leer archivos
@PropertySource(value="classpath:configs/api_currency.properties")
public class PropertiesConfig {

}