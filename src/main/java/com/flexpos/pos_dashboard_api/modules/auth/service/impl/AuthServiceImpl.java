package com.flexpos.pos_dashboard_api.modules.auth.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flexpos.pos_dashboard_api.common.exception.InvariantException;
import com.flexpos.pos_dashboard_api.config.security.JwtService;
import com.flexpos.pos_dashboard_api.modules.auth.dto.request.LoginRequest;
import com.flexpos.pos_dashboard_api.modules.auth.dto.request.RegisterRequest;
import com.flexpos.pos_dashboard_api.modules.auth.dto.response.RegisterLoginResponse;
import com.flexpos.pos_dashboard_api.modules.auth.entity.RefreshToken;
import com.flexpos.pos_dashboard_api.modules.auth.entity.Role;
import com.flexpos.pos_dashboard_api.modules.auth.entity.User;
import com.flexpos.pos_dashboard_api.modules.auth.mapper.AuthMapper;
import com.flexpos.pos_dashboard_api.modules.auth.repository.RefreshTokenRepository;
import com.flexpos.pos_dashboard_api.modules.auth.repository.RoleRepository;
import com.flexpos.pos_dashboard_api.modules.auth.repository.UserRepository;
import com.flexpos.pos_dashboard_api.modules.auth.service.AuthService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthMapper authMapper;

  @Override
  @Transactional
  public RegisterLoginResponse register(RegisterRequest request) {
    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
      throw new InvariantException("Username already registered");
    }

    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
      throw new InvariantException("Email already registered");
    }

    if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
      throw new InvariantException("Phone number already registered");
    }

    Role defaultRole = roleRepository.findByName("OWNER")
        .orElseThrow(() -> new InvariantException("Default role not found"));

    User user = User
        .builder()
        .username(request.getUsername())
        .email(request.getEmail())
        .phoneNumber(request.getPhoneNumber())
        .fullName(request.getFullName())
        .password(passwordEncoder.encode(request.getPassword()))
        .createdAt(LocalDateTime.now())
        .build();

    user.getRoles().add(defaultRole);

    User savedUser = userRepository.save(user);
    String accessToken = jwtService.generateAccessToken(savedUser);

    String refreshToken = jwtService.generateRefreshToken(savedUser);
    Instant expiresAt = jwtService.generateRefreshTokenExpiry();

    RefreshToken refreshTokenEntity = RefreshToken
        .builder()
        .token(refreshToken)
        .expiresAt(expiresAt)
        .user(user)
        .build();
    refreshTokenRepository.save(refreshTokenEntity);

    return RegisterLoginResponse
        .builder()
        .accessToken(accessToken)
        .user(authMapper.toUserResponse(user))
        .build();
  }

  @Override
  @Transactional
  public RegisterLoginResponse login(LoginRequest request) {
    User user = userRepository
        .findByEmailAndDeletedAtIsNull(request.getEmail())
        .orElseThrow(() -> new InvariantException("Invalid credentials"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new InvariantException("Invalid credentials");
    }

    User savedUser = userRepository.save(user);
    String accessToken = jwtService.generateAccessToken(savedUser);

    String refreshToken = jwtService.generateRefreshToken(savedUser);
    Instant expiresAt = jwtService.generateRefreshTokenExpiry();

    RefreshToken refreshTokenEntity = RefreshToken
        .builder()
        .token(refreshToken)
        .expiresAt(expiresAt)
        .user(user)
        .build();
    refreshTokenRepository.save(refreshTokenEntity);

    return RegisterLoginResponse
        .builder()
        .accessToken(accessToken)
        .user(authMapper.toUserResponse(user))
        .build();
  }

  @Override
  @Transactional
  public void logout(String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      throw new InvariantException("Invalid authorization header");
    }

    String token = authHeader.substring(7);
    UUID userId = jwtService.extractUserId(token);
    refreshTokenRepository.deleteAllByUser_Id(userId);
  }
}
