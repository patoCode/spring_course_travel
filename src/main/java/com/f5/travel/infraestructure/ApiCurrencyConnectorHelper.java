package com.f5.travel.infraestructure;


import com.f5.travel.infraestructure.dto.CurrencyDTO;
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
@Slf4j
@Data
public class ApiCurrencyConnectorHelper {

  public static final String SYMBOL_CURRENCY_QUERY_PARAM = "symbols";
  public static final String BASE_CURRENCY_QUERY_PARAM = "base";
  public static final String CURRENCY_PATH = "/exchangerates_data/lastest";

  private final WebClient _currencyWebClient;

  @Value(value = "${BASE_CURRENCY}")
  private String baseCurrency;

  public ApiCurrencyConnectorHelper(@Qualifier("currency") WebClient currencyWebClient) {
    _currencyWebClient = currencyWebClient;
  }

  public CurrencyDTO getCurrency(Currency currency){
    try {
      Mono<Object[]> response = this._currencyWebClient
          .get()
          .uri(
              uriBuilder -> uriBuilder
                  .path(CURRENCY_PATH)
                  .queryParam(SYMBOL_CURRENCY_QUERY_PARAM, currency.getCurrencyCode())
                  .queryParam(BASE_CURRENCY_QUERY_PARAM, baseCurrency)
                  .build()
          )
          .accept(MediaType.APPLICATION_JSON)
          .retrieve()
          .bodyToMono(Object[].class).log();
      Object[] objects = response.block();
      log.info("SALIDA "+objects.toString());
      return null;
    } catch(WebClientResponseException e){
      log.info("ERROR!!!!!!!!!!!!!! "+e.getMessage());
      return null;
    }
  }
}