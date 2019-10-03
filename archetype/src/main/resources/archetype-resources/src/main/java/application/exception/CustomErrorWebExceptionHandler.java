#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Order(-2)
public class CustomErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

  // get value from environment properties
  @Value("${symbol_dollar}{debug:false}")
  private boolean isDebug;

  public CustomErrorWebExceptionHandler(CustomErrorAttributes customErrorAttributes,
      ApplicationContext applicationContext,
      ServerCodecConfigurer serverCodecConfigurer) {

    super(customErrorAttributes, new ResourceProperties(), applicationContext);
    super.setMessageWriters(serverCodecConfigurer.getWriters());
    super.setMessageReaders(serverCodecConfigurer.getReaders());
  }

  @Override
  protected RouterFunction<ServerResponse> getRoutingFunction(
      final ErrorAttributes errorAttributes) {

    return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
  }

  /**
   * Render the error response
   *
   * @param request request
   * @return repose
   */
  private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {

    // show stack trace when running in debug mode
    final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, isDebug);

    return ServerResponse.status(this.getStatusByError(getError(request)))
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(errorPropertiesMap));
  }

  /**
   * Get HTTP response code by error type
   *
   * @param error error
   * @return HTTP response code
   */
  private HttpStatus getStatusByError(Throwable error) {

    switch (error.getClass().getSimpleName()) {
      case "ValidationException":
        return HttpStatus.UNPROCESSABLE_ENTITY;
      case "FilterException":
      case "DomainException":
      case "WebClientException":
        return HttpStatus.BAD_REQUEST;
      default:
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }
}