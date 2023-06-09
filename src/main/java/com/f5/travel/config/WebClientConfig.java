package com.f5.travel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Value(value = "${BASE_URL}")
  private String baseUrl;

  @Value(value = "${API_KEY}")
  private String apiKey;

  @Value(value = "${API_KEY.HEADER}")
  private String apiHeader;



  @Bean(name = "currency")
  public WebClient currencyWebClient(){
    return WebClient.builder()
                    .baseUrl(this.baseUrl)
                    .defaultHeaders(this::addDefaultHeaders)
                    .build();
  }

  // VIDEO 102
  // Es necesario nombrar los BEAN para su uso, cuando se tenga mas de 1 webclient
  // <Primary o Qualifier> son dos notaciones que ayudan en la carga del WebClient, de lo contrario el proyecto no funciona
  // Dependiendo de la necesidad se usa PRIMARY en esta clase Webconfig,
  //                                    QUALIFIER se utiliza en la clase donde se utilizara el webCLient, ver doc de SpringBoot
  @Bean(name = "base")
  public WebClient baseWebClient(){
    return WebClient.builder()
        .baseUrl(this.baseUrl)
        .defaultHeaders(this::addDefaultHeaders)
        .build();
  }


  private void addDefaultHeaders(final HttpHeaders headers) {
    headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
    headers.add(HttpHeaders.ACCEPT, "application/json");
    headers.add("apikey", "YRwK8dP3ZLzxJNpeo1PIkj6JPPm386T3");
  }

}