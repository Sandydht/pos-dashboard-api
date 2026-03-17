package com.flexpos.pos_dashboard_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flexpos.pos_dashboard_api.exceptions.AuthenticationException;
import com.flexpos.pos_dashboard_api.exceptions.AuthorizationException;
import com.flexpos.pos_dashboard_api.exceptions.InvariantException;
import com.flexpos.pos_dashboard_api.exceptions.NotFoundException;
import com.flexpos.pos_dashboard_api.models.common.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController {

  private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message) {
    return ResponseEntity
        .status(status)
        .body(ErrorResponse.builder()
            .status("ERROR")
            .errors(message)
            .build());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> constraintViolationException(ConstraintViolationException exception) {
    return buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
  }

  @ExceptionHandler(InvariantException.class)
  public ResponseEntity<ErrorResponse> invariantException(InvariantException exception) {
    return buildResponse(exception.getStatusCode(), exception.getMessage());
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> authenticationException(AuthenticationException exception) {
    return buildResponse(exception.getStatusCode(), exception.getMessage());
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> notFoundException(NotFoundException exception) {
    return buildResponse(exception.getStatusCode(), exception.getMessage());
  }

  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<ErrorResponse> authorizationException(AuthorizationException exception) {
    return buildResponse(exception.getStatusCode(), exception.getMessage());
  }

}
