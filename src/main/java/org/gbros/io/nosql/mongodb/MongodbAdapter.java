package org.gbros.io.nosql.mongodb;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import org.gbros.io.AbstractAdapter;
import org.gbros.io.Criteria;
import org.gbros.io.IoException;
import org.gbros.io.Param;
import org.gbros.io.Source;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MongodbAdapter  extends AbstractAdapter{
  
  private MongoTemplate mongoTemplate;

  public MongodbAdapter(Source source) {
    super(source);
    this.mongoTemplate = (MongoTemplate) source.getSource();
  }

  @Override
  public Map<String, Object> findObject(String collectionName,
      List<Criteria> criterias) throws IoException {
    return null;
  }

  @Override
  public List<Map<String, Object>> findCollection(String collectionName,
      List<Criteria> criterias, List<String> cols) throws IoException {
    return null;
  }

  
  @Override
  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> findByStatement(String statement,
      List<Param> params) throws IoException {
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    DBCollection dbCollection = mongoTemplate.getCollection(statement);
    DBCursor dbCorsor = dbCollection.find();
    List<DBObject> dbObjectList = dbCorsor.toArray();
    if (dbObjectList != null) {
      for (DBObject obj : dbObjectList) {
        list.add(obj.toMap());
      }
    }
    return list;
  }

}
