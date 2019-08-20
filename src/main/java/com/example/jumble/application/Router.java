package com.example.jumble.application;

import com.example.jumble.application.handlers.AppDetailHandler;
import com.example.jumble.application.handlers.MovieHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {

  @Bean
  public RouterFunction<ServerResponse> appDetailRoutes(AppDetailHandler appDetailHandler) {

    return RouterFunctions.route(RequestPredicates.GET("/"), appDetailHandler::details);
  }

  @Bean
  public RouterFunction<ServerResponse> movieRoutes(MovieHandler movieHandler) {

    return RouterFunctions
        .route(RequestPredicates.GET("/movie"), movieHandler::all)
        .andRoute(RequestPredicates.GET("/movie/{id}"), movieHandler::byId)
        .andRoute(RequestPredicates.GET("/movie/{id}/event"), movieHandler::events);
  }
}
