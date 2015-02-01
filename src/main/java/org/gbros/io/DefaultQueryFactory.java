package org.gbros.io;

public class DefaultQueryFactory implements QueryFactory {
  
  private final Configuration configuration;
  
  private Query query;
  private QuerySchema querySchema;
  private Source source;
  
  public DefaultQueryFactory(Configuration configuration) {
    this.configuration = configuration;
  }
  
  /* (non-Javadoc)
   * @see org.gbros.io.QueryFactory#openQuery(java.lang.String)
   */
  @Override
  public Query openQuery(String name) {
    this.querySchema = configuration.getQuerySchema(name);
    this.source = configuration.getSource(name);
    this.query = new Query(querySchema, source);
    return query;
  }
  
 
}
