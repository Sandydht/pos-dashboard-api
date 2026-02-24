package com.flexpos.pos_dashboard_api.modules.auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexpos.pos_dashboard_api.modules.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
  Optional<Role> findByName(String name);
}
