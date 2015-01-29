package org.gbros.io;

import org.gbros.io.exception.IoException;
import org.gbros.utils.StringKit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class QueryFactory {
  
  private Query query;
  private QuerySchema querySchema;
  
  public QueryFactory(String name) {
    querySchema = IoConfig.getQuerySchema(name);
    query = new Query(querySchema);
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
        dataList = query.getCollection(this.getParams(paramMap));
        break;
      }
      case "collection" : {
        dataList = query.getCollection(this.getCriterias(paramMap), this.getCols(paramMap));
        break;
      }
      default: {
        break;
      }
    }
    return dataList;
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
