package org.gbros.io;

public interface QueryFactory {

  /**
   * open a query.
   * @param name
   * @return
   */
  public Query openQuery(String name);
  
  public Configuration getConfiguration();

}