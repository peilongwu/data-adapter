package org.gbros.io;

import org.gbros.io.rdb.RdbAdapter;

import java.util.List;
import java.util.Map;

public class Query {
  
  private Adapter adapter;

  /**
   * 初始化Query.
   * @param dataSourceType
   */
  public Query(String dataSourceType, String dataSourceName) {
    
    switch (dataSourceType) {
      case "rdb": {
        adapter = new RdbAdapter(dataSourceType, dataSourceName);
        break;
      }
      case "file": {
        break;
      }
      case "nosql": {
        break;
      }
      default: {
        break;
      }
    }
  }
  
  public void init(){
    
  }
  
  public Map<String,Object> getObject(String collectionName, List<Criteria> criterias) {
    return adapter.findObject(collectionName, criterias);
  }
  
  public List<Map<String,Object>> getCollection(String collectionName, List<Criteria> criterias, 
      List<String> cols) {
    return adapter.findCollection(collectionName, criterias, cols);
  }
  
  public Map<String,Object> getObjectById(String collectionName, String objectId, String idName) {
    return null;
  }
  
  public List<Map<String,Object>> getByStatement(String statement, List<Param> params) {
    return adapter.findByStatement(statement, params);
  }
  
  public List<Map<String,Object>> getByStatementName(String statementName, List<Param> params) {
    return adapter.findByStatementName(statementName, params);
  }
  
}
