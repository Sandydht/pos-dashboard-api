package com.flexpos.pos_dashboard_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexpos.pos_dashboard_api.models.common.WebResponse;
import com.flexpos.pos_dashboard_api.models.onboarding.StoreSetupRequest;
import com.flexpos.pos_dashboard_api.models.onboarding.StoreSetupResponse;
import com.flexpos.pos_dashboard_api.services.OnboardingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/onboarding")
@RequiredArgsConstructor
public class OnboardingController {
  
  private final OnboardingService onboardingService;

  @PostMapping("/store-setup")
  public ResponseEntity<WebResponse<StoreSetupResponse>> storeSetup(@RequestBody StoreSetupRequest request) {
    WebResponse<StoreSetupResponse> response = onboardingService.storeSetup(request);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(response);
  }

}
