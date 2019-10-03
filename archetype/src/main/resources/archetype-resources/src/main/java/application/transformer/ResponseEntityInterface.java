#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.transformer;

import java.util.Map;

public interface ResponseEntityInterface {
  public Map transform(Object entity);
}
