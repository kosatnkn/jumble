package com.example.jumble.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomException extends ResponseStatusException {

  public CustomException(HttpStatus status, String message) {
    super(status, message);
  }

  public CustomException(HttpStatus status, String message, Throwable e) {
    super(status, message, e);
  }
}
