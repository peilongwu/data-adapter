package org.gbros.io;

import java.util.List;

public class QuerySchema {

  private String name;
  private String type; // statement or collection
  private String content;
  private String source;
  private List<Param> params;
  
  public QuerySchema(){
    
  }

  /**
   * constructor.
   * @param type
   * @param name
   * @param source
   */
  public QuerySchema(String name, String type, String content, String source) {
    this.name = name;
    this.type = type;
    this.content = content;
    this.source = source;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public List<Param> getParams() {
    return params;
  }

  public void setParams(List<Param> params) {
    this.params = params;
  }
  
}
