package com.f5.travel.domain.repositories;

import com.f5.travel.domain.entities.FlyEntity;
import java.math.BigDecimal;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlyRepository extends JpaRepository<FlyEntity, Integer> {

  @Query("SELECT f FROM fly f WHERE f.price < :price")
  Set<FlyEntity> selectLessPrice(@Param("price") BigDecimal filterPrice);

  @Query("SELECT f FROM fly f WHERE f.price between :min and :max")
  Set<FlyEntity> selectBetweenPrice(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

  @Query("SELECT f FROM fly f WHERE f.originName = :origin and f.destinyName = :destiny")
  Set<FlyEntity> selectOriginDestiny(@Param("origin") String origin, @Param("destiny") String destiny);

}