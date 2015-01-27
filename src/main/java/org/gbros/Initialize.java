package org.gbros;

import com.alibaba.druid.pool.DruidDataSource;

import org.gbros.io.IoConfig;
import org.gbros.utils.PathKit;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class Initialize {
  
  private static Properties props = new Properties();
  private static String baseDir = PathKit.getWebRootPath();

  /**
   * init data source.
   */
  public static void initDataSource() {
    try {
      System.out.println(baseDir);
      File file = new File(baseDir + "\\src\\main\\resource\\datasource.properties");
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
  
  /**
   * init query properties.
   */
  public static void initQueryStatement() {
    try {
      File file = new File(baseDir + "\\src\\main\\resource\\query.properties");
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
  
}
