package org.gbros.builder.xml;

import org.gbros.builder.BuilderException;
import org.gbros.io.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

public class XmlConfigBuilder {

  private Configuration configuration;
  private Document document;
  private XPath xpath;
  
  public XmlConfigBuilder(InputStream inputStream, String environment, Properties props)
      throws Exception {
    this(inputStream);
  }
  
  public XmlConfigBuilder(InputStream inputStream) throws Exception {
    this.document = createDocument(new InputSource(inputStream));
  } 
  
  /**
   * parse xml config to Configuration.
   * @return
   */
  public Configuration parse() {
    configuration = new Configuration();
    Node root = document.getFirstChild();
    NodeList nodeList = root.getChildNodes();
    parseSources(nodeList,configuration);
    parseQuery(nodeList,configuration);
    return configuration;
  }
  
  private void parseQuery(NodeList nodeList, Configuration configuration) {
    Node querysNode = null;
    try {
      for (int i = 0; i < nodeList.getLength(); i++) {
        if ("querys".equals(nodeList.item(i).getNodeName())) {
          querysNode = nodeList.item(i);
          break;
        }
      }
      if (querysNode != null) {
        NodeList queryList = querysNode.getChildNodes();
        for (int i = 0; i < queryList.getLength(); i++) {
         // NamedNodeMap query = queryList.item(i).getNodeValue();
          System.out.println(queryList.item(i).toString());
          System.out.println("111:::" + xpath.evaluate("resource", queryList.item(i)));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void parseSources(NodeList nodeList, Configuration configuration2) {
    
  }

  private Document createDocument(InputSource inputSource) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      XPathFactory pathFactory = XPathFactory.newInstance();
      this.xpath = pathFactory.newXPath();
      return builder.parse(inputSource);
    } catch (Exception e) {
      throw new BuilderException("Error creating document instance.  Cause: " + e, e);
    }
  }

}
