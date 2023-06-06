package com.f5.travel.infraestructure.concrete;

import com.f5.travel.api.models.responses.HotelResponse;
import com.f5.travel.domain.entities.HotelEntity;
import com.f5.travel.domain.repositories.HotelRepository;
import com.f5.travel.infraestructure.abstracts.IHotel;
import com.f5.travel.util.SortType;
import java.math.BigDecimal;
import java.util.Set;
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
public class HotelService implements IHotel {

  private final HotelRepository _hotel;



  @Override
  public Page<HotelResponse> readAll(Integer numberPage, Integer size, SortType sortType) {
    PageRequest pageRequest = null;
    switch (sortType){
      case NONE -> pageRequest = PageRequest.of(numberPage, size);
      case LOWER -> pageRequest = PageRequest.of(numberPage, size, Sort.by(FIELD_BY_SORT).ascending());
      case UPPER -> pageRequest = PageRequest.of(numberPage, size, Sort.by(FIELD_BY_SORT).descending());
    }
    return _hotel.findAll(pageRequest).map(this::_entityToResponse);
  }

  @Override
  public Set<HotelResponse> readLessPrice(BigDecimal price) {
    // TODO  pendiente de implementacion
    return null;
  }

  @Override
  public Set<HotelResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
    return null;
  }

  private HotelResponse _entityToResponse(HotelEntity entity){
    HotelResponse response = new HotelResponse();
    BeanUtils.copyProperties(entity, response);
    return response;
  }
}