package com.f5.travel.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelResponse {

    private Integer idHotel;
    private String name;
    private String address;
    private BigDecimal price;
    private int rating;

}