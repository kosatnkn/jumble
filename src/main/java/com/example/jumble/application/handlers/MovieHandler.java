package com.example.jumble.application.handlers;

import com.example.jumble.domain.entities.Movie;
import com.example.jumble.domain.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
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

    Flux<Movie> movies = this.movieService.getAllMovies();

    return ServerResponse.ok()
        .body(movies, Movie.class);
  }

  /**
   * Handle get movie by id
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> byId(ServerRequest request) {

    String id = request.pathVariable("id");
    Mono<Movie> movie = this.movieService.getMovieById(id);

    return ServerResponse.ok()
        .body(movie, Movie.class);
  }
}
