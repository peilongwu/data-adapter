package org.gbros.builder;

import com.alibaba.druid.pool.DruidDataSource;

import org.gbros.io.Configuration;
import org.gbros.io.Source;
import org.gbros.utils.StringKit;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class SourceBuilder {

  private Properties props;
  private Configuration configuration;

  /**
   * SourceBuilder conductor.
   * 
   * @param inStream
   * @param configuration
   */
  public SourceBuilder(InputStream inStream, Configuration configuration) {
    try {
      this.props = new Properties();
      props.load(inStream);
      this.configuration = configuration;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * parse Source.
   * @param props
   */
  public void parse() {
    try {
      Set<Object> keys = props.keySet();
      List<String> keyPrefixList = getPrefixList(keys);
      for (String keyPrefix : keyPrefixList) {
        String type = props.getProperty(keyPrefix + ".type");
        switch (type) {
          case "rdb": {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(props.getProperty(keyPrefix + ".url"));
            dataSource.setUsername(props.getProperty(keyPrefix + ".username"));
            dataSource.setPassword(props.getProperty(keyPrefix + ".password"));
            Source source = new Source(keyPrefix, type, dataSource);
            configuration.putSource(keyPrefix, source);
            break;
          }
          case "excel": {
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
