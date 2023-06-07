package com.f5.travel.infraestructure.concrete;

import com.f5.travel.api.models.requests.ReservationRequest;
import com.f5.travel.api.models.responses.HotelResponse;
import com.f5.travel.api.models.responses.ReservationResponse;
import com.f5.travel.domain.entities.ReservationEntity;
import com.f5.travel.domain.repositories.CustomerRepository;
import com.f5.travel.domain.repositories.HotelRepository;
import com.f5.travel.domain.repositories.ReservationRepository;
import com.f5.travel.infraestructure.ApiCurrencyConnectorHelper;
import com.f5.travel.infraestructure.BlackListHelper;
import com.f5.travel.infraestructure.abstracts.IReservation;
import com.f5.travel.util.exceptions.IdNotFoundException;
import java.util.Currency;
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
public class ReservationService implements IReservation {

  private final HotelRepository _hotel;
  private final CustomerRepository _customer;
  private final ReservationRepository _reservation;
  private final BlackListHelper _blackListHelper;
  private final ApiCurrencyConnectorHelper _apiCurrency;

  private static final BigDecimal charge_price_percentage = BigDecimal.valueOf(0.2);



  @Override
  public ReservationResponse create(ReservationRequest request) {
    _blackListHelper.isInBlackListCustomer(request.getIdClient());
    var hotel = _hotel.findById(request.getIdHotel()).orElseThrow(()-> new IdNotFoundException("hotel"));
    var customer = _customer.findById(request.getIdClient()).orElseThrow(()-> new IdNotFoundException("customer"));

    var reservationToPersist = ReservationEntity.builder()
        ._hotel(hotel)
        ._customer(customer)
        .totalDays(request.getTotalDays())
        .dateTimeReservation(LocalDateTime.now())
        .dateStart(LocalDate.now())
        .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
        .price(hotel.getPrice().add(hotel.getPrice().multiply(charge_price_percentage)))
        .build();
    var reservationSaved = _reservation.save(reservationToPersist);
    return _entityToResponse(reservationSaved);
  }

  @Override
  public ReservationResponse read(Integer id) {
    var reservationDB = _reservation.findById(id).orElseThrow(()-> new IdNotFoundException("reservation"));
    return _entityToResponse(reservationDB);
  }

  @Override
  public ReservationResponse update(ReservationRequest request, Integer id) {
    var hotel = _hotel.findById(request.getIdHotel()).orElseThrow(()-> new IdNotFoundException("hotel"));
    var customer = _customer.findById(request.getIdClient()).orElseThrow(()-> new IdNotFoundException("customer"));

    var reservationToUpdate = _reservation.findById(id).orElseThrow();

    reservationToUpdate.set_hotel(hotel);
    reservationToUpdate.setTotalDays(request.getTotalDays());
    reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
    reservationToUpdate.setDateStart(LocalDate.now());
    reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
    reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charge_price_percentage)));

    var reservationSaved = _reservation.save(reservationToUpdate);
    return _entityToResponse(reservationSaved);
  }

  @Override
  public void delete(Integer id) {
    var reservationToDelete = _reservation.findById(id).orElseThrow(()-> new IdNotFoundException("reservation"));
    _reservation.delete(reservationToDelete);
  }


  private ReservationResponse _entityToResponse(ReservationEntity entity){
    var reservationResponse = new ReservationResponse();
    BeanUtils.copyProperties(entity, reservationResponse);

    var hotelResponse = new HotelResponse();
    BeanUtils.copyProperties(entity.get_hotel(), hotelResponse);
    reservationResponse.set_hotel(hotelResponse);

    return reservationResponse;
  }

  @Override
  public BigDecimal findPrice(Integer hotelId, Currency currency) {
    var hotel = _hotel.findById(hotelId).orElseThrow(()-> new IdNotFoundException("hotel"));
    var priceInDollars = hotel.getPrice().add(hotel.getPrice().multiply(charge_price_percentage));
    if(currency.equals(Currency.getInstance("USD"))) return priceInDollars;
    var currencyDto = _apiCurrency.getCurrency(currency);
    // log.info("API CURRENCY WORKS IN {}, WITH CONVERTION VALUE: {}", currencyDto.getExchangeDate().toString(), currencyDto.getRates().toString());
    return null;//priceInDollars.multiply(currencyDto.getRates().get(currency));
  }
}