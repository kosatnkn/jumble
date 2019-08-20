package com.example.jumble.application.tasks;

import com.example.jumble.domain.entities.Movie;
import com.example.jumble.domain.boundary.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class SampleDataInitializer implements ApplicationRunner {

	@Autowired
	private MovieRepository mr;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Flux<Movie> movieFlux = Flux.just("Film 1", "Film 2", " Film 3")
				.map(m -> new Movie(null, m))
				.flatMap(mr::save);

		mr.deleteAll()
				.thenMany(movieFlux)
				.thenMany(mr.findAll())
				.subscribe(System.out::println);
	}
}
