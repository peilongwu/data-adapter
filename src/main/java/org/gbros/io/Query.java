package org.gbros.io;

import org.gbros.io.exception.IoException;
import org.gbros.io.file.excel.ExcelAdapter;
import org.gbros.io.rdb.RdbAdapter;

import java.util.List;
import java.util.Map;

public class Query {

  private Adapter adapter;
  private QuerySchema querySchema;

  /**
   * 初始化Query.
   * @param name name is statement's name or collection's name
   */
  public Query(String name) {
    querySchema = IoConfig.getQuerySchema(name);
    Source source = IoConfig.getSource(querySchema.getSource());
    this.init(source);
  }
  
  /**
   * 初始化Query.
   * @param querySchema
   */
  public Query(QuerySchema querySchema) {
    this.querySchema = querySchema;
    Source source = IoConfig.getSource(querySchema.getSource());
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

  public Map<String, Object> getObject(String collectionName,
      List<Criteria> criterias) throws IoException {
    return adapter.findObject(collectionName, criterias);
  }

  /**
   * get collection by collection name.
   * 
   * @param criterias
   * @param cols
   * @return
   */
  public List<Map<String, Object>> getCollection(List<Criteria> criterias,
      List<String> cols) throws IoException {
    return adapter.findCollection(querySchema.getContent(), criterias, cols);
  }

  public List<Map<String, Object>> getCollection(List<Param> params) throws IoException {
    return adapter.findByStatement(querySchema.getContent(), params);
  }

  public Map<String, Object> getObjectById(String collectionName,
      String objectId, String idName) {
    return null;
  }

}
