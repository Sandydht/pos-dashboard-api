package com.flexpos.pos_dashboard_api.utils;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

public class ValidationUtil {

  public static <T> void validate(Validator validator, T request) {
    Set<ConstraintViolation<T>> violations = validator.validate(request);

    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

}
