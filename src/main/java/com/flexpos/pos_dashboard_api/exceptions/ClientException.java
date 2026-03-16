package com.flexpos.pos_dashboard_api.exceptions;

import org.springframework.http.HttpStatus;

public class ClientException extends RuntimeException {

  private final HttpStatus statusCode;

  public ClientException(String message) {
    super(message);
    this.statusCode = HttpStatus.BAD_REQUEST;
  }

  public ClientException(String message, HttpStatus statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }
}
