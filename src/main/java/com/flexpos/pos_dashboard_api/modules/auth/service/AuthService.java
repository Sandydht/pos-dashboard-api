package com.flexpos.pos_dashboard_api.modules.auth.service;

import com.flexpos.pos_dashboard_api.modules.auth.dto.request.LoginRequest;
import com.flexpos.pos_dashboard_api.modules.auth.dto.request.RegisterRequest;
import com.flexpos.pos_dashboard_api.modules.auth.dto.response.RegisterLoginResponse;

public interface AuthService {
  RegisterLoginResponse register(RegisterRequest request);

  RegisterLoginResponse login(LoginRequest request);

  void logout(String authHeader);
}
