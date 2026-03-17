package com.flexpos.pos_dashboard_api.services;

import com.flexpos.pos_dashboard_api.models.auth.AuthResponse;
import com.flexpos.pos_dashboard_api.models.auth.LoginUserRequest;
import com.flexpos.pos_dashboard_api.models.auth.RegisterUserRequest;
import com.flexpos.pos_dashboard_api.models.common.WebResponse;

public interface AuthService {
  
  public WebResponse<AuthResponse> registerUser(RegisterUserRequest request);

  public WebResponse<AuthResponse> loginUser(LoginUserRequest request);

  public WebResponse<String> logoutUser();

}
