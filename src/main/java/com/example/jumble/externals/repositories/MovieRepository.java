package com.example.jumble.externals.repositories;

import com.example.jumble.domain.entities.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

  /**
   * Get Movie by title
   *
   * @param title Movie title
   * @return Flux<Movie>
   */
  Flux<Movie> findByTitle(String title);
}
