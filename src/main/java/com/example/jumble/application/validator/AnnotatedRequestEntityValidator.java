package com.example.jumble.application.validator;

import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AnnotatedRequestEntityValidator {

  @Autowired
  private Validator validator;

  public void validate(Object target) {

    if(!this.validator.validate(target).isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          "Validation Error");
    }

  }
}