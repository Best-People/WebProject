package com.xdShop.common.pojo;

import java.util.List;

public class EUItemListResult {
	private int toltal;
	private List<?> rows;
	public EUItemListResult(int total, List<?> list) {
		this.toltal=total;
		this.rows=list;
	}
	public int getToltal() {
		return toltal;
	}
	public void setToltal(int toltal) {
		this.toltal = toltal;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	} 
}
