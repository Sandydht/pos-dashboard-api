package com.flexpos.pos_dashboard_api.services.implementations;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.flexpos.pos_dashboard_api.services.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtServiceImplementation implements JwtService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.access-token-expiration}")
  private long accessTokenExpiration;

  @Value("${jwt.refresh-token-expiration}")
  private long refreshTokenExpiration;

  @Override
  public String generateAccessToken(String user_id) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.create()
        .withSubject(user_id)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpiration))
        .sign(algorithm);
  }

  @Override
  public String generateRefreshToken(String user_id) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.create()
        .withSubject(user_id)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpiration))
        .sign(algorithm);
  }

  @Override
  public UUID extractUserId(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    DecodedJWT decodedJWT = JWT.require(algorithm)
        .build()
        .verify(token);

    return UUID.fromString(decodedJWT.getSubject());
  }

  @Override
  public boolean isTokenValid(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      JWT.require(algorithm)
          .build()
          .verify(token);

      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
