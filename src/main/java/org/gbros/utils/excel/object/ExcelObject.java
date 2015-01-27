package org.gbros.utils.excel.object;

import java.util.List;

/**
 * 简单Excel对象，只包括名称和sheet数据.
 * @author peilongwu
 */
public class ExcelObject {

  private String name;
  private List<SheetObject> rowset;

  public ExcelObject() {
  }

  public ExcelObject(String name, List<SheetObject> rowset) {
    this.name = name;
    this.rowset = rowset;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<SheetObject> getRowset() {
    return rowset;
  }

  public void setRowset(List<SheetObject> rowset) {
    this.rowset = rowset;
  }
}
