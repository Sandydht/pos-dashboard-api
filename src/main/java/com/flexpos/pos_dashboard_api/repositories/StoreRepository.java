package com.flexpos.pos_dashboard_api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexpos.pos_dashboard_api.entities.StoreEntity;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {
  
  boolean existsByCode(String code);

}
