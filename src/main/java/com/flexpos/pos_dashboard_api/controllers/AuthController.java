package com.flexpos.pos_dashboard_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexpos.pos_dashboard_api.models.auth.AuthResponse;
import com.flexpos.pos_dashboard_api.models.auth.RegisterUserRequest;
import com.flexpos.pos_dashboard_api.models.common.WebResponse;
import com.flexpos.pos_dashboard_api.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<WebResponse<AuthResponse>> registerUser(@RequestBody RegisterUserRequest request) {
    WebResponse<AuthResponse> response = authService.registerUser(request);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(response);
  }

}
