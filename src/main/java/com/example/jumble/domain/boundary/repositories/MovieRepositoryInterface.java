package com.example.jumble.domain.boundary.repositories;

import com.example.jumble.domain.entities.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovieRepositoryInterface extends ReactiveMongoRepository<Movie, String> {

  /**
   * Get Movie by title
   *
   * @param title Movie title
   * @return Flux<Movie>
   */
  Flux<Movie> findByTitle(String title);
}
