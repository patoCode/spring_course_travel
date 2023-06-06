package com.f5.travel.infraestructure.abstracts;

import com.f5.travel.api.models.requests.ReservationRequest;
import com.f5.travel.api.models.responses.ReservationResponse;

public interface IReservation extends CrudService<ReservationRequest, ReservationResponse, Integer>{

}