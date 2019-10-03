#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.filters;

import ${package}.application.exception.types.FilterException;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class RequestCheckFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

    final String[] ignoredRoutes = new String[]{
            "/actuator/prometheus"
    };

    List<String> contentType = exchange.getRequest().getHeaders().get(HttpHeaders.CONTENT_TYPE);
    String endpoint = exchange.getRequest().getPath().value();

    // check ignored routes
    if(Arrays.asList(ignoredRoutes).contains(endpoint)){
      return chain.filter(exchange);
    }

    if (contentType == null) {
      return Mono.error(new FilterException("No content type"));
    }

    if (!contentType.get(0).equals("application/json")) {
      return Mono.error(new FilterException("Only accepts JSON"));
    }

    return chain.filter(exchange);
  }
}
