package com.example.jumble.application;

import com.example.jumble.application.handlers.AppDetailHandler;
import com.example.jumble.application.handlers.CustomHandler;
import com.example.jumble.application.handlers.CustomHandlerTwo;
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
        .andRoute(RequestPredicates.GET("/movie/{id}"), movieHandler::byId);
  }

  @Bean
  public RouterFunction<ServerResponse> customRoutes(CustomHandler customHandler) {

    return RouterFunctions
        .route(RequestPredicates.POST("/custom_one"), customHandler::customValidationOne);
//        .andRoute(RequestPredicates.POST("/custom_two"), customHandler::customValidationTwo);
  }

  @Bean
  public RouterFunction<ServerResponse> customRoutesTwo(CustomHandlerTwo customHandlerTwo) {

    return RouterFunctions
        .route(RequestPredicates.POST("/custom_two"), customHandlerTwo::customValidationTwo);
//        .andRoute(RequestPredicates.POST("/custom_two"), customHandler::customValidationTwo);
  }
}
