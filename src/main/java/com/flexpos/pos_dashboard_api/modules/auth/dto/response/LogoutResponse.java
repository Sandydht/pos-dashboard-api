package com.flexpos.pos_dashboard_api.modules.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LogoutResponse {
  private String message;
}
