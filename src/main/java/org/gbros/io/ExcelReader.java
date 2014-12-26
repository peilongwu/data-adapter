package org.gbros.io;

import org.gbros.utils.excel.object.ExcelObjectUtils;
import org.gbros.utils.excel.simple.ExcelSimpleUtils;

import com.alibaba.fastjson.JSONObject;


public class ExcelReader implements IReader{
	
	public JSONObject get(String excelName) throws Exception{
        return ExcelSimpleUtils.toJSONObject(excelName);
	}
	
	public JSONObject getObjectList(String excelName) throws Exception{
		return ExcelObjectUtils.toJSONObject(excelName);
	}

}
