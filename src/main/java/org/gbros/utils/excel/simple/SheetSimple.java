package org.gbros.utils.excel.simple;

import java.util.List;

import org.gbros.utils.excel.MergedRegion;

public class SheetSimple {
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
	private List<List<String>> data;
	/**
	 * Excel sheet all merge cells
	 */
	//private List<MergedRegion> mergedRegions;

	public SheetSimple() {
	}

	public SheetSimple(String name, int index, int maxRows, int maxCols,
			List<List<String>> data) {
		this.name = name;
		this.index = index;
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		this.data = data;
	}

	public SheetSimple(String name, int index, int maxRows, int maxCols,
			List<List<String>> data, List<MergedRegion> mergedRegions) {
		this.name = name;
		this.index = index;
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		this.data = data;
		//this.mergedRegions = mergedRegions;
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

	public List<List<String>> getData() {
		return data;
	}

	public void setData(List<List<String>> data) {
		this.data = data;
	}
}
