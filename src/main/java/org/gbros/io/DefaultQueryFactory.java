package org.gbros.io;

public class DefaultQueryFactory implements QueryFactory {
  
  private final Configuration configuration;
  
  public DefaultQueryFactory(Configuration configuration) {
    this.configuration = configuration;
  }
  
  /* (non-Javadoc)
   * @see org.gbros.io.QueryFactory#openQuery(java.lang.String)
   */
  @Override
  public Query openQuery(String name) {
    return new DefaultQuery(configuration, name);
  }

  @Override
  public Configuration getConfiguration() {
    return configuration;
  }
  
}
