package com.flexpos.pos_dashboard_api.models.onboarding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreSetupRequest {

  @NotBlank(message = "Code is required")
  @Size(max = 100, message = "Code must be less than 100 characters")
  @Pattern(regexp = "^[A-Z]+(-[A-Z]+)+$", message = "Code is invalid")
  private String code;

  @NotBlank(message = "Name is required")
  @Size(max = 100, message = "Name must be less than 100 characters")
  private String name;

}
