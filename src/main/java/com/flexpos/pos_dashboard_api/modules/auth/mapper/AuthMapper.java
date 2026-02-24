package com.flexpos.pos_dashboard_api.modules.auth.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.flexpos.pos_dashboard_api.modules.auth.dto.response.UserResponse;
import com.flexpos.pos_dashboard_api.modules.auth.entity.Role;
import com.flexpos.pos_dashboard_api.modules.auth.entity.User;

@Component
public class AuthMapper {
  public UserResponse toUserResponse(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .photoUrl(user.getPhotoUrl())
        .username(user.getUsername())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .fullName(user.getFullName())
        .roles(
            user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet()))
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .deletedAt(user.getDeletedAt())
        .build();
  }
}
