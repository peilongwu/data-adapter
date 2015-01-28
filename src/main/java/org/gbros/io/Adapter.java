package org.gbros.io;

import org.gbros.io.exception.IoException;

import java.util.List;
import java.util.Map;

public interface Adapter {
  
  /**
   * find collection data from collection.
   * @param collectionName
   * @param criterias
   * @return
   * @throws IOException
   */
  public Map<String,Object> findObject(String collectionName, List<Criteria> criterias) 
      throws IoException;
  
  /**
   * find collection data from collection.
   * @param collectionName not null
   * @param criteria query criteriacan be null
   * @param cols 
   * @throws IOException
   */
  public List<Map<String,Object>> findCollection(String collectionName, List<Criteria> criterias, 
      List<String> cols) throws IoException;
  
  /**
   * find collection by statement.
   * @param statement
   * @param params
   * @throws IOException
   */
  public List<Map<String,Object>> findByStatement(String statement, List<Param> params) 
      throws IoException;
 
}
