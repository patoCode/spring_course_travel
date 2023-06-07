package com.f5.travel.api.controlers;

import com.f5.travel.api.models.requests.ReservationRequest;
import com.f5.travel.api.models.responses.ReservationResponse;
import com.f5.travel.infraestructure.abstracts.IReservation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="reservation")
@AllArgsConstructor
public class ReservationController {

  private final IReservation reservationService;

  @PostMapping
  public ResponseEntity<ReservationResponse> post(@Valid @RequestBody ReservationRequest request){
    return ResponseEntity.ok(reservationService.create(request));
  }

  @GetMapping(path="{id}")
  public ResponseEntity<ReservationResponse> get(@PathVariable String id){
    Integer inputId = Integer.parseInt(id);
    return ResponseEntity.ok(reservationService.read(inputId));
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<ReservationResponse> put(@Valid @RequestBody ReservationRequest request, @PathVariable String id){
    Integer inputId = Integer.parseInt(id);
    return ResponseEntity.ok(reservationService.update(request, inputId));
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<Void> delete(@PathVariable String id){
    Integer inputId = Integer.parseInt(id);
    reservationService.delete(inputId);
    return ResponseEntity.noContent().build();
  }


  @Operation(summary = "return a reservation price given a hotel id")
  @GetMapping
  public ResponseEntity<Map<String, BigDecimal>> getReservationPrice(
      @RequestParam Integer hotelId,
      @RequestHeader(required = false)Currency currency
  ){
    if(Objects.isNull(currency)) currency = Currency.getInstance("USD");
    return ResponseEntity.ok(Collections.singletonMap("ticketPrice", reservationService.findPrice(hotelId, currency)));
  }

}