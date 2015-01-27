package org.gbros.io;

import java.util.HashMap;
import java.util.Map;

public class IoConfig{
  
  private static Map<String,Object> dataSourceMap = new HashMap<String,Object>();
  private static Map<String,Statement> statementMap = new HashMap<String,Statement>();
  
  public static void putDataSource(String name, Object dataSource) {
    dataSourceMap.put(name, dataSource);
  }
  
  public static Object getDataSource(String name) {
    return dataSourceMap.get(name);
  }
  
  public static void putStatement(String name, Statement statement) {
    statementMap.put(name, statement);
  }
  
  public static Statement getStatement(String name) {
    return statementMap.get(name);
  }
  
}