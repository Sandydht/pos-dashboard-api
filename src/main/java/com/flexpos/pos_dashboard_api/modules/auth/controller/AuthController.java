package com.flexpos.pos_dashboard_api.modules.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flexpos.pos_dashboard_api.modules.auth.dto.request.LoginRequest;
import com.flexpos.pos_dashboard_api.modules.auth.dto.request.RegisterRequest;
import com.flexpos.pos_dashboard_api.modules.auth.dto.response.LogoutResponse;
import com.flexpos.pos_dashboard_api.modules.auth.dto.response.RegisterLoginResponse;
import com.flexpos.pos_dashboard_api.modules.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RestControllerAdvice
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<RegisterLoginResponse> register(@Valid @RequestBody RegisterRequest request) {
		RegisterLoginResponse response = authService.register(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<RegisterLoginResponse> login(@Valid @RequestBody LoginRequest request) {
		RegisterLoginResponse response = authService.login(request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/logout")
	public ResponseEntity<LogoutResponse> logout(@RequestHeader("Authorization") String authorizationHeader) {
		authService.logout(authorizationHeader);
		LogoutResponse response = LogoutResponse.builder().message("See you!").build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
