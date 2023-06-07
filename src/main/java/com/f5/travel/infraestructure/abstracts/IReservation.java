package com.f5.travel.infraestructure.abstracts;

import com.f5.travel.api.models.requests.ReservationRequest;
import com.f5.travel.api.models.responses.ReservationResponse;
import java.math.BigDecimal;
import java.util.Currency;

public interface IReservation extends CrudService<ReservationRequest, ReservationResponse, Integer>{
  BigDecimal findPrice(Integer hotelId, Currency currency);
}