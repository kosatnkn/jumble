package com.example.jumble.domain.services;

import com.example.jumble.domain.boundary.repositories.MovieRepositoryInterface;
import com.example.jumble.domain.boundary.webclients.IMDBClientInterface;
import com.example.jumble.domain.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieService {

  @Autowired
  private MovieRepositoryInterface movieRepository;

  @Autowired
  private IMDBClientInterface imdbClient;

  /**
   * Get a Flux of Movies
   *
   * @return Flux<Movie>
   */
  public Flux<Movie> getAllMovies() {

    return this.movieRepository.findAll();
  }

  /**
   * Get a single Movie by id
   *
   * @param id Movie id
   * @return Mono<Movie>
   */
  public Mono<Movie> getMovieById(String id) {

    return this.movieRepository.findById(id);
  }

  /**
   * Add a movie
   *
   * @param movie movie
   * @return Mono<Movie>
   */
  public Mono<String> addMovie(Movie movie) {

    Mono<Movie> newMovie = this.movieRepository.save(movie);

    return newMovie.flatMap(m -> Mono.just(m.getId()));
  }

  /**
   * Get rating of the movie
   *
   * @param movie movie
   * @return Mono<Double>
   */
  public Mono<Double> getRating(Movie movie) {

    return this.imdbClient.getRating(movie);
  }
}
