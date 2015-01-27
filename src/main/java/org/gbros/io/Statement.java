package org.gbros.io;

public class Statement {

  private String statement;
  private String dataSourceName;
  
  public Statement(String statement, String dataSourceName) {
    this.statement = statement;
    this.dataSourceName = dataSourceName;
  }

  public String getStatement() {
    return statement;
  }

  public void setStatement(String statement) {
    this.statement = statement;
  }

  public String getDataSourceName() {
    return dataSourceName;
  }

  public void setDataSourceName(String dataSourceName) {
    this.dataSourceName = dataSourceName;
  }

}
