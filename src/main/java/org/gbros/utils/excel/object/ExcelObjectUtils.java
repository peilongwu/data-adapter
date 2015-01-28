package org.gbros.utils.excel.object;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gbros.utils.StringKit;
import org.gbros.utils.excel.MergedRegion;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理Excel，只处理excel中cell数据
 * 
 * @author peilw
 * @version 1.0
 */
public class ExcelObjectUtils {

  /**
   * to json object.
   * @function 读取Excel文件，生成JSONObject
   * @param fileName
   */
  public static JSONObject toJSONObject(String fileName) throws Exception {
    File file = new File(fileName);
    if (!file.exists()) {
      throw new RuntimeException();
    } else {
      InputStream inputStream = new FileInputStream(file);
      return toJSONObject(inputStream, fileName);
    }
  }

  /**
   * to excel object.
   * @function 读取文件，生成ExcelObject数据
   * @param fileName
   */
  public static ExcelObject toExcelObject(String fileName) throws Exception {
    File file = new File(fileName);
    if (!file.exists()) {
      throw new RuntimeException();
    } else {
      InputStream inputStream = new FileInputStream(file);
      return toExcelObject(inputStream, fileName);
    }
  }
  
  /**
   * get sheet data.
   * @param fileName
   * @param sheetName
   * @return
   * @throws Exception
   */
  public static List<Map<String,Object>> getSheetData(String fileName, String sheetName) 
      throws Exception {
    ExcelObject excel = toExcelObject(fileName);
    List<SheetObject> sheets = excel.getRowset();
    for (SheetObject sheet : sheets) {
      if (sheetName.equals(sheet.getName())) {
        return sheet.getRow();
      }
    }
    return null;
  }

  /**
   * to excel object.
   * @function 处理excel文件流，生成ExcelObject对象
   * @param inputStream 文件流
   * @param fileName 文件名称
   */
  public static ExcelObject toExcelObject(InputStream inputStream,
      String fileName) throws Exception {
    Workbook wbs = null;
    if (fileName.contains("xlsx")) {
      wbs = new XSSFWorkbook(inputStream);
    } else if (fileName.contains("xls")) {
      wbs = new HSSFWorkbook(inputStream);
    } else {
      throw new RuntimeException("Excel file type wrong! ");
    }
    return toExcelObject(wbs, fileName);
  }

  public static JSONArray toJSONArray(String excelName, String sheetName)
      throws Exception {
    ExcelObject excel = toExcelObject(excelName);
    for (SheetObject sheet : excel.getRowset()) {
      if (sheetName.equals(sheet.getName())) {
        return (JSONArray) JSON.toJSON(sheet.getRow());
      }
    }
    return new JSONArray();
  }

  /**
   * to json object.
   * @function 处理excel文件流，按sheet生成json格式数组数据信息
   * @param inputStream 文件流
   * @param fileName 文件名称
   * @return
   */
  public static JSONObject toJSONObject(InputStream inputStream, String fileName)
      throws Exception {
    Workbook wbs = null;
    if (fileName.contains("xlsx")) {
      wbs = new XSSFWorkbook(inputStream);
    } else if (fileName.contains("xls")) {
      wbs = new HSSFWorkbook(inputStream);
    } else {
      throw new RuntimeException("Excel file type wrong! ");
    }
    return toJSONObject(wbs, fileName);
  }

  public static String toJSONP(String callBackName, InputStream inputStream,
      String fileName) throws Exception {
    JSONObject json = toJSONObject(inputStream, fileName);
    String jsonp = callBackName + ":(" + json.toString() + ")";
    return jsonp;
  }

  /**
   * to json object.
   * @function 处理Workbook，按sheet生成json格式数组数据信息
   * @param wbs Excel WorkBook
   * @param fileName 文件名称
   * @return
   */
  private static JSONObject toJSONObject(Workbook wbs, String fileName)
      throws Exception {
    return (JSONObject) JSON.toJSON(toExcelObject(wbs, fileName));
  }

  /**
   * to excel object.
   * @function 处理Workbook，生成ExcelSimple对象
   * @param wbs
   * @param fileName
   * @return
   */
  private static ExcelObject toExcelObject(Workbook wbs, String fileName)
      throws Exception {
    ExcelObject excel = new ExcelObject();
    excel.setName(fileName);
    int sheetNums = wbs.getNumberOfSheets();
    List<SheetObject> sheetList = new ArrayList<SheetObject>();
    // sheet list start
    for (int i = 0; i < sheetNums; i++) {
      Sheet sheet = wbs.getSheetAt(i);
      SheetObject sheetObject = toSheetObject(i, sheet);
      sheetList.add(sheetObject);
    }
    excel.setRowset(sheetList);
    return excel;
  }

