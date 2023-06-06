package com.f5.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelApplication{
//implements CommandLineRunner {
//
//  private final HotelRepository _hotel;
//  private final CustomerRepository _customer;
//  private final TourRepository _tour;
//  private final TicketRepository _tk;
//  private final FlyRepository _fly;
//
//  public TravelApplication(HotelRepository _hotel, CustomerRepository _customer, TourRepository _tour, TicketRepository _tk, FlyRepository _fly) {
//    this._hotel = _hotel;
//    this._customer = _customer;
//    this._tour = _tour;
//    this._tk = _tk;
//    this._fly = _fly;
//  }

  public static void main(String[] args) {
    SpringApplication.run(TravelApplication.class, args);
    System.out.println("SPRING BOOT - 2023");
  }

//
//  @Override
//  public void run(String... args) throws Exception {
//    var fl = _fly.findById(1).orElseThrow();
//    var ct = _customer.findById(1).orElseThrow();
//    var ht = _hotel.findById(1).orElseThrow();
//
//    var tr = TourEntity.builder()
//        ._customer(ct)
//        .build();
//
//    var tk = TicketEntity.builder()
//        .price(fl.getPrice().multiply(BigDecimal.TEN))
//        .departureDate(LocalDateTime.now())
//        .arrivalDate(LocalDateTime.now())
//        .datePurchase(LocalDateTime.now())
//        ._customer(ct)
//        ._tour(tr)
//        ._fly(fl)
//        .build();
//
//    var rs = ReservationEntity.builder()
//        .dateTimeReservation(LocalDateTime.now())
//        .dateEnd(LocalDate.now().plusDays(2))
//        .dateStart(LocalDate.now().plusDays(1))
//        ._hotel(ht)
//        ._customer(ct)
//        ._tour(tr)
//        .totalDays(1)
//        .price(ht.getPrice().multiply(BigDecimal.TEN))
//        .build();
//
//    System.out.println(" -- SAVING START --");
//    tr.addReservation(rs);
//    tr.addTicket(tk);
//    tr.updateReservation();
//    tr.updateTicket();
//    var tourSaved = this._tour.save(tr);
//
//    var saved = this._tour.findById(tourSaved.getId()).get();
//    System.out.println(" -- SAVING END --");
//    System.out.println(" -- CUSTOMER DATA --");
//    System.out.println(saved.get_customer());
//
//    Thread.sleep(10000);
//    System.out.println(" -- DELETE TOUR --");
//    this._tour.deleteById(tourSaved.getId());
//
//
//  }
}