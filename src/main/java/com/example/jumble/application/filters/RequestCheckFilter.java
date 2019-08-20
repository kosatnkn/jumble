package com.example.jumble.application.filters;

import com.example.jumble.application.exception.types.FilterException;
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

    List<String> contentType = exchange.getRequest().getHeaders().get(HttpHeaders.CONTENT_TYPE);

    if (contentType == null) {
      return Mono.error(new FilterException("No content type"));
    }

    if (!contentType.get(0).equals("application/json")) {
      return Mono.error(new FilterException("Only accepts JSON"));
    }

    return chain.filter(exchange);
  }
}
