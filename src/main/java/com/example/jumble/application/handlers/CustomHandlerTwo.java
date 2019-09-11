package com.example.jumble.application.handlers;

import com.example.jumble.application.exception.types.ValidationException;
import com.example.jumble.application.transport.request.entities.CustomRequestEntity;
import com.example.jumble.application.transport.request.entities.RequestEntity;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CustomHandlerTwo {

  @Autowired
  private Validator validator;

  public Mono<ServerResponse> customValidationTwo(ServerRequest request) {

    Mono<String> responseBody = request.bodyToMono(CustomRequestEntity.class)
        .flatMap(body -> {

          Set<ConstraintViolation<RequestEntity>> errors = this.validator.validate(body);

          if(!errors.isEmpty()) {
            return Mono.error(new ValidationException(errors));
          }

          return Mono.just(String.format("Hi there, %s [%s]!", body.getName(), body.getCode()));
        });

    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseBody, String.class);
  }

}
