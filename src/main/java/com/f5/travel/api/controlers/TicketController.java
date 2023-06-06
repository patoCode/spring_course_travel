package com.f5.travel.api.controlers;

import com.f5.travel.api.models.requests.TicketRequest;
import com.f5.travel.api.models.responses.TicketResponse;
import com.f5.travel.infraestructure.abstracts.ITickets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "ticket")
@AllArgsConstructor
@Slf4j
public class TicketController {

  private final ITickets ticketService;

  @PostMapping
  public ResponseEntity<TicketResponse> post(@RequestBody TicketRequest request){
    return ResponseEntity.ok(ticketService.create(request));
  }

  // NOMBRE DEL PARAMETRO DEBE SER EL MISMO QUE LA VARIABLE DEL PATH
  @GetMapping(path="{id}")
  public ResponseEntity<TicketResponse> get(@PathVariable String id){
    Integer inputId = Integer.parseInt(id);
    return ResponseEntity.ok(ticketService.read(inputId));
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<TicketResponse> put(@RequestBody TicketRequest request, @PathVariable String id){
    Integer inputId = Integer.parseInt(id);
    return ResponseEntity.ok(ticketService.update(request, inputId));
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<Void> delete(@PathVariable String id){
    Integer inputId = Integer.parseInt(id);
    ticketService.delete(inputId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<Map<String, BigDecimal>> getFlyPrice(@RequestParam String id){
    Integer inputId = Integer.parseInt(id);
    return ResponseEntity.ok(Collections.singletonMap("flyPrice", ticketService.findLowerPrice(inputId)));
  }

}