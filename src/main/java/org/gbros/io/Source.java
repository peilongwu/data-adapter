package org.gbros.io;

public class Source {

  private String name;
  private String type;
  private Object source;

  /**
   * constructor.
   * @param name
   * @param type
   * @param source
   */
  public Source(String name, String type, Object source) {
    this.type = type;
    this.source = source;
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Object getSource() {
    return source;
  }

  public void setSource(Object source) {
    this.source = source;
  }

}
