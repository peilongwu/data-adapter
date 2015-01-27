package org.gbros.config;

public class Config {

  private String runmode = "dev";
  private String confPath = "";

  public String getRunmode() {
    return runmode;
  }

  public void setRunmode(String runmode) {
    this.runmode = runmode;
  }

  public String getConfPath() {
    return confPath;
  }

  public void setConfPath(String confPath) {
    this.confPath = confPath;
  }

}
