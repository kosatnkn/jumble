package com.example.jumble.externals.webclients;

import com.example.jumble.domain.entities.Movie;
import com.example.jumble.domain.boundary.webclients.IMDBClientInterface;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class IMDBClient implements IMDBClientInterface {

  @Override
  public Mono<Double> getRating(Movie movie) {

    return Mono.just(3.5);
  }
}
