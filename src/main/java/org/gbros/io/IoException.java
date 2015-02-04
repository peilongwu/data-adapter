package org.gbros.io;

import org.gbros.exceptions.PersistenceException;

public class IoException extends PersistenceException {

  private static final long serialVersionUID = 1L;

  public IoException() {
    super();
  }
 
  public IoException(String error) {
     super(error);
  }
  
  public IoException(String message, Throwable cause) {
    super(message, cause);
  }

  public IoException(Throwable cause) {
    super(cause);
  }
  
}
