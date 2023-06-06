package com.f5.travel.domain.repositories;

import com.f5.travel.domain.entities.TourEntity;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<TourEntity, Integer> {

}