#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.validator;

import ${package}.application.exception.types.ValidationException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestEntityValidator {

  @Autowired
  private Validator validator;

  public void validate(RequestEntityInterface target) throws ValidationException {

    Set<ConstraintViolation<RequestEntityInterface>> errors = this.validator.validate(target);

    if(!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }
}