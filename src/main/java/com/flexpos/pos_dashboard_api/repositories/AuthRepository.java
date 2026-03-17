package com.flexpos.pos_dashboard_api.repositories;

import com.flexpos.pos_dashboard_api.entities.RefreshTokenEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<RefreshTokenEntity, String> {
  
  boolean existsByUserId(String user_id);
  
}
