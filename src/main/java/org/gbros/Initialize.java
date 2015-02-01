package org.gbros;

import com.alibaba.druid.pool.DruidDataSource;

import org.gbros.io.Configuration;
import org.gbros.io.QuerySchema;
import org.gbros.io.Source;
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
  
  private final Configuration configuration = new Configuration();
  
  /**
   * init.
   * @return
   */
  public Configuration init() {
    this.initQuerySchema();
    this.initSource();
    return configuration;
  }

  /**
   * init data source.
   */
  public void initSource() {
    try {
      System.out.println(baseDir);
      File file = new File(baseDir + "\\src\\main\\resource\\datasource.properties");
      InputStream inStream = new FileInputStream(file);
      props = new Properties();
      props.load(inStream);
      Set<Object> keys = props.keySet();
      List<String> keyPrefixList = getPrefixList(keys);
      for (String keyPrefix : keyPrefixList) {
        String type = props.getProperty(keyPrefix + ".type");
        switch (type) {
          case "rdb" : {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(props.getProperty(keyPrefix + ".url"));
            dataSource.setUsername(props.getProperty(keyPrefix + ".username"));
            dataSource.setPassword(props.getProperty(keyPrefix + ".password"));
            Source source = new Source(keyPrefix, type, dataSource);
            configuration.putSource(keyPrefix, source);
            break;
          }
          case "excel" : {
            String excelName = props.getProperty(keyPrefix + ".url");
            Source source = new Source(keyPrefix, type, excelName);
            configuration.putSource(keyPrefix, source);
            break;
          }
          default: {
            break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * init query properties.
   */
  public void initQuerySchema() {
    try {
      File file = new File(baseDir + "\\src\\main\\resource\\query.properties");
      InputStream inStream = new FileInputStream(file);
      props = new Properties();
      props.load(inStream);
      Set<Object> keys = props.keySet();
      List<String> keyPrefixList = getPrefixList(keys);
      for (String prefix : keyPrefixList) {
        String type = props.getProperty(prefix + "." + "type");
        String content = props.getProperty(prefix + "." + "content");
        String source = props.getProperty(prefix + "." + "source");
        QuerySchema stms = new QuerySchema(type, content, source);
        configuration.putQuerySchema(prefix, stms);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private static List<String> getPrefixList(Set<Object> keys) {
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
    return keyPrefixList;
  }
  
}
