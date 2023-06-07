package com.f5.travel.infraestructure.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {

  private String success;
  private String timestamp;
  private String base;
  @JsonProperty(value = "date")
  private LocalDate exchangeDate;
  private Map<Currency, BigDecimal> rates;

}