#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.handlers;

import ${package}.application.transport.response.transformers.AppDetailTransformer;
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

    AppDetailTransformer appDetailTransformer = new AppDetailTransformer(
        "Test API",
        "v1.0.0",
        "A short description of the usage of the API"
    );

    return ServerResponse.ok()
        .body(Mono.just(appDetailTransformer), AppDetailTransformer.class);
  }
}
