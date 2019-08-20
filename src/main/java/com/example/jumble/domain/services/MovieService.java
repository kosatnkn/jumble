package com.example.jumble.domain.services;

import com.example.jumble.domain.entities.Movie;
import com.example.jumble.domain.entities.MovieEvent;
import com.example.jumble.domain.boundary.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

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
   * Get a Flux of events associated with the Movie
   *
   * @param movieId Movie id
   * @return Flux<MovieEvent>
   */
  public Flux<MovieEvent> getEvents(String movieId) {

    return Flux.<MovieEvent>generate(sink -> sink.next(new MovieEvent(movieId, new Date())))
        .delayElements(Duration.ofSeconds(1));
  }
}
