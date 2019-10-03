#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.boundary.webclients;

import ${package}.domain.entities.Movie;
import reactor.core.publisher.Mono;

public interface IMDBClientInterface {

  public Mono<Double> getRating(Movie movie);
}
