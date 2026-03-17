package com.flexpos.pos_dashboard_api.models.auth;

import jakarta.validation.constraints.Email;
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
public class RegisterUserRequest {

  @NotBlank(message = "Username is required")
  @Size(max = 50, message = "Username must be less than 50 characters")
  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must not contain spaces or special characters")
  private String username;

  @NotBlank(message = "Email is required")
  @Email(message = "Email is invalid")
  @Size(max = 100, message = "Email must be less than 100 characters")
  private String email;

  @NotBlank(message = "Phone number is required")
  @Size(max = 20, message = "Phone number must be less than 20 characters")
  private String phoneNumber;

  @NotBlank(message = "Full name is required")
  @Size(max = 100, message = "Full name must be less than 100 characters")
  private String fullName;

  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Password must be at least 8 characters")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain uppercase, lowercase, number, and special character")
  private String password;

}
