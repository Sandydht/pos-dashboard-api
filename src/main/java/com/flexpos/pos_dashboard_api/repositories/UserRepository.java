package com.flexpos.pos_dashboard_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexpos.pos_dashboard_api.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
  
  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  boolean existsByPhoneNumber(String phone_number);

}
