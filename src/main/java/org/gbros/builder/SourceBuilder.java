package org.gbros.builder;

import com.alibaba.druid.pool.DruidDataSource;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;

import org.gbros.io.Configuration;
import org.gbros.io.Source;
import org.gbros.utils.StringKit;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

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
          case "mongodb": {
            MongoOptions options = new MongoOptions();
            options.connectionsPerHost = Integer.parseInt(props.getProperty(keyPrefix + ".port"));
            options.threadsAllowedToBlockForConnectionMultiplier =  
                Integer.parseInt(props.getProperty(keyPrefix + ".port"));
            options.connectTimeout = Integer.parseInt(props.getProperty(keyPrefix + ".port"));
            options.maxWaitTime = Integer.parseInt(props.getProperty(keyPrefix + ".max_wait_time"));
            options.autoConnectRetry = 
                Boolean.parseBoolean(props.getProperty(keyPrefix + ".auto_connect"));
            options.socketKeepAlive = 
                Boolean.parseBoolean(props.getProperty(keyPrefix + ".socket_keep_alive"));
            options.socketTimeout = 
                Integer.parseInt(props.getProperty(keyPrefix + ".socket_timeout"));
            options.fsync = Boolean.parseBoolean(props.getProperty(keyPrefix + ".write-fsync"));
            String host = props.getProperty(keyPrefix + ".host");
            Mongo mongo = new Mongo(host, options);
            UserCredentials credentials = 
                new UserCredentials(props.getProperty(keyPrefix + ".username"),
                    props.getProperty(keyPrefix + ".password"));
            SimpleMongoDbFactory mongoDbFactory = 
                new SimpleMongoDbFactory(mongo, props.getProperty(keyPrefix 
                    + ".dbname"),credentials);
            MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
            Source source = new Source(keyPrefix, type, mongoTemplate);
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
