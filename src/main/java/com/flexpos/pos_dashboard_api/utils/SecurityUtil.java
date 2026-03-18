package com.flexpos.pos_dashboard_api.utils;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.flexpos.pos_dashboard_api.entities.UserEntity;
import com.flexpos.pos_dashboard_api.exceptions.AuthorizationException;
import com.flexpos.pos_dashboard_api.repositories.UserRepository;

public class SecurityUtil {

  public static UUID getCurrentUserId() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth == null || auth.getPrincipal() == null) {
      throw new AuthorizationException("Unauthorized");
    }

    Object principal = auth.getPrincipal();

    if (principal instanceof UUID uuid) {
      return uuid;
    }

    if (principal instanceof String str) {
      try {
        return UUID.fromString(str);
      } catch (Exception e) {
        throw new AuthorizationException("Invalid user ID in token");
      }
    }

    throw new AuthorizationException("Invalid authentication principal");
  }

  public static UserEntity getCurrentUser(UserRepository userRepository) {
    UUID userId = getCurrentUserId();

    return userRepository.findById(userId)
        .orElseThrow(() -> new AuthorizationException("Forbidden"));
  }

}
