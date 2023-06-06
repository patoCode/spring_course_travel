package com.f5.travel.api.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationResponse {

    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.YYYY HH:mm")
    private LocalDateTime dateTimeReservation;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate dateEnd;
    private Integer totalDays;
    private BigDecimal price;
    private HotelResponse _hotel;

}