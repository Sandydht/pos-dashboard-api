package com.flexpos.pos_dashboard_api.common.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends ClientException {
  public AuthorizationException(String message) {
    super(message, HttpStatus.FORBIDDEN.value());
  }
}
