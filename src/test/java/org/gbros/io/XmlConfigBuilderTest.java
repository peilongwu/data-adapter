package org.gbros.io;

import org.gbros.builder.xml.XmlConfigBuilder;
import org.gbros.utils.PathKit;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
      builder.parse();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
}
