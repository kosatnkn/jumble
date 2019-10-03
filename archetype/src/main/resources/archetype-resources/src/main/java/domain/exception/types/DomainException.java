#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.exception.types;

public class DomainException extends Exception {

  public DomainException(String message) {
    super(message);
  }
}
