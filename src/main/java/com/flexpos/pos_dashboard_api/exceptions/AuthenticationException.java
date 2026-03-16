package com.flexpos.pos_dashboard_api.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends ClientException {

  public AuthenticationException(String message) {
    super(message, HttpStatus.UNAUTHORIZED);
  }

}
