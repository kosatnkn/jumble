package com.example.jumble.application.handlers;

import com.example.jumble.application.transport.request.entities.CustomRequestEntity;
import com.example.jumble.application.validator.CustomRequestEntityValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class CustomHandler {

  public Mono<ServerResponse> customValidationOne(ServerRequest request) {

    Validator validator = new CustomRequestEntityValidator();

    Mono<String> responseBody = request
        .bodyToMono(CustomRequestEntity.class)
        .map(body -> {

          Errors errors = new BeanPropertyBindingResult(body, CustomRequestEntity.class.getName());

          validator.validate(body, errors);

          if (errors.getAllErrors().isEmpty()) {
            return String.format("Hi, %s [%s]!", body.getName(), body.getCode());
          } else {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                errors.getAllErrors().toString());
          }
        });

    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseBody, String.class);
  }

}
