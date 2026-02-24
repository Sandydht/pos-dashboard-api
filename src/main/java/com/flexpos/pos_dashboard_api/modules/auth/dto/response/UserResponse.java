package com.flexpos.pos_dashboard_api.modules.auth.dto.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
  private UUID id;
  private String photoUrl;
  private String username;
  private String email;
  private String phoneNumber;
  private String fullName;
  private Set<String> roles;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;
}
