package com.example.jumble.application.validator;

import com.example.jumble.application.exception.types.ValidationException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestEntityValidator {

  @Autowired
  private Validator validator;

  public void validate(RequestEntity target) throws ValidationException {

    Set<ConstraintViolation<RequestEntity>> errors = this.validator.validate(target);

    if(!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }
}