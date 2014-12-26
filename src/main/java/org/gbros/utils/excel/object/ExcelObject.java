package org.gbros.utils.excel.object;

import java.util.List;
/**
 * 简单Excel对象，只包括名称和sheet数据
 * 
 * @author Peilw
 * 
 */
public class ExcelObject {

	private String Name;
	private List<SheetObject> Rowset;

	public ExcelObject() {
	}

	public ExcelObject(String Name, List<SheetObject> Rowset) {
		this.Name = Name;
		this.Rowset = Rowset;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<SheetObject> getRowset() {
		return Rowset;
	}

	public void setRowset(List<SheetObject> rowset) {
		Rowset = rowset;
	}
}
