package org.gbros.config;

import org.gbros.io.Configuration;

public class Config {

  private String runmode = "dev";
  private String confPath = "";
  public static Configuration configuration; 

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
