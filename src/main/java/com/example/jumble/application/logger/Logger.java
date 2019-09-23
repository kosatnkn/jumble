package com.example.jumble.application.logger;

import org.slf4j.LoggerFactory;

public class Logger implements LoggerInterface {

  private org.slf4j.Logger logger;

  public Logger(Class<?> cls) {
    this.logger = LoggerFactory.getLogger(cls);
  }

  @Override
  public void error(String message) {
    this.logger.error(message);
  }

  @Override
  public void warn(String message) {
    this.logger.warn(message);
  }

  @Override
  public void info(String message) {
    this.logger.info(message);
  }

  @Override
  public void debug(String message) {
    this.logger.debug(message);
  }

  @Override
  public void trace(String message) {
    this.logger.trace(message);
  }
}
