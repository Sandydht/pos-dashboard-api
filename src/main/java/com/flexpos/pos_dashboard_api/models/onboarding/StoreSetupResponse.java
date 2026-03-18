package com.flexpos.pos_dashboard_api.models.onboarding;

import java.time.LocalDateTime;
import java.util.UUID;

import com.flexpos.pos_dashboard_api.enums.StoreStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreSetupResponse {
  
  private UUID id;
  private UUID userId;
  private String photoUrl;
  private String code;
  private String name;
  private StoreStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

}
