package org.gbros.io.exception;

public class IoException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public IoException(String error) {
     super(error);
  }
  
  public IoException() {
    super();
  }
  
}
