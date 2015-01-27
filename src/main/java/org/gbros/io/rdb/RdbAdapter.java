package org.gbros.io.rdb;

import org.gbros.io.AbstractAdapter;
import org.gbros.io.Criteria;
import org.gbros.io.IoConfig;
import org.gbros.io.Param;
import org.gbros.io.exception.IoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

public class RdbAdapter extends AbstractAdapter{
  
  private DataSource dataSource;
  private Connection conn;

  /**
   * relation database adapter  instantiate.
   * @param dataSourceType
   * @param dataSourceName
   */
  public RdbAdapter(String dataSourceType, String dataSourceName) {
    super(dataSourceType, dataSourceName);
    Object tempDataSource = IoConfig.getDataSource(dataSourceName);
    System.out.println(tempDataSource.getClass().getName());
    try {
      dataSource = (DataSource)IoConfig.getDataSource(dataSourceName);
    } catch (Exception e) {
      throw new IoException("initialize data source error : can't convert object to data source");
    }
    try {
      conn = dataSource.getConnection();
    } catch (Exception e) {
      e.printStackTrace();
      throw new IoException("get connection from data source error"); 
    }
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
  public List<Map<String, Object>> findByStatement(String sqlString,
      List<Param> params) throws IoException {
    String sql = SqlUtils.getSql(sqlString, params);
    try {
      PreparedStatement statement = conn.prepareStatement(sql);
      ResultSet rs = statement.executeQuery();
      return getResults(rs);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IoException("excute sql error: " + e.getMessage());
    }
  }
  
  private List<Map<String, Object>> getResults(ResultSet rs) throws SQLException {
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    ResultSetMetaData rsmd = rs.getMetaData();
    List<String> columns = new ArrayList<String>();
    for (int i = 0, n = rsmd.getColumnCount(); i < n; i++) {
      columns.add(rsmd.getColumnLabel(i + 1));
    }
    while (rs.next()) {
      Map<String, Object> row = new HashMap<String, Object>();
      for (int i = 0, n = columns.size(); i < n; i++) {
        String name = columns.get(i);
        row.put(name.toUpperCase(Locale.ENGLISH), rs.getObject(name));
      }
      list.add(row);
    }
    return list;
  }

  @Override
  public List<Map<String, Object>> findByStatementName(String statementName,
      List<Param> params) throws IoException {
    String sqlString = IoConfig.getStatement(statementName);
    return this.findByStatement(sqlString, params);
  }

}
