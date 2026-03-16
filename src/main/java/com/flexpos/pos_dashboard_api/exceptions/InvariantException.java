package com.flexpos.pos_dashboard_api.exceptions;

import org.springframework.http.HttpStatus;

public class InvariantException extends ClientException {
  
  public InvariantException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }

}