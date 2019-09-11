package com.example.jumble.application.handlers;

import com.example.jumble.application.transport.request.entities.CustomRequestEntity;
import com.example.jumble.application.validator.RequestEntityValidator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CustomHandlerTwo {

  public Mono<ServerResponse> customValidationTwo(ServerRequest request) {

    RequestEntityValidator validator = new RequestEntityValidator();

    Mono<String> responseBody = request.bodyToMono(CustomRequestEntity.class)
        .map(body -> {
          validator.validate(body, CustomRequestEntity.class);

          return String.format("Hi there, %s [%s]!", body.getName(), body.getCode());
        });

    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(responseBody, String.class);
  }

}
