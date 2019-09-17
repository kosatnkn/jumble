package com.example.jumble.domain.boundary.webclients;

import com.example.jumble.domain.entities.Movie;
import reactor.core.publisher.Mono;

public interface IMDBClientInterface {

  public Mono<Double> getRating(Movie movie);
}
