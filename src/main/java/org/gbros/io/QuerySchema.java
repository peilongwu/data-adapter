package org.gbros.io;

import java.util.Map;

public class QuerySchema {

  private String type; // statement or collection
  private String content;
  private String source;
  private Map<String,Object> defaultParam;

  /**
   * constructor.
   * @param type
   * @param name
   * @param source
   */
  public QuerySchema(String type, String content, String source) {
    this.type = type;
    this.content = content;
    this.source = source;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Map<String, Object> getDefaultParam() {
    return defaultParam;
  }

  public void setDefaultParam(Map<String, Object> defaultParam) {
    this.defaultParam = defaultParam;
  }
  
}
