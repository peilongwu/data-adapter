package org.gbros.io;

import com.alibaba.fastjson.JSONObject;

public interface IReader {
	
	public JSONObject get(String name)throws Exception;
	
	public JSONObject getObjectList(String name) throws Exception;
	
}
