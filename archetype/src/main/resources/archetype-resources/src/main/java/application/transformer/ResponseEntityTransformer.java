#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.transformer;

import java.util.Map;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ResponseEntityTransformer {

  /**
   * Transform a Mono of entities using the given transformer
   *
   * @param entityMono Mono
   * @param transformer ResponseEntity
   * @return Mono
   */
  public Mono<Map> transform(Mono<?> entityMono, ResponseEntityInterface transformer) {

    return entityMono.flatMap(entity -> {

      Map mapping = transformer.transform(entity);

      return Mono.just(mapping);
    });
  }

  /**
   * Transform a Flux of entities using the given transformer
   *
   * @param entityFlux Flux
   * @param transformer Response Transformer
   * @return Flux
   */
  public Flux<Map> transform(Flux<?> entityFlux, ResponseEntityInterface transformer) {

    return entityFlux.flatMap( entity -> {

      Map mapping = transformer.transform(entity);

      return Flux.just(mapping);
    });
  }
}
