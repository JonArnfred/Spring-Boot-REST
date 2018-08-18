package org.name.exceptions;

public class AppException extends Exception {

  public AppException(Throwable cause) {
    super(cause);
  }

  public AppException(String message, Throwable cause) {
    super(message, cause);
  }
}
