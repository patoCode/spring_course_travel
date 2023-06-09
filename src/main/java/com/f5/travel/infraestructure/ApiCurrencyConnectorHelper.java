package com.f5.travel.infraestructure;


import com.f5.travel.infraestructure.dto.CurrencyDTO;
import java.time.Duration;
import java.util.Currency;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;


@Component
public class ApiCurrencyConnectorHelper {

  public static final String SYMBOL_CURRENCY_QUERY_PARAM = "symbols";
  public static final String BASE_CURRENCY_QUERY_PARAM = "base";
  public static final String CURRENCY_PATH = "/exchangerates_data/latest";

  private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

  private final WebClient _currencyWebClient;

  @Value(value = "${BASE_CURRENCY}")
  private String baseCurrency;

  public ApiCurrencyConnectorHelper(@Qualifier("currency") WebClient currencyWebClient) {
    this._currencyWebClient = currencyWebClient;
  }

  public CurrencyDTO getCurrency(Currency currency){
    return this._currencyWebClient
          .get()
          .uri(
              uriBuilder -> uriBuilder
                  .path(CURRENCY_PATH)
                  .queryParam(SYMBOL_CURRENCY_QUERY_PARAM, currency.getCurrencyCode())
                  .queryParam(BASE_CURRENCY_QUERY_PARAM, baseCurrency)
                  .build()
          )
          .retrieve()
          .bodyToMono(CurrencyDTO.class)
          .block(REQUEST_TIMEOUT);
  }
}