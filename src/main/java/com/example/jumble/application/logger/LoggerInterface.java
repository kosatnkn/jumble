package com.example.jumble.application.logger;

public interface LoggerInterface {

  void error(String message);

  void warn(String message);

  void info(String message);

  void debug(String message);

  void trace(String message);
}
