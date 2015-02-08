package org.gbros.builder.xml;

import org.gbros.io.Param;
import org.gbros.io.QuerySchema;
import org.gbros.parse.XNode;
import org.gbros.parse.XPathParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlQuerySchemaBuilder {
  
  private QuerySchema querySchema;
  private XPathParser parser;
  
  public XmlQuerySchemaBuilder(InputStream inStream) {
    parser = new XPathParser(inStream);
  }
  
  /**
   * parse QuerySchema.
   * @return
   */
  public QuerySchema parse() {
    querySchema = new QuerySchema();
    configurationElement(parser.evalNode("/query"));
    return querySchema;
  }

  private void configurationElement(XNode context) {
    XNode nameNode = context.evalNode("name");
    XNode typeNode = context.evalNode("type");
    XNode contentNode = context.evalNode("content");
    XNode sourceNode = context.evalNode("source");
    
    querySchema.setName(nameNode != null ? nameNode.getStringBody() : "");
    querySchema.setType(typeNode != null ? typeNode.getStringBody() : "");
    querySchema.setContent(contentNode != null ? contentNode.getStringBody() : "");
    querySchema.setSource(sourceNode != null ? sourceNode.getStringBody() : "");
    
    List<Param> params = new ArrayList<Param>();
    XNode paramsNode = context.evalNode("params");
    if (paramsNode != null) {
      for (XNode paramNode : paramsNode.getChildren()) {
        XNode name = paramNode.evalNode("name");
        XNode value = paramNode.evalNode("value");
        String paramValue = value.getStringBody();
        Param param = new Param(name.getStringBody(), paramValue != null ? paramValue : "");
        params.add(param);
      }
      querySchema.setParams(params);
    }
  }
  
}
