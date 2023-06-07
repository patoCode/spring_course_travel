package com.f5.travel.infraestructure.concrete;

import com.f5.travel.api.models.requests.TicketRequest;
import com.f5.travel.api.models.responses.FlyResponse;
import com.f5.travel.api.models.responses.TicketResponse;
import com.f5.travel.domain.entities.TicketEntity;
import com.f5.travel.domain.repositories.CustomerRepository;
import com.f5.travel.domain.repositories.FlyRepository;
import com.f5.travel.domain.repositories.TicketRepository;
import com.f5.travel.infraestructure.BlackListHelper;
import com.f5.travel.infraestructure.abstracts.ITickets;
import com.f5.travel.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketServices implements ITickets {

  private final FlyRepository _fly;
  private final CustomerRepository _customer;
  private final TicketRepository _ticket;
  private BlackListHelper _blackListHelper;

  private static final BigDecimal charge_price_percentage = BigDecimal.valueOf(0.25);

  @Override
  public TicketResponse create(TicketRequest request) {
  _blackListHelper.isInBlackListCustomer(request.getIdClient());
    var fly = _fly.findById(request.getIdFly()).orElseThrow(()-> new IdNotFoundException("fly"));
    var customer = _customer.findById(request.getIdClient()).orElseThrow(()-> new IdNotFoundException("customer"));
    var ticketToPersist = TicketEntity.builder()
        ._fly(fly)
        ._customer(customer)
        .price(fly.getPrice().multiply(charge_price_percentage))
        .datePurchase(LocalDate.now())
        .arrivalDate(LocalDateTime.now())
        .departureDate(LocalDateTime.now())
        .build();
    var ticketDB = this._ticket.save(ticketToPersist);
    return _entityToResponse(ticketToPersist);
  }

  @Override
  public TicketResponse read(Integer id) {
    var ticketDB = this._ticket.findById(id).orElseThrow(()-> new IdNotFoundException("ticket"));
    return _entityToResponse(ticketDB);
  }

  @Override
  public TicketResponse update(TicketRequest request, Integer id) {

    var ticketToUpdate = _ticket.findById(id).orElseThrow(()-> new IdNotFoundException("ticket"));
    var flyDB = _fly.findById(request.getIdFly()).orElseThrow(()-> new IdNotFoundException("fly"));
    var customerDB = _customer.findById(request.getIdClient()).orElseThrow(()-> new IdNotFoundException("customer"));

    ticketToUpdate.set_customer(customerDB);
    ticketToUpdate.set_fly(flyDB);
    ticketToUpdate.setPrice(flyDB.getPrice().multiply(charge_price_percentage));

    var ticketUpdated = _ticket.save(ticketToUpdate);
    return _entityToResponse(ticketUpdated);

  }

  @Override
  public void delete(Integer id) {
    var ticketToDelete = _ticket.findById(id).orElseThrow(()-> new IdNotFoundException("ticket"));
    _ticket.delete(ticketToDelete);
  }

  @Override
  public BigDecimal findLowerPrice(Integer idFly){
    var fly = _fly.findById(idFly).orElseThrow(()-> new IdNotFoundException("fly"));
    return fly.getPrice().add(fly.getPrice().multiply(charge_price_percentage));
  }

  private TicketResponse _entityToResponse(TicketEntity entity){
    var ticketResponse = new TicketResponse();
    BeanUtils.copyProperties(entity, ticketResponse);
    var flyResponse = new FlyResponse();
    BeanUtils.copyProperties(entity.get_fly(), flyResponse);
    ticketResponse.setFly(flyResponse);

    return ticketResponse;
  }
}