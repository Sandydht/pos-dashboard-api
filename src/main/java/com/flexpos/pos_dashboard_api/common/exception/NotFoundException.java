package com.flexpos.pos_dashboard_api.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ClientException {
  public NotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND.value());
  }
}
