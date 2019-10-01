package com.example.jumble.application.exception.types;

import com.example.jumble.application.validator.RequestEntityInterface;
import java.util.Set;
import javax.validation.ConstraintViolation;

public class ValidationException extends RuntimeException {

  private Set<ConstraintViolation<RequestEntityInterface>> errors;

  public ValidationException(Set<ConstraintViolation<RequestEntityInterface>> errors) {
    this.errors = errors;
  }

  public Set<ConstraintViolation<RequestEntityInterface>> getErrors() {
    return this.errors;
  }
}
