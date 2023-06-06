package com.f5.travel.infraestructure.abstracts;

import com.f5.travel.util.SortType;
import java.math.BigDecimal;
import java.util.Set;
import org.springframework.data.domain.Page;

public interface CatalogService<RES> {

  String FIELD_BY_SORT = "price";
  Page<RES> readAll(Integer numberPage, Integer size, SortType sortType);

  Set<RES> readLessPrice(BigDecimal price);

  Set<RES> readBetweenPrice(BigDecimal min, BigDecimal max);



}