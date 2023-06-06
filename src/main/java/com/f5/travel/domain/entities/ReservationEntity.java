package com.f5.travel.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  @Column(name = "id_reservation")
  private Integer id;

  @Column(name = "date_reservation")
  private LocalDateTime dateTimeReservation;
  private LocalDate dateStart;
  private LocalDate dateEnd;
  private Integer totalDays;
  private BigDecimal price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_hotel")
  private HotelEntity _hotel;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_tour", nullable = true)
  private TourEntity _tour;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_customer")
  private CustomerEntity _customer;



}