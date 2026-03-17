package com.flexpos.pos_dashboard_api.services;

import java.util.UUID;

public interface JwtService {

  public String generateAccessToken(String userId);

  public String generateRefreshToken(String userId);

  public UUID extractUserId(String token);

  boolean isTokenValid(String token);

}
