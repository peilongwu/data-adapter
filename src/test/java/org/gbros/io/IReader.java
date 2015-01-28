package org.gbros.io;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface IReader {

  public JSONObject get(String name) throws Exception;

  public JSONObject getList(String name) throws Exception;

  public JSONObject getList(String location, String collection,
      Map<String, Object> filter) throws Exception;
}
