package com.example.jumble.externals.webclients;

import com.example.jumble.domain.entities.Movie;
import com.example.jumble.domain.boundary.webclients.IMDBClientInterface;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Component
public class IMDBClient implements IMDBClientInterface {

  @Autowired
  private MeterRegistry meterRegistry;

  @Override
  public Mono<Double> getRating(Movie movie) {

    // register metrics
    Timer timer = this.meterRegistry.timer("imdb.request.time", "title", movie.getId());
    long start = System.currentTimeMillis();

    // call REST API

    // set metrics
    timer.record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);

    return Mono.just(3.5);
  }
}
