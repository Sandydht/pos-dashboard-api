package com.flexpos.pos_dashboard_api.modules.onboarding.service.impl;

import org.springframework.stereotype.Service;

import com.flexpos.pos_dashboard_api.modules.onboarding.service.OnboardingService;
import com.flexpos.pos_dashboard_api.modules.store.entity.Store;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OnboardingServiceImpl implements OnboardingService {
  @Override
  public String getOnboardingStatus() {
    return "";
  }

  @Override
  public Store getStoreDetail() {
    return null;
  }

  @Override
  public Store createStore(Store store) {
    return null;
  }
}