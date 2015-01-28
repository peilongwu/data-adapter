package org.gbros.io.rdb;

import org.gbros.io.Criteria;
import org.gbros.io.Param;

import java.util.List;

public class SqlUtils {

  private static final String variableMark = "$";
  private static final String variableStart = "{";
  private static final String variableEnd = "}";

  /**
   * process param in sql string.
   * 
   * @param sqlString
   * @param params
   */
  public static String getSql(String sqlString, List<Param> params) {
    String returnSqlString = sqlString;
    if (params != null && params.size() > 0) {
      String tmpParamName = null;
      for (Param param : params) {
        tmpParamName = variableMark;
        tmpParamName = tmpParamName + variableStart + param.getName()
            + variableEnd;
        returnSqlString = returnSqlString.replace(tmpParamName,
            param.getValue());
      }
    }
    return returnSqlString;
  }

  /**
   * generate sql.
   * 
   * @param collectionName
   * @param criterias
   * @param cols
   */
  public static String getSql(String collectionName, List<Criteria> criterias,
      List<String> cols) {
    StringBuffer sqlBuffer = new StringBuffer("select ");
    if (cols != null && cols.size() > 0) {
      for (String col : cols) {
        sqlBuffer.append(col);
        sqlBuffer.append(",");
      }
      sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
    } else {
      sqlBuffer.append(" * ");
    }
    sqlBuffer.append(" from " + collectionName);
    return sqlBuffer.toString();
  }
}
