package org.gbros.io;

import org.gbros.Initialize;
import org.gbros.builder.Resources;
import org.gbros.builder.xml.XmlConfigBuilder;
import org.gbros.exceptions.ErrorContext;
import org.gbros.exceptions.ExceptionFactory;

import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class QueryFactoryBuilder {
  
  /**
   * build.
   * @return
   */
  public QueryFactory build() {
    Initialize initialize = new Initialize();
    Configuration configuration = initialize.init();
    return build(configuration);
  }

  public QueryFactory build(Reader reader) {
    return build(reader, null, null);
  }

  public QueryFactory build(Reader reader, String environment) {
    return build(reader, environment, null);
  }

  public QueryFactory build(Reader reader, Properties properties) {
    return build(reader, null, properties);
  }

  /**
   * build QueryFactiory.
   */
  public QueryFactory build(Reader reader, String environment,
      Properties properties) {
    try {
      InputStream inStream = Resources.getResourceAsStream("config.xml");
      XmlConfigBuilder configBuilder = new XmlConfigBuilder(inStream);
      Configuration configuration = configBuilder.parse();
      return build(configuration);
    } catch (Exception e) {
      throw ExceptionFactory.wrapException("Error building SqlSession.", e);
    } finally {
      ErrorContext.instance().reset();
      try {
        reader.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public QueryFactory build(InputStream inputStream) {
    return build(inputStream, null, null);
  }

  public QueryFactory build(InputStream inputStream, String environment) {
    return build(inputStream, environment, null);
  }

  public QueryFactory build(InputStream inputStream, Properties properties) {
    return build(inputStream, null, properties);
  }

  /**
   * build QueryFactiory.
   */
  public QueryFactory build(InputStream inputStream, String environment,
      Properties properties) {
    try {
      Initialize initialize = new Initialize();
      Configuration configuration = initialize.init();
      return build(configuration);
    } catch (Exception e) {
      throw ExceptionFactory.wrapException("Error building SqlSession.", e);
    } finally {
      ErrorContext.instance().reset();
      try {
        inputStream.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public QueryFactory build(Configuration config) {
    return new DefaultQueryFactory(config);
  }

}
