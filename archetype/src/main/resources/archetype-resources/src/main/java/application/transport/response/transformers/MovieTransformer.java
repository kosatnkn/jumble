#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.transport.response.transformers;

import ${package}.application.transformer.ResponseEntityInterface;
import ${package}.domain.entities.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class MovieTransformer implements ResponseEntityInterface {

  @Override
  public Map transform(Object datum) {

    ObjectMapper mapper = new ObjectMapper();
    Movie movie = (Movie)datum;

    Map<String, Object> mapping = new HashMap<>();

    mapping.put("transformed_id", movie.getId());
    mapping.put("transformed_title", movie.getTitle());

    return mapping;
  }
}
