package org.gbros;

import com.alibaba.druid.pool.DruidDataSource;

import org.gbros.io.IoConfig;
import org.gbros.io.Statement;
import org.gbros.utils.PathKit;
import org.gbros.utils.StringKit;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
      List<String> keyPrefixList = new ArrayList<String>();
      for (Object key : keys) {
        String tempKey = key.toString();
        String[] strs = tempKey.split("\\.");
        if (strs != null && strs.length > 0) {
          if (StringKit.isNotBlank(strs[0]) && !keyPrefixList.contains(strs[0])) {
            keyPrefixList.add(strs[0]);
          }
        }
      }
      for (String prefix : keyPrefixList) {
        String statement = props.getProperty(prefix + "." + "statement");
        String dataSourceName = props.getProperty(prefix + "." + "datasource");
        Statement stms = new Statement(statement, dataSourceName);
        IoConfig.putStatement(prefix, stms);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
