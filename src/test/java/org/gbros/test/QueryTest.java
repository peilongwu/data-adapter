package org.gbros.test;

import com.alibaba.druid.pool.DruidDataSource;

import org.gbros.io.IoConfig;
import org.gbros.io.Param;
import org.gbros.io.Query;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class QueryTest {
  
  private static Properties props = new Properties();
  
  private static void initDataSource() {
    try {
      File file = new File("D:\\datasource.properties");
      InputStream inStream = new FileInputStream(file);
      props = new Properties();
      props.load(inStream);
      DruidDataSource dataSource = new DruidDataSource();
      dataSource.setUrl(props.getProperty("test.url"));
      dataSource.setUsername(props.getProperty("test.username"));
      dataSource.setPassword(props.getProperty("test.password"));
      IoConfig.putDataSource("test", dataSource);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private static void initQueryStatement() {
    try {
      File file = new File("D:\\query.properties");
      InputStream inStream = new FileInputStream(file);
      props = new Properties();
      props.load(inStream);
      Set<Object> keys = props.keySet();
      for (Object key : keys) {
        IoConfig.putStatement(key.toString(), props.getProperty(key.toString()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * main method.
   */
  public static void main(String[] args) {
    try {
      initDataSource();
      initQueryStatement();
      Query query = new Query("rdb","test");
      String sql = "select * from gbe_user where org_key = '046098d326ad4904b0fb91bdb0127909'";
      List<Map<String,Object>> list = query.getByStatement(sql, null);
      System.out.println(list.size());
      List<Param> params = new ArrayList<Param>();
      params.add(new Param("orgKey","046098d326ad4904b0fb91bdb0127909"));
      List<Map<String,Object>> list2 = query.getByStatementName("testsql", params);
      System.out.println(list2.size());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
