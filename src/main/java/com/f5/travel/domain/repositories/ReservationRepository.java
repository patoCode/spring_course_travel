package com.f5.travel.domain.repositories;

import com.f5.travel.domain.entities.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {

}