package com.flexpos.pos_dashboard_api.modules.onboarding.service;

import com.flexpos.pos_dashboard_api.modules.store.entity.Store;

public interface OnboardingService {
  String getOnboardingStatus();
  Store getStoreDetail();
  Store createStore(Store store);
} 