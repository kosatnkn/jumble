package com.example.jumble.application.handlers;

import com.example.jumble.application.transport.request.entities.MovieRequestEntity;
import com.example.jumble.application.validator.RequestEntityValidator;
import com.example.jumble.domain.entities.Movie;
import com.example.jumble.domain.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MovieHandler {

  @Autowired
  private RequestEntityValidator validator;

  @Autowired
  private MovieService movieService;

  /**
   * Handle get all movies request
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> get(ServerRequest request) {

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
  public Mono<ServerResponse> getById(ServerRequest request) {

    String id = request.pathVariable("id");
    Mono<Movie> movie = this.movieService.getMovieById(id);

    return ServerResponse.ok()
        .body(movie, Movie.class);
  }

  /**
   * Handle creating a new movie entry
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> add(ServerRequest request) {

    Mono<String> responseData = request.bodyToMono(MovieRequestEntity.class)
        .flatMap(payload -> {

          // validate
          try {
            this.validator.validate(payload);
          }
          catch (Exception ex) {
            return Mono.error(ex);
          }

          return Mono.just(String.format("{id: \"%s\"}", "1234567"));
        });

    return ServerResponse
        .status(HttpStatus.CREATED)
        .body(responseData, String.class);
  }
}
