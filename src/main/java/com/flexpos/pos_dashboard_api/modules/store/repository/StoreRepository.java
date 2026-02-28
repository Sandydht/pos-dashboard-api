package com.flexpos.pos_dashboard_api.modules.store.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexpos.pos_dashboard_api.modules.store.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {
  Optional<Store> findByCode(String code);
}
