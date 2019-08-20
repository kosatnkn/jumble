package com.example.jumble.application.handlers;

import com.example.jumble.domain.entities.Movie;
import com.example.jumble.domain.entities.MovieEvent;
import com.example.jumble.domain.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MovieHandler {

  @Autowired
  private MovieService movieService;

  /**
   * Handle get all movies request
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> all(ServerRequest request) {

    return ServerResponse.ok()
        .body(this.movieService.getAllMovies(), Movie.class);
  }

  /**
   * Handle get movie by id
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> byId(ServerRequest request) {

    return ServerResponse.ok()
        .body(this.movieService.getMovieById(request.pathVariable("id")), Movie.class);
  }

  /**
   * Handle get events of movies by movie
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> events(ServerRequest request) {

    return ServerResponse.ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(this.movieService.getEvents(request.pathVariable("id")), MovieEvent.class);
  }
}
