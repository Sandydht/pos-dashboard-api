package com.flexpos.pos_dashboard_api.modules.auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexpos.pos_dashboard_api.modules.auth.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
  Optional<RefreshToken> findByUserId(String userId);

  void deleteAllByUser_Id(UUID userId);
}
