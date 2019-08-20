package com.example.jumble.application.handlers;

import com.example.jumble.application.entities.AppDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AppDetailHandler {

  /**
   * Handle get API details
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> details(ServerRequest request) {

    AppDetail appDetail = new AppDetail(
        "Test API",
        "v1.0.0",
        "A short description of the usage of the API"
    );

    return ServerResponse.ok()
        .body(Mono.just(appDetail), AppDetail.class);
  }
}
