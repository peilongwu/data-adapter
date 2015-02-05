package org.gbros.builder.xml;

import org.gbros.builder.BuilderException;
import org.gbros.builder.Resources;
import org.gbros.builder.SourceBuilder;
import org.gbros.io.Configuration;
import org.gbros.io.QuerySchema;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
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
    parseSources(root,configuration);
    parseQuery(root,configuration);
    return configuration;
  }
  
  private void parseQuery(Node root, Configuration configuration) {
    try {
      Node querysNode = (Node) xpath.evaluate("querys", root, XPathConstants.NODE);
      for (int i = 0; i < querysNode.getChildNodes().getLength(); i++ ) {
        Node node = querysNode.getChildNodes().item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          NamedNodeMap map = node.getAttributes();
          String resource = map.getNamedItem("resource").getNodeValue();
          InputStream inputStream = Resources.getResourceAsStream(resource);
          XmlQuerySchemaBuilder queryScemaBuilder = new XmlQuerySchemaBuilder(inputStream);
          QuerySchema querySchema = queryScemaBuilder.parse();
          configuration.putQuerySchema(querySchema.getName(), querySchema);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void parseSources(Node root, Configuration configuration2) {
    try {
      Node querysNode = (Node) xpath.evaluate("sources", root, XPathConstants.NODE);
      for (int i = 0; i < querysNode.getChildNodes().getLength(); i++ ) {
        Node node = querysNode.getChildNodes().item(i);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          NamedNodeMap map = node.getAttributes();
          String resource = map.getNamedItem("resource").getNodeValue();
          InputStream inputStream = Resources.getResourceAsStream(resource);
          SourceBuilder builder = new SourceBuilder(inputStream, configuration);
          builder.parse();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
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
