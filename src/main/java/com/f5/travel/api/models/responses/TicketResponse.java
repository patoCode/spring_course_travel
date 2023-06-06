package com.f5.travel.api.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketResponse implements Serializable {

  private Integer idTicket;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.YYYY HH:mm")
  private LocalDateTime departureDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime arrivalDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.YYYY")
  private LocalDate purchaseDate;
  private BigDecimal price;
  private FlyResponse fly;

}