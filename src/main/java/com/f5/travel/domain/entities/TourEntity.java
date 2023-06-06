package com.f5.travel.domain.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "tour")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_tour")
  private Integer id;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true,
      mappedBy = "_tour"
  )
  private Set<ReservationEntity> _reservations;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true,
      mappedBy = "_tour"
  )
  private Set<TicketEntity> _tickets;

  @ManyToOne
  @JoinColumn(name = "id_customer")
  private CustomerEntity _customer;

  public void addTicket(TicketEntity ticket){
    if(Objects.isNull(this._tickets)) this._tickets = new HashSet<>();
    this._tickets.add(ticket);
  }

  public void removeTicket(Integer idTicket){
    if(Objects.isNull(this._tickets)) this._tickets = new HashSet<>();
    this._tickets.removeIf(ticket -> ticket.getIdTicket().equals(idTicket));
  }

  public void updateTicket() {
    this._tickets.forEach(ticket -> ticket.set_tour(this));
  }

  public void addReservation(ReservationEntity reservation){
    if(Objects.isNull(this._reservations)) this._reservations = new HashSet<>();
    this._reservations.add(reservation);
  }

  public void removeReservations(Integer idReservation){
    if(Objects.isNull(this._reservations)) this._reservations = new HashSet<>();
    this._reservations.removeIf(r -> r.getId().equals(idReservation));
  }

  public void updateReservation(){
    this._reservations.forEach( r -> r.set_tour(this));
  }

}