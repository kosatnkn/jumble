package com.example.jumble.application.handlers;

import com.example.jumble.application.transformer.ResponseEntityTransformer;
import com.example.jumble.application.transport.request.entities.MovieRequestEntity;
import com.example.jumble.application.transport.response.transformers.MovieTransformer;
import com.example.jumble.application.transport.response.transformers.ResourceIdentifierTransformer;
import com.example.jumble.application.validator.RequestEntityValidator;
import com.example.jumble.domain.entities.Movie;
import com.example.jumble.domain.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Slf4j // logger (generates a class property of name `log`)
public class MovieHandler {

  @Autowired
  private RequestEntityValidator validator;

  @Autowired
  private ResponseEntityTransformer transformer;

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

    Flux<Map> trMovies = this.transformer.transform(movies, new MovieTransformer());

    // logging
    log.info("Lombok logger");

    return ServerResponse.ok()
        .body(trMovies, Map.class);
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

    Mono<Map> trMovie = this.transformer.transform(movie, new MovieTransformer());

    return ServerResponse.ok()
        .body(trMovie, Map.class);
  }

  /**
   * Handle get rating of a movie.
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> getRating(ServerRequest request) {

    String id = request.pathVariable("id");

    // map
    Movie movie = new Movie();
    movie.setId(id);

    Mono<Double> rating = this.movieService.getRating(movie);

//    Mono<Map> trMovie = this.transformer.transform(movie, new MovieTransformer());

    return ServerResponse.ok()
            .body(rating, Double.class);
  }

  /**
   * Handle creating a new movie entry
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> add(ServerRequest request) {

    Mono<String> movieId = request.bodyToMono(MovieRequestEntity.class)
        .flatMap(payload -> {

          // validate
          try {
            this.validator.validate(payload);
          }
          catch (Exception ex) {
            return Mono.error(ex);
          }

          // map
          Movie movie = new Movie();
          movie.setTitle(payload.getTitle());

          return this.movieService.addMovie(movie);
        });

    Mono<Map> trMovieId = transformer.transform(movieId, new ResourceIdentifierTransformer());

    return ServerResponse
        .status(HttpStatus.CREATED)
        .body(trMovieId, Map.class);
  }

  /**
   * Handle updating of an existing movie
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> edit(ServerRequest request) {

    // get query parameters
    String id = request.pathVariable("id");

    Mono<Void> done = request.bodyToMono(MovieRequestEntity.class)
        .flatMap(payload -> {

          // validate
          try {
            this.validator.validate(payload);
          }
          catch (Exception ex) {
            return Mono.error(ex);
          }

          // map
          Movie movie = new Movie();
          movie.setId(id);
          movie.setTitle(payload.getTitle());

          return this.movieService.edit(movie);
        });

    return done.then(ServerResponse.noContent().build());
  }

  /**
   * Handle deletion of an existing movie
   *
   * @param request ServerRequest
   * @return ServerResponse
   */
  public Mono<ServerResponse> delete(ServerRequest request) {

    // get query parameters
    String id = request.pathVariable("id");

    Mono<Void> done = this.movieService.delete(id);

    return done.then(ServerResponse.noContent().build());
  }
}
