package com.flexpos.pos_dashboard_api.exceptions;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends ClientException {

  public AuthorizationException(String message) {
    super(message, HttpStatus.FORBIDDEN);
  }

}
