package com.flexpos.pos_dashboard_api.services;

import com.flexpos.pos_dashboard_api.models.common.WebResponse;
import com.flexpos.pos_dashboard_api.models.onboarding.StoreSetupRequest;
import com.flexpos.pos_dashboard_api.models.onboarding.StoreSetupResponse;

public interface OnboardingService {
  
  public WebResponse<StoreSetupResponse> storeSetup(StoreSetupRequest request);

}
