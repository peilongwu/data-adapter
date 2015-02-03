package org.gbros.builder.xml;

import org.gbros.io.Configuration;

import java.io.InputStream;
import java.util.Properties;

public class XmlConfigBuilder {

  private Configuration configuration;
  
  public XmlConfigBuilder(InputStream inputStream, String environment, Properties props) {
    
  }
  
  public Configuration parse() {
    return configuration;
  }
}
