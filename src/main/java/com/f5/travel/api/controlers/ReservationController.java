package com.f5.travel.api.controlers;

import com.f5.travel.api.models.requests.ReservationRequest;
import com.f5.travel.api.models.responses.ReservationResponse;
import com.f5.travel.infraestructure.abstracts.IReservation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="reservation")
@AllArgsConstructor
public class ReservationController {

  private final IReservation reservationService;

  @PostMapping
  public ResponseEntity<ReservationResponse> post(@RequestBody ReservationRequest request){
    return ResponseEntity.ok(reservationService.create(request));
  }

  @GetMapping(path="{id}")
  public ResponseEntity<ReservationResponse> get(@PathVariable String id){
    Integer inputId = Integer.parseInt(id);
    return ResponseEntity.ok(reservationService.read(inputId));
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<ReservationResponse> put(@RequestBody ReservationRequest request, @PathVariable String id){
    Integer inputId = Integer.parseInt(id);
    return ResponseEntity.ok(reservationService.update(request, inputId));
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<Void> delete(@PathVariable String id){
    Integer inputId = Integer.parseInt(id);
    reservationService.delete(inputId);
    return ResponseEntity.noContent().build();
  }

}