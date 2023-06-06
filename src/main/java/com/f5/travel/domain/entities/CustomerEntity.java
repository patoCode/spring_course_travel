package com.f5.travel.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_customer")
  private Integer id;

  @Column(length = 12)
  private String dni;

  @Column(length = 50)
  private String fullName;

  @Column(length = 20)
  private String creditCard;

  @Column(length = 12)
  private String phoneNumber;

  private Integer totalFlights;
  private Integer totalLodings;
  private Integer totalTours;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true,
      mappedBy = "_customer"
  )
  private Set<TicketEntity> _tickets;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true,
      mappedBy = "_customer"
  )
  private Set<ReservationEntity> _reservations;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true,
      mappedBy = "_customer"
  )
  private Set<TourEntity> _tours;

}