package com.event.management.infrastructure.persistence.repository;

import com.event.management.infrastructure.persistence.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity,String> {
}
