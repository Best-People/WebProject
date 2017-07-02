package com.xdShop.rest.pojo;

import java.util.List;

public class CatNode {
	private String u;
	private String n;
	private List<?> i;

	public List<?> getI() {
		return i;
	}
	public void setI(List<?> i) {
		this.i = i;
	}
	public String getU() { 
		return u;
	}
	public void setU(String u) {
		this.u = u;
	}
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
}
