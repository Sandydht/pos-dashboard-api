package com.flexpos.pos_dashboard_api.common.exception;

public class ClientException extends RuntimeException {
  private final int statusCode;

  public ClientException(String message) {
    super(message);
    this.statusCode = 400;
  }

  public ClientException(String message, int statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
