package com.flexpos.pos_dashboard_api.repositories;

import com.flexpos.pos_dashboard_api.entities.RefreshTokenEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<RefreshTokenEntity, UUID> {
  
  void deleteByUserId(UUID userId);

}
