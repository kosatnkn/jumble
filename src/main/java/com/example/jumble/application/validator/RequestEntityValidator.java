package com.example.jumble.application.validator;

import com.example.jumble.application.transport.request.entities.CustomRequestEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

public class RequestEntityValidator implements Validator {

  private static final int MINIMUM_CODE_LENGTH = 6;

  @Override
  public boolean supports(Class<?> clazz) {
    return CustomRequestEntity.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "field.required");
    CustomRequestEntity request = (CustomRequestEntity) target;
    if (request.getCode() != null && request.getCode()
        .trim()
        .length() < MINIMUM_CODE_LENGTH) {
      errors.rejectValue("code", "field.min.length", new Object[] { Integer.valueOf(MINIMUM_CODE_LENGTH) }, "The code must be at least [" + MINIMUM_CODE_LENGTH + "] characters in length.");
    }
  }

  public void validate(Object target, Class<?> requestEntity) {

    Errors errors = new BeanPropertyBindingResult(target, requestEntity.getName());

    this.validate(target, errors);

    if (!errors.getAllErrors().isEmpty()) {

      // TODO: throw a validation exception here so that error handler can catch it
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST,
          errors.getAllErrors().toString());
    }
  }
}