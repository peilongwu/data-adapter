package org.gbros.io;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface IReader {
	
	public JSONObject get(String name)throws Exception;
	
	public JSONObject getList(String name) throws Exception;
	
	public JSONObject getList(String location, String collection, Map<String,Object> filter) throws Exception;
}
