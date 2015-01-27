package org.gbros.utils.excel;

public class MergedRegion {

  private int rowBegin;
  private int colBegin;
  private int rowEnd;
  private int colEnd;

  public MergedRegion() {
  }

  public MergedRegion(int rowBegin, int colBegin, int rowEnd, int colEnd) {

    this.rowBegin = rowBegin;
    this.colBegin = colBegin;
    this.rowEnd = rowEnd;
    this.colEnd = colEnd;
  }

  public int getRowBegin() {
    return rowBegin;
  }

  public void setRowBegin(int rowBegin) {
    this.rowBegin = rowBegin;
  }

  public int getColBegin() {
    return colBegin;
  }

  public void setColBegin(int colBegin) {
    this.colBegin = colBegin;
  }

  public int getRowEnd() {
    return rowEnd;
  }

  public void setRowEnd(int rowEnd) {
    this.rowEnd = rowEnd;
  }

  public int getColEnd() {
    return colEnd;
  }

  public void setColEnd(int colEnd) {
    this.colEnd = colEnd;
  }

}
