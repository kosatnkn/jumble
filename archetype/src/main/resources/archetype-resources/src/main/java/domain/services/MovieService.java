#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.services;

import ${package}.domain.boundary.repositories.MovieRepositoryInterface;
import ${package}.domain.boundary.webclients.IMDBClientInterface;
import ${package}.domain.entities.Movie;
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

  /**
   * Edit Movie
   *
   * @param movie Movie
   * @return Mono<Void>
   */
  public Mono<Void> edit(Movie movie) {

    Mono<Movie> editedBanner = this.movieRepository.save(movie);

    return editedBanner.then(Mono.empty());
  }

  /**
   * Delete Movie
   *
   * @param id Movie id
   * @return Mono<Void>
   */
  public Mono<Void> delete(String id) {

    return this.movieRepository.deleteById(id);
  }
}
