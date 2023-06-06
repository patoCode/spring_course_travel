package com.f5.travel.infraestructure.concrete;

import com.f5.travel.api.models.responses.FlyResponse;
import com.f5.travel.domain.entities.FlyEntity;
import com.f5.travel.domain.repositories.FlyRepository;
import com.f5.travel.infraestructure.abstracts.IFly;
import com.f5.travel.util.SortType;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class FlyService implements IFly {

  private final FlyRepository _fly;

  @Override
  public Page<FlyResponse> readAll(Integer numberPage, Integer size, SortType sortType) {

    PageRequest pageRequest = null;
    switch (sortType){
      case NONE -> pageRequest = PageRequest.of(numberPage, size);
      case LOWER -> pageRequest = PageRequest.of(numberPage, size, Sort.by(FIELD_BY_SORT).ascending());
      case UPPER -> pageRequest = PageRequest.of(numberPage, size, Sort.by(FIELD_BY_SORT).descending());
    }
    return _fly.findAll(pageRequest).map(this::_entityToResponse);

  }

  @Override
  public Set<FlyResponse> readLessPrice(BigDecimal price) {
    return _fly.selectLessPrice(price)
               .stream()
               .map(this::_entityToResponse)
               .collect(Collectors.toSet());
  }

  @Override
  public Set<FlyResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
    return _fly.selectBetweenPrice(min, max)
        .stream()
        .map(this::_entityToResponse)
        .collect(Collectors.toSet());
  }

  @Override
  public Set<FlyResponse> readOriginDestiny(String origen, String destiny) {
    return _fly.selectOriginDestiny(origen, destiny)
        .stream()
        .map(this::_entityToResponse)
        .collect(Collectors.toSet());
  }

  private FlyResponse _entityToResponse(FlyEntity entity){
    FlyResponse response = new FlyResponse();
    BeanUtils.copyProperties(entity, response);
    return response;
  }

}