package com.f5.travel.api.controlers;


import com.f5.travel.api.models.responses.FlyResponse;
import com.f5.travel.infraestructure.abstracts.IFly;
import com.f5.travel.util.SortType;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "fly")
@AllArgsConstructor
@Slf4j
public class FlyController {

  private final IFly _fly;

  @GetMapping
  public ResponseEntity<Page<FlyResponse>> get(@RequestParam Integer page,
                                               @RequestParam Integer size,
                                               @RequestHeader(required = false) SortType sortType){

    if(Objects.isNull(sortType)) sortType = SortType.NONE;
    var response = _fly.readAll(page, size, sortType);
    return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);

  }

  @GetMapping(path = "less_price")
  public ResponseEntity<Set<FlyResponse>> getLessPrice(@RequestParam BigDecimal price){
    var response = _fly.readLessPrice(price);
    return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
  }

  @GetMapping(path = "between_price")
  public ResponseEntity<Set<FlyResponse>> getBetweenPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max){
    var response = _fly.readBetweenPrice(min, max);
    return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
  }

  @GetMapping(path = "origin_destiny")
  public ResponseEntity<Set<FlyResponse>> getByOriginDestiny(@RequestParam String origin, @RequestParam String destiny){
    var response = _fly.readOriginDestiny(origin, destiny);
    return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
  }

}