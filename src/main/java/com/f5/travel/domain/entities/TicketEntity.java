package com.f5.travel.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_ticket")
  private Integer idTicket;

  private BigDecimal price;
  private LocalDateTime departureDate;
  private LocalDateTime arrivalDate;
  private LocalDate datePurchase;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_fly")
  private FlyEntity _fly;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_tour", nullable = true)
  private TourEntity _tour;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_customer")
  private CustomerEntity _customer;

}