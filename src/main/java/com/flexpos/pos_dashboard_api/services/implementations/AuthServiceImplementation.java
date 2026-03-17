package com.flexpos.pos_dashboard_api.services.implementations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flexpos.pos_dashboard_api.entities.RefreshTokenEntity;
import com.flexpos.pos_dashboard_api.entities.UserEntity;
import com.flexpos.pos_dashboard_api.exceptions.InvariantException;
import com.flexpos.pos_dashboard_api.exceptions.NotFoundException;
import com.flexpos.pos_dashboard_api.models.auth.AuthResponse;
import com.flexpos.pos_dashboard_api.models.auth.LoginUserRequest;
import com.flexpos.pos_dashboard_api.models.auth.RegisterUserRequest;
import com.flexpos.pos_dashboard_api.models.common.WebResponse;
import com.flexpos.pos_dashboard_api.repositories.AuthRepository;
import com.flexpos.pos_dashboard_api.repositories.UserRepository;
import com.flexpos.pos_dashboard_api.services.AuthService;
import com.flexpos.pos_dashboard_api.utils.ValidationUtil;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

  private final UserRepository userRepository;
  private final AuthRepository authRepository;
  private final Validator validator;
  private final PasswordEncoder passwordEncoder;
  private final JwtServiceImplementation jwtServiceImplementation;

  @Value("${jwt.refresh-token-expiration}")
  private long refreshTokenExpiration;

  @Transactional
  public WebResponse<AuthResponse> registerUser(RegisterUserRequest request) {
    ValidationUtil.validate(validator, request);

    if (userRepository.existsByUsername(request.getUsername())) {
      throw new InvariantException("Username already exist");
    }

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new InvariantException("Email already exist");
    }

    if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
      throw new InvariantException("Phone number already exist");
    }

    UserEntity user = new UserEntity();
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPhoneNumber(request.getPhoneNumber());
    user.setFullName(request.getFullName());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setCreatedAt(LocalDateTime.now());
    UserEntity savedUser = userRepository.save(user);

    String accessToken = jwtServiceImplementation.generateAccessToken(savedUser.getId().toString());
    String refreshToken = jwtServiceImplementation.generateRefreshToken(savedUser.getId().toString());
    Instant refreshTokenExpiresAt = Instant.now().plusMillis(refreshTokenExpiration);

    RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
    refreshTokenEntity.setToken(refreshToken);
    refreshTokenEntity.setExpiresAt(refreshTokenExpiresAt);
    refreshTokenEntity.setUser(savedUser);
    authRepository.save(refreshTokenEntity);

    AuthResponse authResponse = AuthResponse
        .builder()
        .accessToken(accessToken)
        .build();

    return WebResponse
        .<AuthResponse>builder()
        .status("CREATED")
        .data(authResponse)
        .build();
  }

  @Transactional
  public WebResponse<AuthResponse> loginUser(LoginUserRequest request) {
    ValidationUtil.validate(validator, request);

    UserEntity user = userRepository
        .findByEmail(request.getEmail())
        .orElseThrow(() -> new InvariantException("The credentials you entered are incorrect"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new InvariantException("Invalid credentials");
    }

    String accessToken = jwtServiceImplementation.generateAccessToken(user.getId().toString());
    String refreshToken = jwtServiceImplementation.generateRefreshToken(user.getId().toString());
    Instant refreshTokenExpiresAt = Instant.now().plusMillis(refreshTokenExpiration);

    RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
    refreshTokenEntity.setToken(refreshToken);
    refreshTokenEntity.setExpiresAt(refreshTokenExpiresAt);
    refreshTokenEntity.setUser(user);
    authRepository.save(refreshTokenEntity);

    AuthResponse authResponse = AuthResponse
        .builder()
        .accessToken(accessToken)
        .build();

    return WebResponse
        .<AuthResponse>builder()
        .status("CREATED")
        .data(authResponse)
        .build();
  }

  @Transactional
  public WebResponse<String> logoutUser() {
    UUID userId = (UUID) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();

    if (!userRepository.existsById(userId)) {
      throw new NotFoundException("User not found!");
    } 

    authRepository.deleteByUserId(userId);

    return WebResponse
        .<String>builder()
        .status("OK")
        .data("See you!")
        .build();
  }

}
