package org.gbros.exceptions;


/**
 * @author peilongwu.
 */
public class ExceptionFactory {

  public static RuntimeException wrapException(String message, Exception exception) {
    return new PersistenceException(ErrorContext.instance().message(message)
        .cause(exception).toString(), exception);
  }

}
