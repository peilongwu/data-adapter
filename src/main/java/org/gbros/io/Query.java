package org.gbros.io;

import org.gbros.io.exception.IoException;

import java.util.List;
import java.util.Map;

public interface Query {

  /**
   * Query method choose.
   * @param paramMap
   * @return
   */
  public List<Map<String, Object>> getCollection(
      Map<String, List<String>> paramMap) throws IoException;

  public Map<String, Object> getObject(String collectionName,
      List<Criteria> criterias) throws IoException;

  public Map<String, Object> getObjectById(String collectionName,
      String objectId, String idName);
  
  public Configuration getConfiguration();

}