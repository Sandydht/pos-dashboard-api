package com.flexpos.pos_dashboard_api.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ClientException {

  public NotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }

}
