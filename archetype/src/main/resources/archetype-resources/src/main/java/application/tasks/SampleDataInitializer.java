#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.tasks;

import ${package}.domain.entities.Movie;
import ${package}.domain.boundary.repositories.MovieRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class SampleDataInitializer implements ApplicationRunner {

	@Autowired
	private MovieRepositoryInterface mr;

	private Logger logger = LoggerFactory.getLogger(SampleDataInitializer.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Flux<Movie> movieFlux = Flux.just("Film 1", "Film 2", " Film 3")
				.map(m -> new Movie(null, m))
				.flatMap(mr::save);

		mr.deleteAll()
				.thenMany(movieFlux)
				.thenMany(mr.findAll())
				.subscribe(message -> this.logger.info(message.toString()));
	}
}
