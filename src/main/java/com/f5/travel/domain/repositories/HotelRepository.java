package com.f5.travel.domain.repositories;

import com.f5.travel.domain.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {

}