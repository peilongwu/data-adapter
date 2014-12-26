package org.gbros.utils.excel.object;

import java.util.List;
import java.util.Map;

public class SheetObject {
	/**
	 * Excel sheet name
	 */
	private String name;
	/**
	 * Excel sheet index begin 0
	 */
	private int index;
	/**
	 * Excel sheet maxRows
	 */
	private int maxRows;//
	/**
	 * Excel sheet maxCols
	 */
	private int maxCols;
	/**
	 * Excel sheet data
	 */
	private List<Map<String, Object>> row;

	/**
	 * Excel sheet title
	 */
	private List<Column> columns;

	public SheetObject() {
	}

	public SheetObject(String name, int index, int maxRows, int maxCols,
			List<Map<String, Object>> row,List<Column> columns) {
		this.name = name;
		this.index = index;
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		this.row = row;
		this.columns = columns;
	}

	public SheetObject(String name, int index, int maxRows, int maxCols,
			List<Map<String, Object>> row) {
		this.name = name;
		this.index = index;
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		this.row = row;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	public int getMaxCols() {
		return maxCols;
	}

	public void setMaxCols(int maxCols) {
		this.maxCols = maxCols;
	}

	public List<Map<String, Object>> getRow() {
		return row;
	}

	public void setRow(List<Map<String, Object>> row) {
		this.row = row;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

}