  public static ExcelObject toExcelObject(String excelName,
      List<SheetObject> sheetList) {
    ExcelObject excel = new ExcelObject();
    excel.setName(excelName);
    excel.setRowset(sheetList);
    return excel;
  }

  public static SheetObject toSheetObject(int index, Sheet sheet) {
    Row row = null;
    Cell cell = null;
    SheetObject sheetObject = new SheetObject();
    List<Column> columns = new ArrayList<Column>();
    List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
    int maxCols = 0;
    sheetObject.setMaxRows(sheet.getPhysicalNumberOfRows());
    if (sheet.getPhysicalNumberOfRows() > 0) {
      row = sheet.getRow(0);
      for (int i = 0; i < row.getLastCellNum(); i++) {
        cell = row.getCell(i);
        Column column = new Column();
        column.setName(cell.getStringCellValue());
        columns.add(column);
      }
    }
    // row list start
    for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
      row = sheet.getRow(j);
      if (null != row) {
        // cell list start
        Map<String, Object> object = new HashMap<String, Object>();
        for (int k = 0; k < columns.size(); k++) {
          cell = row.getCell(k);
          Object value;
          if (null != cell) {
            switch (cell.getCellType()) {
              case Cell.CELL_TYPE_STRING: // 字符串
                value = cell.getStringCellValue();
                break;
              case Cell.CELL_TYPE_NUMERIC: // 数字
                value = StringKit.doubleToObject(cell.getNumericCellValue());
                break;
              case Cell.CELL_TYPE_BOOLEAN: // Boolean
                value = cell.getBooleanCellValue();
                break;
              case Cell.CELL_TYPE_FORMULA: // 公式
                value = cell.getNumericCellValue();
                break;
              case Cell.CELL_TYPE_BLANK: // 空值
                value = "";
                break;
              case Cell.CELL_TYPE_ERROR: // 故障
                value = "";
                break;
              default:
                value = "";
                break;
            }
          } else {
            value = "";
          }
          object.put(columns.get(k).getName(), value);
        }// end cell list
        rows.add(object);
      }
    }// end row list
    int mergedRegions = sheet.getNumMergedRegions();
    List<MergedRegion> mergedRegionList = new ArrayList<MergedRegion>();
    for (int m = 1; m <= mergedRegions; m++) {
      CellRangeAddress cellRangeAddress = sheet.getMergedRegion(m - 1);
      MergedRegion mergedRegion = new MergedRegion();
      mergedRegion.setRowBegin(cellRangeAddress.getFirstRow());
      mergedRegion.setColBegin(cellRangeAddress.getFirstColumn());
      mergedRegion.setRowEnd(cellRangeAddress.getLastRow());
      mergedRegion.setColEnd(cellRangeAddress.getLastColumn());
      mergedRegionList.add(mergedRegion);
    }
    for (MergedRegion mergedRegion : mergedRegionList) {
      int rowBegin = mergedRegion.getRowBegin();
      int rowEnd = mergedRegion.getRowEnd();
      int colBegin = mergedRegion.getColBegin();
      int colEnd = mergedRegion.getColEnd();
      Object value = rows.get(rowBegin).get(columns.get(colBegin).getName());
      for (int m = rowBegin; m <= rowEnd; m++) {
        for (int n = colBegin; n <= colEnd; n++) {
          rows.get(m).put(columns.get(n).getName(), value);
        }
      }
    }
    sheetObject.setName(sheet.getSheetName());
    sheetObject.setIndex(index);
    sheetObject.setMaxCols(maxCols);
    sheetObject.setRow(rows);
    sheetObject.setColumns(columns);
    return sheetObject;
  }

  /**
   * 把ExcelEasy对象转换为JSON对象.
   * @param excelEasy
   * @return
   */
  public static JSONObject toJSONObject(ExcelObject excel) {
    return (JSONObject) JSON.toJSON(excel);
  }

  public static Date getExcelDate(Double dateValue) {
    return HSSFDateUtil.getJavaDate(dateValue, false);
  }
}
