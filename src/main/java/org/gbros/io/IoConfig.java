package org.gbros.io;

import java.util.HashMap;
import java.util.Map;

public class IoConfig{
  
  public static final String SOURCE_TYPE_SUFFIX = ".type";
  public static final String QUERY_TYPE_SUFFIX = ".type";
  public static final String QUERY_SOURCE_SUFFIX = ".source";
  public static final String QUERY_CONTENT_SUFFIX = ".content";
  
  private static Map<String,Source> source = new HashMap<String,Source>();
  private static Map<String,QuerySchema> querySchema = new HashMap<String,QuerySchema>();
  
  public static void putSource(String name, Source src) {
    source.put(name, src);
  }
  
  public static Source getSource(String name) {
    return source.get(name);
  }
  
  public static void putQuerySchema(String name, QuerySchema statement) {
    querySchema.put(name, statement);
  }
  
  public static QuerySchema getQuerySchema(String name) {
    return querySchema.get(name);
  }
  
}