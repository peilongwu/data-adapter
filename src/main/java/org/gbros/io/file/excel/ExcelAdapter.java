package org.gbros.io.file.excel;

import org.gbros.io.AbstractAdapter;
import org.gbros.io.Criteria;
import org.gbros.io.IoException;
import org.gbros.io.Param;
import org.gbros.io.Source;
import org.gbros.utils.excel.object.ExcelObjectUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ExcelAdapter  extends AbstractAdapter{
  
  private String fileName;

  /**
   * excel adapter constructor.
   * @param source
   * @throws IOException
   */
  public ExcelAdapter(Source source) {
    super(source);
    Object tmpSource = source.getSource();
    fileName = tmpSource.toString();
    File file = new File(fileName);
    if (!file.exists()) {
      throw new IoException("excel source is not exist");
    }
  }

  @Override
  public Map<String, Object> findObject(String collectionName,
      List<Criteria> criterias) throws IoException {
    return null;
  }

  @Override
  public List<Map<String, Object>> findCollection(String collectionName,
      List<Criteria> criterias, List<String> cols) throws IoException {
    try {
      return ExcelObjectUtils.getSheetData(fileName, collectionName);
    } catch (Exception e) {
      e.printStackTrace();
      throw new IoException("get data error");
    }
  }

  @Override
  public List<Map<String, Object>> findByStatement(String statement,
      List<Param> params) throws IoException {
    try {
      return ExcelObjectUtils.getSheetData(fileName, statement);
    } catch (Exception e) {
      e.printStackTrace();
      throw new IoException("get data error");
    }
  }

}
