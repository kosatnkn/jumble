package com.example.jumble.application.exception.types;

import com.example.jumble.application.transport.request.entities.RequestEntity;
import java.util.Set;
import javax.validation.ConstraintViolation;

public class ValidationException extends Exception {

  private Set<ConstraintViolation<RequestEntity>> errors;

  public ValidationException(Set<ConstraintViolation<RequestEntity>> errors) {
    this.errors = errors;
  }

  public Set<ConstraintViolation<RequestEntity>> getErrors() {
    return this.errors;
  }
}
