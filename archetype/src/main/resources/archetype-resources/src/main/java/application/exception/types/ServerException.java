#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.exception.types;

public class ServerException extends Exception {

  public ServerException(String message) {
    super(message);
  }
}
