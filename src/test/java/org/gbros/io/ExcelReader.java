package org.gbros.io;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.gbros.utils.StringKit;
import org.gbros.utils.excel.object.ExcelObjectUtils;
import org.gbros.utils.excel.simple.ExcelSimpleUtils;

import java.util.Map;

public class ExcelReader implements IReader {

  private static String BASE_DIR = "D:/work_test/";
  private static String BASE_JSONOBJECT = "{Rowsets:{Rowset:{}}}";

  @Override
  public JSONObject get(String excelName) throws Exception {
    return ExcelSimpleUtils.toJSONObject(BASE_DIR + excelName);
  }

  @Override
  public JSONObject getList(String excelName) throws Exception {
    return ExcelObjectUtils.toJSONObject(BASE_DIR + excelName);
  }

  @Override
  public JSONObject getList(String excelName, String sheetName,
      Map<String, Object> filter) throws Exception {
    JSONArray array = ExcelObjectUtils.toJSONArray(BASE_DIR + excelName,
        sheetName);
    JSONArray returnArray = new JSONArray();
    for (int i = 0; i < array.size(); i++) {
      JSONObject object = array.getJSONObject(i);
      boolean flag = true;
      String value = null;
      String compareValue = null;
      for (Map.Entry<String, Object> entry : filter.entrySet()) {
        value = StringKit.toString(object.get(entry.getKey()));
        compareValue = StringKit.toString(entry.getValue());
        if (!value.equals(compareValue)) {
          flag = false;
          break;
        }
      }
      if (flag) {
        returnArray.add(object);
      }

    }
    JSONObject rowset = JSON.parseObject(BASE_JSONOBJECT);
    rowset.getJSONObject("Rowsets").getJSONObject("Rowset")
        .put("Row", returnArray);
    return rowset;
  }

}
