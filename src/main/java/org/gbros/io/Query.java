package org.gbros.io;

import org.gbros.io.exception.IoException;
import org.gbros.io.file.excel.ExcelAdapter;
import org.gbros.io.rdb.RdbAdapter;
import org.gbros.utils.StringKit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Query {

  private Adapter adapter;
  private QuerySchema querySchema;
  
  /**
   * 初始化Query.
   * @param querySchema
   */
  public Query(QuerySchema querySchema, Source source) {
    this.querySchema = querySchema;
    this.init(source);
  }

  private void init(Source source) {
    switch (source.getType()) {
      case "rdb": {
        adapter = new RdbAdapter(source);
        break;
      }
      case "excel": {
        adapter = new ExcelAdapter(source);
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
  
  /**
   * Query method choose.
   * @param paramMap
   * @return
   */
  public List<Map<String,Object>> getCollection(Map<String, List<String>> paramMap) 
      throws IoException {
    List<Map<String,Object>> dataList = null;
    switch (querySchema.getType()) {
      case "statement" : {
        dataList = adapter.findByStatement(querySchema.getContent(),this.getParams(paramMap));
        break;
      }
      case "collection" : {
        dataList = adapter.findCollection(querySchema.getContent(), this.getCriterias(paramMap),
            this.getCols(paramMap));
        break;
      }
      default: {
        break;
      }
    }
    return dataList;
  }

  public Map<String, Object> getObject(String collectionName,
      List<Criteria> criterias) throws IoException {
    return adapter.findObject(collectionName, criterias);
  }

  public Map<String, Object> getObjectById(String collectionName,
      String objectId, String idName) {
    return null;
  }
  
  private List<Param> getParams(Map<String, List<String>> paramMap) {
    List<Param> params = null;
    if (paramMap != null) {
      params = new ArrayList<Param>();
      for (Entry<String,List<String>> entry : paramMap.entrySet()) {
        if (StringKit.isNotBlank(entry.getKey())) {
          String key = entry.getKey();
          String value = null;
          for (String val : entry.getValue()) {
            value = val;
          }
          System.out.println("param key is : " + key);
          System.out.println("param value is : " + value);
          params.add(new Param(key, value));
        }
      }
    }
    return params;
  }
  
  private List<Criteria> getCriterias(Map<String, List<String>> paramMap) {
    List<Criteria> criterias = null;
    return criterias;
  }
  
  private List<String> getCols(Map<String, List<String>> paramMap) {
    return paramMap.get("_cols");
  }

}
