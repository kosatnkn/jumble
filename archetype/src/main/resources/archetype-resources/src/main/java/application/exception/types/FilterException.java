#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.exception.types;

public class FilterException extends Exception {

  public FilterException(String message) {
    super(message);
  }
}
