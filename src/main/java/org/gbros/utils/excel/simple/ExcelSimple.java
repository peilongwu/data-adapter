package org.gbros.utils.excel.simple;

import java.util.List;
/**
 * 简单Excel对象，只包括名称和sheet数据
 * 
 * @author Peilw
 * 
 */
public class ExcelSimple {

	private String name;
	private List<SheetSimple> sheets;

	public ExcelSimple() {
	}

	public ExcelSimple(String name, List<SheetSimple> sheets) {
		this.name = name;
		this.sheets = sheets;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SheetSimple> getSheets() {
		return sheets;
	}

	public void setSheets(List<SheetSimple> sheets) {
		this.sheets = sheets;
	}
}
