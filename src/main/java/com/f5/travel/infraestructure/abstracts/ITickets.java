package com.f5.travel.infraestructure.abstracts;

import com.f5.travel.api.models.requests.TicketRequest;
import com.f5.travel.api.models.responses.TicketResponse;

import java.math.BigDecimal;

public interface ITickets extends CrudService<TicketRequest, TicketResponse, Integer>{

    BigDecimal findLowerPrice(Integer idFly);

}