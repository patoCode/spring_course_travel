package com.f5.travel.domain.repositories;

import com.f5.travel.domain.entities.TicketEntity;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<TicketEntity, Integer> {

}