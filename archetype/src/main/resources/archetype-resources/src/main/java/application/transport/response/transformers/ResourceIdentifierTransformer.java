#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.transport.response.transformers;

import ${package}.application.transformer.ResponseEntityInterface;
import java.util.HashMap;
import java.util.Map;

public class ResourceIdentifierTransformer implements ResponseEntityInterface {

  @Override
  public Map transform(Object entity) {

    Map<String, Object> mapping = new HashMap<>();

    mapping.put("id", entity);

    return mapping;

  }
}
