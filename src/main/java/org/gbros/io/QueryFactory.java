package org.gbros.io;

public interface QueryFactory {

  /**
   * open a query.
   * @param name
   * @return
   */
  public abstract Query openQuery(String name);

}