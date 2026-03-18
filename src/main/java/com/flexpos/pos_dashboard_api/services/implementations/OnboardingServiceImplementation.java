package com.flexpos.pos_dashboard_api.services.implementations;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.flexpos.pos_dashboard_api.entities.StoreEntity;
import com.flexpos.pos_dashboard_api.entities.UserEntity;
import com.flexpos.pos_dashboard_api.enums.StoreStatus;
import com.flexpos.pos_dashboard_api.exceptions.InvariantException;
import com.flexpos.pos_dashboard_api.models.common.WebResponse;
import com.flexpos.pos_dashboard_api.models.onboarding.StoreSetupRequest;
import com.flexpos.pos_dashboard_api.models.onboarding.StoreSetupResponse;
import com.flexpos.pos_dashboard_api.repositories.StoreRepository;
import com.flexpos.pos_dashboard_api.repositories.UserRepository;
import com.flexpos.pos_dashboard_api.services.OnboardingService;
import com.flexpos.pos_dashboard_api.utils.SecurityUtil;
import com.flexpos.pos_dashboard_api.utils.ValidationUtil;

import jakarta.transaction.Transactional;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OnboardingServiceImplementation implements OnboardingService {

  private final Validator validator;
  private final StoreRepository storeRepository;
  private final UserRepository userRepository;

  @Transactional
  public WebResponse<StoreSetupResponse> storeSetup(StoreSetupRequest request) {
    ValidationUtil.validate(validator, request);
    UserEntity user = SecurityUtil.getCurrentUser(userRepository);

    if (storeRepository.existsByCode(request.getCode())) {
      throw new InvariantException("Store already exist");
    }

    StoreEntity store = new StoreEntity();
    store.setCode(request.getCode());
    store.setName(request.getName());
    store.setStatus(StoreStatus.ACTIVE);
    store.setUser(user);
    store.setCreatedAt(LocalDateTime.now());
    StoreEntity savedStore = storeRepository.save(store);

    StoreSetupResponse storeSetupResponse = StoreSetupResponse
        .builder()
        .id(savedStore.getId())
        .userId(savedStore.getUser().getId())
        .photoUrl(savedStore.getPhotoUrl())
        .code(savedStore.getCode())
        .name(savedStore.getName())
        .status(savedStore.getStatus())
        .createdAt(savedStore.getCreatedAt())
        .updatedAt(savedStore.getUpdatedAt())
        .deletedAt(savedStore.getDeletedAt())
        .build();

    return WebResponse
        .<StoreSetupResponse>builder()
        .status("CREATED")
        .data(storeSetupResponse)
        .build();
  }

}
