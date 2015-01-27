package org.gbros.io;

import java.util.HashMap;
import java.util.Map;

public class IoConfig{
  
  private static Map<String,Object> dataSourceMap = new HashMap<String,Object>();
  private static Map<String,String> statementMap = new HashMap<String,String>();
  
  public static void putDataSource(String name, Object dataSource) {
    dataSourceMap.put(name, dataSource);
  }
  
  public static Object getDataSource(String name) {
    return dataSourceMap.get(name);
  }
  
  public static void putStatement(String name, String statement) {
    statementMap.put(name, statement);
  }
  
  public static String getStatement(String name) {
    return statementMap.get(name);
  }
  
}