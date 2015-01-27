package org.gbros.test;

import org.gbros.Initialize;
import org.gbros.io.Param;
import org.gbros.io.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryTest {
  

  
  /**
   * main method.
   */
  public static void main(String[] args) {
    try {
      Initialize.initDataSource();
      Initialize.initQueryStatement();
      Query query = new Query("rdb","test");
      String sql = "select * from gbe_user where org_key = '046098d326ad4904b0fb91bdb0127909'";
      List<Map<String,Object>> list = query.getByStatement(sql, null);
      System.out.println(list.size());
      List<Param> params = new ArrayList<Param>();
      params.add(new Param("param","046098d326ad4904b0fb91bdb0127909"));
      List<Map<String,Object>> list2 = query.getByStatementName("user_query", params);
      System.out.println(list2.size());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
