package org.gbros.io;

import java.util.HashMap;
import java.util.Map;

public class Configuration{
  
  public static final String SOURCE_TYPE_SUFFIX = ".type";
  public static final String QUERY_TYPE_SUFFIX = ".type";
  public static final String QUERY_SOURCE_SUFFIX = ".source";
  public static final String QUERY_CONTENT_SUFFIX = ".content";
  
  private Map<String,Source> source = new HashMap<String,Source>();
  private Map<String,QuerySchema> querySchema = new HashMap<String,QuerySchema>();
  
  public void putSource(String name, Source src) {
    source.put(name, src);
  }
  
  public Source getSource(String name) {
    return source.get(name);
  }
  
  public void putQuerySchema(String name, QuerySchema statement) {
    querySchema.put(name, statement);
  }
  
  public QuerySchema getQuerySchema(String name) {
    return querySchema.get(name);
  }
  
}