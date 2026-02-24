package com.flexpos.pos_dashboard_api.modules.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
  @NotBlank(message = "Username is required")
  @Size(max = 50, message = "Username must be maximum 50 characters")
  @Pattern(regexp = "^[\\w]+$", message = "Username contains restricted character")
  private String username;

  @NotBlank(message = "Email is required")
  @Email(message = "Email is invalid format")
  private String email;

  @NotBlank(message = "Phone Number is required")
  @Pattern(regexp = "^(?:\\\\+62|62|08)[0-9]{8,11}$", message = "Phone Number must be a valid Indonesian number (08xxx or +62xxx)")
  private String phoneNumber;

  @NotBlank(message = "Full Name is required")
  private String fullName;

  @NotBlank(message = "Password is required")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$", message = "Password must be at least 8 characters, must contain at least 1 uppercase letter, 1 lowercase letter, 1 number, and 1 special character")
  private String password;
}
