#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.exception.types;

import ${package}.application.validator.RequestEntityInterface;
import java.util.Set;
import javax.validation.ConstraintViolation;

public class ValidationException extends RuntimeException {

  private Set<ConstraintViolation<RequestEntityInterface>> errors;

  public ValidationException(Set<ConstraintViolation<RequestEntityInterface>> errors) {
    this.errors = errors;
  }

  public Set<ConstraintViolation<RequestEntityInterface>> getErrors() {
    return this.errors;
  }
}
