package org.gbros.utils.excel.simple;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.gbros.utils.excel.MergedRegion;
import org.gbros.utils.StringKit;

/**
 * 处理Excel，只处理excel中cell数据
 * 
 * @author Peilw
 * @version 1.0
 */
public class ExcelSimpleUtils {
  /**
   * @function 处理excel文件流，按sheet生成json格式数组数据信息格式：{"sheets":[{"rows":[[],[],[]...]}]...}
   * @param in文件流
   * @param fileName 文件名称
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

  /**
   * @function 处理excel文件流，生成ExcelSimple对象.
   * @param inputStream 文件流
   * @param fileName 文件类型
   */
  public static ExcelSimple toExcelSimple(InputStream inputStream,
      String fileName) throws Exception {
    Workbook wbs = null;
    if (fileName.contains("xlsx")) {
      wbs = new XSSFWorkbook(inputStream);
    } else if (fileName.contains("xls")) {
      wbs = new HSSFWorkbook(inputStream);
    } else {
      throw new RuntimeException("Excel file type wrong! ");
    }
    return toExcelSimple(wbs, fileName);
  }

  public static ExcelSimple toExcelSimple(String fileName) throws Exception {
    File file = new File(fileName);
    if (!file.exists()) {
      throw new RuntimeException();
    } else {
      InputStream inputStream = new FileInputStream(file);
      return toExcelSimple(inputStream, fileName);
    }
  }

  public static JSONObject toJSONObject(String fileName) throws Exception {
    File file = new File(fileName);
    if (!file.exists()) {
      throw new RuntimeException();
    } else {
      InputStream inputStream = new FileInputStream(file);
      return toJSONObject(inputStream, fileName);
    }
  }

  public static String toJSONP(String callBackName, InputStream inputStream,
      String fileName) throws Exception {
    JSONObject json = toJSONObject(inputStream, fileName);
    String jsonp = callBackName + ":(" + json.toString() + ")";
    return jsonp;
  }

  /**
   * @function 处理Workbook，按sheet生成json格式数组数据信息
   *           格式：{"sheets":[{"rows":[[],[],[]...]}]...}
   * @param wbs
   *          Excel WorkBook
   * @param fileName
   *          文件类型
   * @return
   */
  private static JSONObject toJSONObject(Workbook wbs, String fileName)
      throws Exception {
    return (JSONObject) JSON.toJSON(toExcelSimple(wbs, fileName));
  }

  /**
   * @function 处理Workbook，生成ExcelSimple对象
   * @param Workbook
   *          wbs
   * @param fileName
   * @return
   */
  private static ExcelSimple toExcelSimple(Workbook wbs, String fileName)
      throws Exception {
    ExcelSimple excel = new ExcelSimple();
    excel.setName(fileName);
    int sheetNums = wbs.getNumberOfSheets();
    List<SheetSimple> sheetList = new ArrayList<SheetSimple>();
    // sheet list start
    for (int i = 0; i < sheetNums; i++) {
      Sheet sheet = wbs.getSheetAt(i);
      SheetSimple sheetSimple = new SheetSimple();
      sheetSimple.setName(sheet.getSheetName());
      sheetSimple.setIndex(i);
      List<List<String>> rowList = new ArrayList<List<String>>();
      int maxCols = 0;
      sheetSimple.setMaxRows(sheet.getPhysicalNumberOfRows());
      // row list start
      for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
        List<String> cellList = new ArrayList<String>();
        Row row = sheet.getRow(j);
        if (null != row) {
          // cell list start
          for (int k = 0; k < row.getLastCellNum(); k++) {
            Cell cell = row.getCell(k);
            String tmpString;
            if (null != cell) {
              switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING: // 字符串
                  tmpString = cell.getStringCellValue();
                  break;
                case Cell.CELL_TYPE_NUMERIC: // 数字
                  tmpString = StringKit
                      .doubleToString(cell.getNumericCellValue());
                  break;
                case Cell.CELL_TYPE_BOOLEAN: // Boolean
                  tmpString = Boolean.toString(cell.getBooleanCellValue());
                  break;
                case Cell.CELL_TYPE_FORMULA: // 公式
                  tmpString = Double.toString(cell.getNumericCellValue());
                  break;
                case Cell.CELL_TYPE_BLANK: // 空值
                  tmpString = "";
                  break;
                case Cell.CELL_TYPE_ERROR: // 故障
                  tmpString = "Error";
                  break;
                default:
                  tmpString = "未知类型";
                  break;
              }
              if (k > maxCols) {
                maxCols = k;
              }
            } else {
              tmpString = "";
            }
            cellList.add(tmpString);
          }// end cell list
        }
        rowList.add(cellList);
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
        String value = rowList.get(rowBegin).get(colBegin);
        for (int m = rowBegin; m <= rowEnd; m++) {
          for (int n = colBegin; n <= colEnd; n++) {
            rowList.get(m).set(n, value);
          }
        }
      }
      sheetSimple.setMaxCols(maxCols);
      sheetSimple.setData(rowList);
      sheetList.add(sheetSimple);
    }
    excel.setSheets(sheetList);
    return excel;
  }

  /**
   * @function 转换JSONObject 为Excel WorkBook
   * @param json
   * @return Workbook
   */
  public static Workbook toExcel(JSON json) {
    try {
      Workbook wbs;
      ExcelSimple excel = JSON.toJavaObject(json, ExcelSimple.class);
      if (excel.getName().contains("xlsx")) {
        wbs = new XSSFWorkbook();
      } else {
        wbs = new HSSFWorkbook();
      }
      List<SheetSimple> sheetList = excel.getSheets();
      for (int i = 0; i < sheetList.size(); i++) {
        SheetSimple sheetEasy = sheetList.get(i);
        Sheet sheet = wbs.createSheet(sheetEasy.getName());
        List<List<String>> rowList = sheetEasy.getData();
        for (int j = 0; j < rowList.size(); j++) {
          Row row = sheet.createRow(j);
          List<String> cellList = rowList.get(j);
          for (int k = 0; k < cellList.size(); k++) {
            Cell cell = row.createCell(k);
            cell.setCellValue(cellList.get(k));
          }
        }
      }
      return wbs;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static ExcelSimple toGbrosExcel(String excelName,
      List<SheetSimple> sheetList) {
    ExcelSimple excel = new ExcelSimple();
    excel.setName(excelName);
    excel.setSheets(sheetList);
    return excel;
  }

  public static SheetSimple toGbrosSheet(String sheetName,
      List<List<String>> list) {
    SheetSimple sheet = new SheetSimple();
    sheet.setName(sheetName);
    sheet.setData(list);
    return sheet;
  }

  /**
   * 把ExcelEasy对象转换为JSON对象
   * @param excelEasy
   * @return
   */
  public static JSONObject toJSONObject(ExcelSimple gbrosExcel) {
    return (JSONObject) JSON.toJSON(gbrosExcel);
  }

  public static Date getExcelDate(Double dateValue) {
    return HSSFDateUtil.getJavaDate(dateValue, false);
  }
}
