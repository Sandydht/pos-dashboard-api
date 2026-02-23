package com.flexpos.pos_dashboard_api.modules.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterLoginResponse {
    private String accessToken;
    private UserResponse user;
}
