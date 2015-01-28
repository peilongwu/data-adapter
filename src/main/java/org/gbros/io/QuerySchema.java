package org.gbros.io;

public class QuerySchema {

  private String type; // statement or collection
  private String content;
  private String source;

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

}
