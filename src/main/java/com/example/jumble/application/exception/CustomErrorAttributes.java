package com.example.jumble.application.exception;

import com.example.jumble.application.exception.types.ValidationException;
import com.example.jumble.application.validator.RequestEntityInterface;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {

    // get the error from request
    Throwable error = getError(request);

    switch (error.getClass().getSimpleName()) {
      case "ValidationException":
        return handleValidationException((ValidationException)error);
      case "FilterException":
      case "DomainException":
        return handleRecoverableException(error, includeStackTrace);
      default:
        return handleGenericException(error, includeStackTrace);
    }
  }

  /**
   * Handle recoverable exceptions
   *
   * @param error exception
   * @return error description
   */
  private Map<String, Object> handleRecoverableException(Throwable error,
      boolean includeStackTrace) {

    Map<String, Object> errorDetails = new LinkedHashMap<String, Object>();

    errorDetails.put("code", "");
    errorDetails.put("type", error.getClass().getSimpleName());
    errorDetails.put("message", error.getMessage());

    if (includeStackTrace) {
      errorDetails.put("trace", this.getStackTrace(error));
    }

    return errorDetails;
  }

  /**
   * Handle validation exceptions
   *
   * @param error exception
   * @return error description
   */
  private Map<String, Object> handleValidationException(ValidationException error) {

    Map<String, Object> errorDetails = new LinkedHashMap<String, Object>();

    errorDetails.put("type", error.getClass().getSimpleName());
    errorDetails.put("errors", this.formatValidationErrors(error.getErrors()));

    return errorDetails;
  }


  /**
   * Handle unrecoverable and more generic exceptions
   *
   * @param error exception
   * @return error description
   */
  private Map<String, Object> handleGenericException(Throwable error, boolean includeStackTrace) {

    Map<String, Object> errorDetails = new LinkedHashMap<String, Object>();

    errorDetails.put("code", "");
    errorDetails.put("type", error.getClass().getSimpleName());
    errorDetails.put("message", error.getMessage());

    if (includeStackTrace) {
      errorDetails.put("trace", this.getStackTrace(error));
    }

    return errorDetails;
  }

  /**
   * Get stack trace from an exception
   *
   * @param error exception
   * @return stack trace
   */
  private String getStackTrace(Throwable error) {

    StringWriter stackTrace = new StringWriter();
    error.printStackTrace(new PrintWriter(stackTrace));
    stackTrace.flush();

    return stackTrace.toString();
  }

  /**
   * Get validation errors from validator output
   *
   * @param errors Set of validation errors
   * @return error map
   */
  private Map<String, ArrayList<String>> formatValidationErrors(Set<ConstraintViolation<RequestEntityInterface>> errors) {

    Map<String, ArrayList<String>> errDetails = new LinkedHashMap<String, ArrayList<String>>();

    errors.forEach(error -> {

      String key = error.getPropertyPath().toString();
      String val = error.getMessage();

      // when a validation error already exists for the field
      if(errDetails.containsKey(key)) {

        ArrayList<String> arrVal = errDetails.get(key);
        arrVal.add(val);

        errDetails.put(key, arrVal);

        return;
      }

      // when there are no validation errors for the field
      ArrayList<String> arr = new ArrayList<String>();
      arr.add(val);

      errDetails.put(key, arr);
    });

    return errDetails;
  }
}