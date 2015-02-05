package org.gbros.io;

import org.gbros.builder.xml.XmlConfigBuilder;
import org.gbros.utils.PathKit;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map.Entry;

public class XmlConfigBuilderTest {
  
  /**
   * Test XmlConfigBuilder.
   * @param args
   */
  public static void main(String[] args) {
    try {
      String filePath = PathKit.getRootClassPath();
      System.out.println(filePath);
      File xmlConfig = new File(filePath + "\\" + "config.xml");
      System.out.println(xmlConfig.exists());
      InputStream inStream = new FileInputStream(xmlConfig);
      XmlConfigBuilder builder = new XmlConfigBuilder(inStream);
      Configuration config = builder.parse();
      for (Entry<String, QuerySchema> query : config.getQuerySchema().entrySet()) {
        QuerySchema schema = query.getValue();
        System.out.println(schema.getName());
        System.out.println(schema.getSource());
        System.out.println(schema.getContent());
        System.out.println(schema.getType());
      }
      for (Entry<String, Source> sourceMap : config.getSource().entrySet()) {
        Source source = sourceMap.getValue();
        System.out.println(source.getName());
        System.out.println(source.getSource());
        System.out.println(source.getType());
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
}
