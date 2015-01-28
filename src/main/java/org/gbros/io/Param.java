package org.gbros.io;

import org.gbros.utils.StringKit;

public class Param {

  private String name;
  private String value;
  private String defaultValue = "";
  
  public Param(){
  }
  
  /**
   * param constructor.
   * @param name
   * @param value
   */
  public Param(String name, String value) {
    this.name = name;
    if (StringKit.isBlank(value)) {
      this.value = defaultValue;
    }
  }
  
  /**
   * param constructor.
   * @param name
   * @param value
   * @param defaultValue
   */
  public Param(String name, String value, String defaultValue) {
    this.name = name;
    this.defaultValue = defaultValue;
    if (StringKit.isBlank(value)) {
      this.value = defaultValue;
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }
  
}
