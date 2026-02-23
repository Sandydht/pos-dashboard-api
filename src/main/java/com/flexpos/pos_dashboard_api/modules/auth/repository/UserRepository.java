package com.flexpos.pos_dashboard_api.modules.auth.repository;

import com.flexpos.pos_dashboard_api.modules.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
